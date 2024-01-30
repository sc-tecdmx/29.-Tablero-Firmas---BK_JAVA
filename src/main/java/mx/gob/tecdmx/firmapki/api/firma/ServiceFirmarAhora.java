package mx.gob.tecdmx.firmapki.api.firma;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceAlmacenarMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceConsultaMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceValidationsMethods;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.utils.dto.DTOConfiguracion;
import mx.gob.tecdmx.firmapki.utils.dto.DTOFirmanteDestinatario;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.dto.PayloadAltaDocumento;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceFirmarAhora {

	@Autowired
	ServiceValidationsMethods serviceValidationsMethods;
	
	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;
	
	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;



	public boolean altaDocAndfirmarAhora(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta, DTOResponse res,
			DTOResponseUserInfo userInfo) {

		for (DTOConfiguracion config : payload.getConfiguraciones()) {
			if (config.getAtributo().equals("FIRM")) {
				payload.setEnOrden(config.isConfig());
				break;
			}
		}

		// Le asignamos los datos básicos
		documentoAlta = new DAOAltaDocumento(payload.getFolioEspecial(), payload.getAsunto(), payload.getNotas(),
				payload.getContenido(), payload.getFechaLimiteFirma(), payload.isEnOrden());

		boolean dataValid = validateDataFirmarAhora(payload, documentoAlta, userInfo, res);
		if (!dataValid) {
			return false;
		}

		TabDocumentos documentoStored = serviceAlmacenarMethods.storeTabDocumento(documentoAlta.getDestinoDoc(),
				documentoAlta.getTipoDoc(), documentoAlta.getPrioridad(), documentoAlta.getFolioEspecial(),
				documentoAlta.getExpediente(), documentoAlta.getAsunto(), documentoAlta.getNotas(),
				documentoAlta.getContenido(), documentoAlta.getFechaLimiteFirma(), documentoAlta.isEnOrden(), userInfo,
				res, payload.getConfiguraciones());

		if (documentoStored == null) {
			return false;
		}

		boolean configStored = serviceAlmacenarMethods.storeTabDocConfig(payload.getConfiguraciones(), documentoStored, res);
		if (!configStored) {
			return false;
		}

		boolean isDocAdjuntosStored = serviceAlmacenarMethods.storeDocumentosAdjuntos(payload.getDocumentosAdjuntos(), documentoStored, res,
				userInfo);
		if (!isDocAdjuntosStored) {
			return false;
		}

		boolean isfirmantesStored = serviceAlmacenarMethods.storeFirmantes(documentoStored, documentoAlta, res);
		if (!isfirmantesStored) {
			return false;
		}

		boolean isDestinatariosStored = serviceAlmacenarMethods.storeDestinatarios(documentoStored, documentoAlta, res);
		if (!isDestinatariosStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_creado = serviceConsultaMethods.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.CREADO.getOpcion(), res);
		if (etapaDoc_creado == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Creado = serviceAlmacenarMethods.storeWorkFlow(documentoStored, etapaDoc_creado,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Creado == null) {
			return false;
		}

		try {
			// Espera por 2000 milisegundos (2 segundos)
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Manejar la excepción en caso de que el sleep sea interrumpido
			e.printStackTrace();
		}

		// Se obtienen los datos previamente guardados de la BD
		List<TabDocumentosAdjuntos> documentosAdjuntos = serviceConsultaMethods.getTabDocumentosAdjuntos(documentoStored);
		List<TabDocsFirmantes> firmantes = serviceConsultaMethods.getTabDocsFirmantes(documentoStored.getId());
		TabDocsFirmantes currentFirmante = serviceConsultaMethods.getCurrentFirmanteInList(firmantes, userInfo.getData().getEmpleado(), res);
		if (currentFirmante == null) {
			return false;
		}
		//

		boolean archivosPkiDocumentoStored = serviceAlmacenarMethods.storePkiDocumento(documentosAdjuntos,
				EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), documentoAlta.isEnOrden(),
				userInfo.getData().getEmpleado(), null, new Date(), null, res);
		if (!archivosPkiDocumentoStored) {
			return false;
		}

		String tipofirma = payload.getDocumentosAdjuntos().size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
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

		res.setMessage("Se ha guardado el documento satisfactoriamente en modo firmar ahora");
		res.setStatus("Success");
		payload.setFolio(documentoStored.getFolioDocumento());
		res.setData(payload);

		return true;
	}
	
	public boolean validateDataFirmarAhora(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponseUserInfo userInfo, DTOResponse res) {
		// Validamos que los datos de catálogos existan
		boolean catalogosValid = serviceValidationsMethods.validateCatalogos(payload.getNumExpediente(), payload.getTipoDocumento(),
				payload.getTipoDestino(), payload.getTipoPrioridad(), documentoAlta, res);
		if (!catalogosValid) {
			return false;
		}
		// Validamo que el que firma se haya agregado a la lista de firmantes
		if (payload.getFirmantes().size() < 1) {
			res.setMessage(
					"La lista de frirmantes está vacía y para poder firmar debes agregarte como usuario firmante");
			res.setStatus("fail");
			return false;
		}

		boolean firmantesExist = serviceValidationsMethods.validateFirmantesExist(payload, documentoAlta, res);
		if (!firmantesExist) {
			return false;
		}
		
		res = serviceValidationsMethods.verificaFirmanteDestinatarioIsUsuario(payload.getFirmantes(), res);

		if (!res.getStatus().equals("Success")) {
			return false;
		}

		boolean destinatariosExist = serviceValidationsMethods.validateDestinatariosExist(payload, documentoAlta, res);
		if (!destinatariosExist) {
			return false;
		}
		
		res = serviceValidationsMethods.verificaFirmanteDestinatarioIsUsuario(payload.getDestinatarios(), res);

		if (!res.getStatus().equals("Success")) {
			return false;
		}

		boolean ordenAndSecuenciaValid = validateOrdenAndSecuenciaFirmantesFirmarAhora(payload, documentoAlta, userInfo,
				res);
		if (!ordenAndSecuenciaValid) {
			return false;
		}

		boolean archivosUnicos = serviceValidationsMethods.validarArchivosUnicos(payload.getDocumentosAdjuntos(), res);
		if (!archivosUnicos) {
			return false;
		}

		boolean isNuevoArchivoInDB = serviceValidationsMethods.validateArchivoNuevoInDataBase(payload.getDocumentosAdjuntos(), res);
		if (!isNuevoArchivoInDB) {
			return false;
		}

		return true;

	}
	
	public boolean validateOrdenAndSecuenciaFirmantesFirmarAhora(PayloadAltaDocumento payload,
			DAOAltaDocumento documentoAlta, DTOResponseUserInfo userInfo, DTOResponse res) {
		if (payload.isEnOrden()) {
			// Ordenamos la lista conforme a la secuencia dada por el usuario
			Collections.sort(payload.getFirmantes(), (o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));
			Collections.sort(documentoAlta.getFirmantes(),
					(o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

			// Verificamos que la numeración de la secuencia no este corrupta (que se salte
			// números)
			int secuencia = 1;
			for (DTOFirmanteDestinatario firmante : payload.getFirmantes()) {
				if (firmante.getSecuencia() != secuencia) {
					res.setMessage("La secuencia en los firmantes se salta la numeración");
					res.setStatus("fail");
					return false;
				}
				secuencia++;
			}

			DTOFirmanteDestinatario firmante1 = payload.getFirmantes().get(0);
			// Como la opción es Firmar ahora, debemos verificar que el que va a firmar
			// ahora sea el primero en la lista y si no, entonces lo ponemos como primero
			if (firmante1.getIdEmpleado() != userInfo.getData().getEmpleado().getId()) {
				res.setMessage(
						"Para permitir la opción firmar ahora, debes estar en la lista de firmantes y tener como número 1 en el orden de secuencia de firma");
				res.setStatus("fail");
				return false;
			}
			return true;
		} else {
			boolean isInList = false;
			for (DTOFirmanteDestinatario firmante : payload.getFirmantes()) {
				if (firmante.getIdEmpleado() == userInfo.getData().getIdEmpleado()) {
					isInList = true;
				}
			}
			if (!isInList) {
				res.setMessage("Para poder firmar debes agregarte como usuario firmante");
				res.setStatus("fail");
				return false;
			}
			return true;
		}
	}



}
