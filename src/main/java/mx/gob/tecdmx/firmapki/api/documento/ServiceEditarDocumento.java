package mx.gob.tecdmx.firmapki.api.documento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceAlmacenarMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceConsultaMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceValidationsMethods;
import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.api.firma.DAOFirmanteDestinatario;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDocConfig;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocConfig;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocDestinatarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocConfigRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocDestinatariosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocsFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentoWorkflowRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.utils.dto.DTOConfiguracion;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.dto.PayloadAltaDocumento;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceEditarDocumento {
	
	@Autowired
	TabDocConfigRepository tabConfigDocumentoRepository;
	
	@Autowired
	TabDocumentosRepository tabDocumentosRepository;

	@Autowired
	TabDocsFirmantesRepository tabDocsFirmantesRepository;

	@Autowired
	TabDocDestinatariosRepository tabDocDestinatariosRepository;
	
	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;
	
	@Autowired
	TabDocumentoWorkflowRepository tabDocumentoWorkflowRepository;
	
	@Autowired
	ServiceValidationsMethods serviceValidationsMethods;
	
	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;
	
	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;
	
	@Autowired
	ServiceModoCapturaDocumento serviceModoCapturaDocumento;
	
	public boolean editarDocumento(int idDocumento, PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res, DTOResponseUserInfo userInfo) {

		for (DTOConfiguracion config : payload.getConfiguraciones()) {
			if (config.getAtributo().equals("FIRM")) {
				payload.setEnOrden(config.isConfig());
				break;
			}
		}

		// busca el documento a editar
		Optional<TabDocumentos> documentExist = tabDocumentosRepository.findById(idDocumento);

		if (!documentExist.isPresent()) {
			res.setMessage("El documento que deseas actualizar no existe");
			res.setStatus("Fail");
			res.setData(payload);
			return false;
		}

		List<TabCatDocConfig> listConfig = new ArrayList<TabCatDocConfig>();
		boolean validCatalog = serviceValidationsMethods.validateCatalogEditDocument(payload.getConfiguraciones(), listConfig);

		if (!validCatalog) {
			res.setMessage("los elementos del catálogo de configuración no existen");
			res.setStatus("Fail");
			res.setData(payload);
			return false;
		}

		List<TabDocConfig> tabDocConfList = tabConfigDocumentoRepository.findByIdDocumento(idDocumento);
		for (TabDocConfig tabDocConf : tabDocConfList) {
			tabConfigDocumentoRepository.delete(tabDocConf);
		}

		TabDocConfig docConfig = null;
		for (TabCatDocConfig configCatalogo : listConfig) {
			docConfig = new TabDocConfig();
			docConfig.setIdDocumento(idDocumento);
			docConfig.setIdDocConfig(configCatalogo.getId());
			tabConfigDocumentoRepository.save(docConfig);
		}

		// Le asignamos los datos básicos
		documentoAlta = new DAOAltaDocumento(payload.getFolioEspecial(), payload.getAsunto(), payload.getNotas(),
				payload.getContenido(), payload.getFechaLimiteFirma(), payload.isEnOrden());

		// valida los datos que sean correctos
		boolean dataValid = serviceModoCapturaDocumento.validateDataModoCaptura(payload, documentoAlta, userInfo, res);
		if (!dataValid) {
			return false;
		}
		
		List<TabDocumentoWorkflow> docWorkflowList = tabDocumentoWorkflowRepository
				.findByIdDocumentOrderByWorkflowFecha(documentExist.get());
		if (docWorkflowList.get(0).getIdEtapaDocumento().getDescetapa()
				.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion())) {
			// Elimina primero los docs adjuntos existentes solo para cuando se encuentre en
			// etapa creado// esto no lo hace para docs cargados en app de escritorio
			List<TabDocumentosAdjuntos> docsExistentes = tabDocumentosAdjuntosRepository
					.findByIdDocument(documentExist.get());
			for (TabDocumentosAdjuntos docAdjunto : docsExistentes) {
				tabDocumentosAdjuntosRepository.delete(docAdjunto);
			}
		}
		
		// almacena en tab los nuevos datos del documento
		TabDocumentos documentoEdited = editTabDocumento(documentExist.get(),
				documentoAlta.getDestinoDoc(), documentoAlta.getTipoDoc(), documentoAlta.getPrioridad(),
				documentoAlta.getFolioEspecial(), documentoAlta.getExpediente(), documentoAlta.getAsunto(),
				documentoAlta.getNotas(), documentoAlta.getContenido(), documentoAlta.getFechaLimiteFirma(),
				documentoAlta.isEnOrden(), userInfo, res);

		if (documentoEdited == null) {
			return false;
		}
		// esto no lo hace para docs cargados en app de escritorio
		if (docWorkflowList.get(0).getIdEtapaDocumento().getDescetapa()
				.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion())) {
			boolean isDocAdjuntosStored = serviceAlmacenarMethods.storeDocumentosAdjuntos(payload.getDocumentosAdjuntos(), documentoEdited, res,
					userInfo);
			if (!isDocAdjuntosStored) {
				return false;
			}
		}

		boolean isfirmantesStored = editedFirmantes(documentoEdited, documentoAlta, res);
		if (!isfirmantesStored) {
			return false;
		}

		boolean isDestinatariosStored = editedDestinatarios(documentoEdited, documentoAlta, res);
		if (!isDestinatariosStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_creado = serviceConsultaMethods.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.CREADO.getOpcion(), res);
		if (etapaDoc_creado == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Creado = serviceAlmacenarMethods.storeWorkFlow(documentoEdited, etapaDoc_creado,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Creado == null) {
			return false;
		}

		res.setMessage("Se ha actualizado el documento satisfactoriamente");
		res.setStatus("Success");
		payload.setFolio(documentoEdited.getFolioDocumento());
		res.setData(payload);

		return true;
	}
	
	public TabDocumentos editTabDocumento(TabDocumentos documento, TabCatDestinoDocumento tipoDestino, TabCatTipoDocumento tipoDocumento,
			TabCatPrioridad prioridad, String folioEspecial, TabExpedientes numExpediente, String asunto, String notas,
			String contenido, Date fechaLimiteFirma, boolean isEnOrden, DTOResponseUserInfo userInfo, DTOResponse res) {

		try {
			documento.setIdTipoDestino(tipoDestino);
			documento.setIdTipoDocumento(tipoDocumento);
			documento.setIdPrioridad(prioridad);

			documento.setFolioEspecial(folioEspecial);
			documento.setNumExpediente(numExpediente);
			documento.setEnOrden(isEnOrden ? 1 : 0);
			documento.setAsunto(asunto);
			documento.setNotas(notas);
			documento.setContenido(contenido);
			documento.setFechaLimiteFirma(fechaLimiteFirma);
			documento.setHashDocumento(null);// Quitar de la base de datos este campo

			TabDocumentos documentoEdited = tabDocumentosRepository.save(documento);
			
			return documentoEdited;
		} catch (Exception e) {
			res.setMessage("" + e);
			res.setStatus("fail");
			return null;
		}

	}
	
	public boolean editedFirmantes(TabDocumentos documentoStored, DAOAltaDocumento documentoAlta, DTOResponse res) {

		List<TabDocsFirmantes> firmantesExistentes = tabDocsFirmantesRepository
				.findByIdDocumento(documentoStored.getId());
		for (TabDocsFirmantes firmante : firmantesExistentes) {
			tabDocsFirmantesRepository.delete(firmante);
		}

		Collections.sort(documentoAlta.getFirmantes(),
				(o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

		// Se almacenan los firmantes
		for (DAOFirmanteDestinatario firmante : documentoAlta.getFirmantes()) {
			TabDocsFirmantes firmanteStored = serviceAlmacenarMethods.storeFirmante(documentoStored, firmante.getEmpleado(),
					firmante.getInstruccionFirmante(), firmante.getSecuencia(), res);
			if (firmanteStored == null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean editedDestinatarios(TabDocumentos documentoStored, DAOAltaDocumento documentoAlta, DTOResponse res) {

		List<TabDocDestinatarios> destinatariosExistentes = tabDocDestinatariosRepository
				.findByIdDocumento(documentoStored.getId());
		for (TabDocDestinatarios destinatario : destinatariosExistentes) {
			tabDocDestinatariosRepository.delete(destinatario);
		}

		for (DAOFirmanteDestinatario destinatario : documentoAlta.getDestinatarios()) {

			TabDocDestinatarios destinatarioStored = serviceAlmacenarMethods.storeDestinatario(documentoStored,
					destinatario.getEmpleado(), destinatario.getInstruccionDest(), res);
			if (destinatarioStored == null) {
				return false;
			}
		}
		return true;
	}


}
