package mx.gob.tecdmx.firmapki.api.escritorio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceAlmacenarMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceConsultaMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceValidationsMethods;
import mx.gob.tecdmx.firmapki.api.documento2.PayloadAltaDocumento;
import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceAltaDocumentoEscritorio {
	
	@Autowired
	ServiceValidationsMethods serviceValidacionesMetodos;
	
	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;
	
	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;
	
	public boolean altaDocAndfirmarAhoraEscritorio(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res, DTOResponseUserInfo userInfo) {

		// Le asignamos los datos básicos
		documentoAlta = new DAOAltaDocumento(payload.getFolioEspecial(), payload.getAsunto(), payload.getNotas(),
				payload.getContenido(), payload.getFechaLimiteFirma(), payload.isEnOrden());

		boolean dataValid = validateDataFirmarAhoraEscritorio(payload, documentoAlta, userInfo, res);
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

		boolean isDocAdjuntosStored = serviceAlmacenarMethods.storeDocumentosAdjuntosDesktop(payload.getDocumentosAdjuntos(), documentoStored,
				res, userInfo);
		if (!isDocAdjuntosStored) {
			return false;
		}

		boolean isfirmantesStored = serviceAlmacenarMethods.storeFirmantes(documentoStored, documentoAlta, res);
		if (!isfirmantesStored) {
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

		for (int doc = 0; doc < documentosAdjuntos.size(); doc++) {
			payload.getDocumentosAdjuntos().get(doc).setOriginalHash(documentosAdjuntos.get(doc).getDocumentoHash());
		}
		List<TabDocsFirmantes> firmantes = serviceConsultaMethods.getTabDocsFirmantes(documentoStored.getId());
		TabDocsFirmantes currentFirmante = serviceValidacionesMetodos.getCurrentFirmanteInList(firmantes, userInfo.getData().getEmpleado(), res);
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

		res.setMessage("Se ha guardado el documento satisfactoriamente en firma de Escritorio");
		res.setStatus("Success");
		payload.setFolio(documentoStored.getFolioDocumento());
		res.setData(payload);

		return true;
	}
	
	public boolean validateDataFirmarAhoraEscritorio(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponseUserInfo userInfo, DTOResponse res) {

		boolean catalogosValid = serviceValidacionesMetodos.validateCatalogosEscritorio(payload.getTipoPrioridad(), documentoAlta, res);
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

		payload.getFirmantes().get(0).setIdEmpleado(userInfo.getData().getIdEmpleado());
		payload.getFirmantes().get(0).setSecuencia(0);

		boolean firmantesExist = serviceValidacionesMetodos.validateFirmantesExist(payload, documentoAlta, res);
		if (!firmantesExist) {
			return false;
		}

		boolean archivosUnicos = serviceValidacionesMetodos.validarArchivosUnicos(payload.getDocumentosAdjuntos(), res);
		if (!archivosUnicos) {
			return false;
		}

		boolean isNuevoArchivoInDB = serviceValidacionesMetodos.validateArchivoNuevoInDataBase(payload.getDocumentosAdjuntos(), res);
		if (!isNuevoArchivoInDB) {
			return false;
		}

		return true;
	}
	

}
