package mx.gob.tecdmx.firmapki.api.firma;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceAlmacenarMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceConsultaMethods;
import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumento;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocDestinatarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoRepository;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.dto.PayloadFirma;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceFirmarDocumento {
	
	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;
	
	@Autowired
	PkiDocumentoRepository pkiDocumentoRepository;
	
	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;
	
	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;
	
	@Autowired
	ServiceFirma serviceFirma;
	
	@Value("${firma.document.pdf.path}")
	private String documentPath;

	@Value("${firma.document.pdf.firmados.path}")
	private String documentFirmadosPath;

	@Value("${firma.document.encryption}")
	private String encryptionAlgorithm;
	
	public boolean firmar(PayloadFirma payload, DTOResponse res, DTOResponseUserInfo userInfo) {
		ResponseBodyFirma responde = new ResponseBodyFirma();
		List<TabDocDestinatarios> listaDestinatariosTab = null;
		// Sección de actualización de PKI documentos_firmantes
		HashDocumentoIdUsuarioIdTransaccionID idDocumentoFirmantes = new HashDocumentoIdUsuarioIdTransaccionID();
		idDocumentoFirmantes.setHashDocumento(payload.getHashDocumento());
		idDocumentoFirmantes.setIdUsuario(userInfo.getData().getIdUsuario());

		Optional<PkiDocumentoFirmantes> docFirmantesPki = pkiDocumentoFirmantesRepository
				.findById(idDocumentoFirmantes);
		if (!docFirmantesPki.isPresent()) {
			res.setData(null);
			res.setStatus("fail");
			res.setMessage("No puedes firmar este documento, ya que no te fue asignado para firmar");
			return false;
		}

		PkiCatFirmaAplicada firmaAplicada_firmado = serviceConsultaMethods
				.findFirmaAplicada(EnumPkiCatFirmaAplicada.FIRMADO.getOpcion(), res);
		if (firmaAplicada_firmado == null) {
			return false;
		}

		try {
			docFirmantesPki.get().setCadenaFirma(payload.getCadenaFirma().toString());
			docFirmantesPki.get().setFechaFirma(new Date());
			docFirmantesPki.get().setIdFirmaAplicada(firmaAplicada_firmado);

			pkiDocumentoFirmantesRepository.save(docFirmantesPki.get());

		} catch (Exception e) {
			res.setMessage("No se pudo actualizar la tabla documentos firmantes");
			res.setStatus("fail");
			return false;
		}

		// Sección para agregar firma al documento pdf
		String fileName = documentFirmadosPath + "/" + payload.getHashDocumento() + "_"
				+ userInfo.getData().getIdUsuario() + ".pdf";

		serviceFirma.firma(payload.getHashDocumento(), payload.getDocumento(), payload.getCadenaFirma(),
				payload.getCertificado(), fileName,
				userInfo);

		try {
			// sección para actualizar documento PKI
			PkiDocumento documentoPki = docFirmantesPki.get().getDocumento();

			documentoPki.setAlgoritmo(encryptionAlgorithm);
			//

			TabDocumentosAdjuntos documentoAdjuntoTab = serviceConsultaMethods
					.getTabDocumentoAdjuntoByHash(payload.getHashDocumento(), res);
			if (documentoAdjuntoTab == null) {
				return false;
			}
			List<TabDocumentosAdjuntos> listaDocAdjuntosTab = serviceConsultaMethods
					.getTabDocumentosAdjuntos(documentoAdjuntoTab.getIdDocument());
			List<TabDocsFirmantes> listaFirmantesTab = serviceConsultaMethods
					.getTabDocsFirmantes(documentoAdjuntoTab.getIdDocument().getId());
			
			listaDestinatariosTab = serviceConsultaMethods.getTabDocsDestinatarios(documentoAdjuntoTab.getIdDocument().getId());

			for (TabDocumentosAdjuntos docAdjuntoTab : listaDocAdjuntosTab) {
				boolean archivoCompleted = true;
				for (TabDocsFirmantes firmanteTab : listaFirmantesTab) {

					Optional<PkiDocumentoFirmantes> docUsuFirmante = pkiDocumentoFirmantesRepository
							.findByHashDocumentoAndIdNumEmpleadoAndIdFirmaAplicada(docAdjuntoTab.getDocumentoHash(),
									firmanteTab.getIntEmpleado(), firmaAplicada_firmado);
					if (!docUsuFirmante.isPresent()) {
						archivoCompleted = false;
					}
				}
				if (archivoCompleted) {
					documentoPki.setTerminado(1);
				}
			}

			pkiDocumentoRepository.save(documentoPki);

		} catch (Exception e) {
			res.setMessage("No se pudo actualizar la información en la tabla documento PKI");
			res.setStatus("fail");
			return false;
		}

		// Validamos si podemos dar por terminado el dcumento (No el archivo adjunto,
		// más bien el tab documento)
		// Obtenemos los elementos necesarios de la sección de la BD tablero para
		// posterior
		TabDocumentosAdjuntos documentoAdjuntoTab = serviceConsultaMethods
				.getTabDocumentoAdjuntoByHash(payload.getHashDocumento(), res);
		if (documentoAdjuntoTab == null) {
			return false;
		}
		List<TabDocumentosAdjuntos> documentosAdjuntosTabList = serviceConsultaMethods
				.getTabDocumentosAdjuntos(documentoAdjuntoTab.getIdDocument());

		boolean allDocsFirmados = true;
		PkiDocumento pkiDoc = null;
		for (TabDocumentosAdjuntos docAdjunto : documentosAdjuntosTabList) {
			pkiDoc = serviceConsultaMethods.getDocumentoPKIByHash(docAdjunto.getDocumentoHash(), res);
			if (pkiDoc.getTerminado() == 0) {
				allDocsFirmados = false;
			}
		}

		if (allDocsFirmados) {
			boolean documentoDestinatariosStored = serviceAlmacenarMethods.storePkiDocumentoDestinatarios(documentosAdjuntosTabList,listaDestinatariosTab,res);
			
			TabCatEtapaDocumento etapaDoc_terminado = serviceConsultaMethods
					.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.TERMINADO.getOpcion(), res);
			if (etapaDoc_terminado == null) {
				return false;
			}

			TabDocumentoWorkflow workflowStored_Terminado = serviceAlmacenarMethods.storeWorkFlow(
					documentoAdjuntoTab.getIdDocument(), etapaDoc_terminado,
					userInfo.getData().getEmpleado(), res);
			if (workflowStored_Terminado == null) {
				return false;
			}
		}

		responde.setAlgortimo(encryptionAlgorithm);

		res.setData(responde);
		res.setStatus("Succes");
		res.setMessage("Documento firmado");
		return true;

	}


}
