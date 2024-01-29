package mx.gob.tecdmx.firmapki.api.documento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceAlmacenarMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceConsultaMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceValidationsMethods;
import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.utils.dto.DTOConfiguracion;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.dto.PayloadAltaDocumento;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceModoCapturaDocumento {
	
	@Autowired
	ServiceValidationsMethods serviceValidationsMethods;
	
	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;
	
	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;
	
	public boolean altaDocumentoModoCaptura(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res, DTOResponseUserInfo userInfo) {

		for (DTOConfiguracion config : payload.getConfiguraciones()) {
			if (config.getAtributo().equals("FIRM")) {
				payload.setEnOrden(config.isConfig());
				break;
			}
		}

		// Le asignamos los datos básicos
		documentoAlta = new DAOAltaDocumento(payload.getFolioEspecial(), payload.getAsunto(), payload.getNotas(),
				payload.getContenido(), payload.getFechaLimiteFirma(), payload.isEnOrden());

		boolean dataValid = validateDataModoCaptura(payload, documentoAlta, userInfo, res);
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

		res.setMessage("Se ha guardado el documento satisfactoriamente en modo captura");
		res.setStatus("Success");
		payload.setFolio(documentoStored.getFolioDocumento());
		res.setData(payload);

		return true;
	}
	
	public boolean validateDataModoCaptura(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponseUserInfo userInfo, DTOResponse res) {
		// Validamos que los datos de catálogos existan
		boolean catalogosValid = serviceValidationsMethods.validateCatalogos(payload.getNumExpediente(), payload.getTipoDocumento(),
				payload.getTipoDestino(), payload.getTipoPrioridad(), documentoAlta, res);
		if (!catalogosValid) {
			return false;
		}

		if (payload.getFirmantes() != null) {

			boolean firmantesExist = serviceValidationsMethods.validateFirmantesExist(payload, documentoAlta, res);
			if (!firmantesExist) {
				return false;
			}
			res = serviceValidationsMethods.verificaFirmanteDestinatarioIsUsuario(payload.getFirmantes(), res);

			if (!res.getStatus().equals("Success")) {
				return false;
			}
		}

		if (payload.getDestinatarios() != null) {

			boolean destinatariosExist = serviceValidationsMethods.validateDestinatariosExist(payload, documentoAlta, res);
			if (!destinatariosExist) {
				return false;
			}

			res = serviceValidationsMethods.verificaFirmanteDestinatarioIsUsuario(payload.getDestinatarios(), res);

			if (!res.getStatus().equals("Success")) {
				return false;
			}
		}

		if (payload.getDocumentosAdjuntos() != null) {
			boolean archivosUnicos = serviceValidationsMethods.validarArchivosUnicos(payload.getDocumentosAdjuntos(), res);
			if (!archivosUnicos) {
				return false;
			}

			boolean isNuevoArchivoInDB = serviceValidationsMethods.validateArchivoNuevoInDataBase(payload.getDocumentosAdjuntos(), res);
			if (!isNuevoArchivoInDB) {
				return false;
			}
		}

		return true;

	}
	

	

}
