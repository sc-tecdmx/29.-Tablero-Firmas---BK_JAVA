package mx.gob.tecdmx.firmapki.api.firma;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceAlmacenarMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceConsultaMethods;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocDestinatarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceGoToFirmar {
	
	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;
	
	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;
	
	public boolean gotoFirmar(int idTabDocumento, DTOResponseUserInfo userInfo, DTOResponse res) {	

		List<TabDocsFirmantes> firmantes = serviceConsultaMethods.getTabDocsFirmantes(idTabDocumento);
		List<TabDocDestinatarios> destinatarios = serviceConsultaMethods.getTabDocsDestinatarios(idTabDocumento);

		TabDocsFirmantes currentFirmante = serviceConsultaMethods.getCurrentFirmanteInList(firmantes, userInfo.getData().getEmpleado(), res);
		if (currentFirmante == null) {
			return false;
		}

		TabDocumentos documentoStored = serviceConsultaMethods.findTabDocumento(idTabDocumento, res);
		if (documentoStored == null) {
			return false;
		}

		String etapaSequence = serviceConsultaMethods.getSequenceWorkflow(documentoStored, res);
		if (etapaSequence == null) {
			return false;
		}

		List<TabDocumentosAdjuntos> documentosAdjuntos = serviceConsultaMethods.getTabDocumentosAdjuntos(documentoStored);

		if (etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion()) || etapaSequence.equals(
				EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-" + EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())) {
			boolean puedeFirmarEnEtapaCreado = puedeFirmarEnEtapaCreado(documentoStored, firmantes, userInfo, res);
			if (!puedeFirmarEnEtapaCreado) {
				return false;
			}

			boolean firmado = firmar(documentoStored, documentosAdjuntos, firmantes, currentFirmante, userInfo, res);
			if (!firmado) {
				return false;
			}

			res.setMessage("Se ha firmado el documento satisfactoriamente con número de folio: "
					+ documentoStored.getFolioDocumento());
			res.setStatus("Success");
			return true;
		} else if (etapaSequence.equals(
				EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-" + EnumTabCatEtapaDocumento.ENVIADO.getOpcion())
				|| etapaSequence.equals(
						EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-" + EnumTabCatEtapaDocumento.ENVIADO.getOpcion()
								+ "-" + EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())
				|| etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.ENVIADO.getOpcion())
				|| etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.ENVIADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())) {

			TabCatEtapaDocumento etapaDoc_enfirma = serviceConsultaMethods.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(),
					res);
			if (etapaDoc_enfirma == null) {
				return false;
			}

			TabDocumentoWorkflow workflowStored_Enfirma = serviceAlmacenarMethods.storeWorkFlow(documentoStored, etapaDoc_enfirma,
					userInfo.getData().getEmpleado(), res);
			if (workflowStored_Enfirma == null) {
				return false;
			}

		}
		res.setMessage("Se ha firmado el documento satisfactoriamente ");
		res.setStatus("Success");
		return true;

	}
	
	public boolean puedeFirmarEnEtapaCreado(TabDocumentos documentoStored, List<TabDocsFirmantes> firmantes,
			DTOResponseUserInfo userInfo, DTOResponse res) {
		// validamos si el usuario que quiere firmar es el mismo quecreó el documento
		if (documentoStored.getIdUsuarioCreador() == userInfo.getData().getUser()) {
			// validamos si el documento s econfiguró para firmar en orden
			if (documentoStored.getEnOrden() == 1) {

				Collections.sort(firmantes, (o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));
				Optional<TabDocsFirmantes> resultado = firmantes.stream()
						.filter(firmante -> firmante.getIntEmpleado() == userInfo.getData().getEmpleado()).findFirst();
				if (resultado.get().getSecuencia() == 1) {
					return true;
				} else {
					res.setMessage(
							"Para poder firmar debes tener el número de secuencia 1, ya que el documento se connfiguró para firmar en orden");
					res.setStatus("fail");
					return false;
				}
			} else {
				return true;
			}

		} else {
			res.setMessage("El documento en etapa " + EnumTabCatEtapaDocumento.CREADO.getOpcion()
					+ " aún no puede ser firmado por otra persona distinta al creador");
			res.setStatus("fail");
			return false;
		}

	}
	
	public boolean firmar(TabDocumentos documentoStored, List<TabDocumentosAdjuntos> documentosAdjuntos,
			List<TabDocsFirmantes> firmantes, TabDocsFirmantes currentFirmante, DTOResponseUserInfo userInfo,
			DTOResponse res) {

		boolean archivosPkiDocumentoStored = serviceAlmacenarMethods.storePkiDocumento(documentosAdjuntos,
				EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), documentoStored.getEnOrden() == 1 ? true : false,
				userInfo.getData().getEmpleado(), null, new Date(), null, res);
		if (!archivosPkiDocumentoStored) {
			return false;
		}

		String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
				: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
		PkiCatTipoFirma tipoFirma = serviceConsultaMethods.findTipoFirma(tipofirma, res);
		if (tipoFirma == null) {
			return false;
		}

		boolean documentoFirmantesStored = serviceAlmacenarMethods.storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored, tipoFirma,
				currentFirmante, res);
		if (!documentoFirmantesStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_enfirma = serviceConsultaMethods.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), res);
		if (etapaDoc_enfirma == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Enfirma = serviceAlmacenarMethods.storeWorkFlow(documentoStored, etapaDoc_enfirma,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Enfirma == null) {
			return false;
		}
		return true;
	}



}
