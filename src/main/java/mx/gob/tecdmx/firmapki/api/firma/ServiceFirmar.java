package mx.gob.tecdmx.firmapki.api.firma;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumento;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDocConfig;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstDest;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocConfig;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocDestinatarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatDocConfigRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocConfigRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocDestinatariosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocsFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentoWorkflowRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@Service
public class ServiceFirmar {

	@Autowired
	PkiDocumentoRepository pkiDocumentoRepository;

//	@Autowired
//	ServiceFirmarAhora serviceFirmarAhora;

	@Autowired
	TabDocumentosRepository tabDocumentosRepository;

	@Autowired
	TabCatDocConfigRepository tabCatConfigDocumentoRepository;

	@Autowired
	TabDocConfigRepository tabConfigDocumentoRepository;

	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;

	@Autowired
	TabDocsFirmantesRepository tabDocsFirmantesRepository;

	@Autowired
	TabDocDestinatariosRepository tabDocDestinatariosRepository;
	
	@Autowired
	TabDocumentoWorkflowRepository tabDocumentoWorkflowRepository;
	
	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;

	@Value("${firma.document.encryption}")
	private String encryptionAlgorithm;

	@Value("${firma.document.pdf.path}")
	private String documentPath;
	
	CertificateUtils utils = null;

	public boolean documentNotExistInDataBase(String docBase64, int pos, DTOResponse res) {
		utils = new CertificateUtils();
		try {
			Optional<TabDocumentosAdjuntos> documentInDB = tabDocumentosAdjuntosRepository
					.findByDocumentoHash(utils.calcularSHA256(new String(docBase64)));
			if (documentInDB.isPresent()) {
				res.setMessage("El archivo adjunto número "+pos+" que cargaste, ya existe en la BD, no se pueden duplicar documentos");
				res.setStatus("fail");
				return false;
			}
			return true;
		}catch(Exception e) {
			res.setMessage("La BD está corrupta. Contiene dos o más archivos repetidos iguales al archivo adjunto número "+pos+" que cargaste");
			res.setStatus("fail");
			return false;
		}
	}
	
	public PkiDocumento getDocumentoPKIByHash(String hashDocumento, DTOResponse res) {
		Optional<PkiDocumento> documento = pkiDocumentoRepository.findById(hashDocumento);
		if (!documento.isPresent()) {
			res.setMessage("No se encontró en la base de datos pki el archivo que intentas firmar");
			res.setStatus("fail");
			return null;
		}
		return documento.get();
	}

	public String buildFileName(String fecha, String tipoDocumento, int documentoId, int numDocumento) {
		String fileName = fecha + "_" + tipoDocumento + "_" + documentoId + "_" + numDocumento + ".pdf";
		return fileName;
	}
	
	public List<TabDocumentosAdjuntos> getTabDocumentosAdjuntos(TabDocumentos documento) {
		return tabDocumentosAdjuntosRepository.findByIdDocument(documento);
	}
	
	public TabDocumentosAdjuntos getTabDocumentoAdjuntoByHash(String hashDocumento, DTOResponse res) {
		Optional<TabDocumentosAdjuntos> docTabDocumentoAdjunto = tabDocumentosAdjuntosRepository
				.findByDocumentoHash(hashDocumento);
		if(!docTabDocumentoAdjunto.isPresent()) {
			res.setMessage("No se pudo encontrar el documento de la tabla de tablero que corresponde al archivo que se está firmando");
			res.setStatus("fail");
			return null;
		}
		return docTabDocumentoAdjunto.get();
	}
	
	
	public boolean updateDataEnviadoInPkiDocumentosAdjuntos(List<TabDocumentosAdjuntos> documentosAdjuntos, 
			Date fechaEnvio, InstEmpleado empleadoEnvio, DTOResponse res) {
		for(TabDocumentosAdjuntos docAdjunto : documentosAdjuntos) {
			Optional<PkiDocumento> documentoPki = pkiDocumentoRepository.findByHashDocumento(docAdjunto.getDocumentoHash());
			if(documentoPki.isPresent()) {
				documentoPki.get().setFechaEnvio(fechaEnvio);
				documentoPki.get().setIdNumEmpleadoEnvio(empleadoEnvio);
				pkiDocumentoRepository.save(documentoPki.get());
			}
		}
//		res.setMessage("Hay inconsistencia entre los archivos adjuntos de la sección de tablero con la sección pki (datos faltantes)");
//		res.setStatus("fail");
		return true;
	}
	
	public int getNumFirmantesPendientesDeFirmarUnArchivoAdjunto(String hashDocumento) {
		List<PkiDocumentoFirmantes> faltantesFirmarArchivo = pkiDocumentoFirmantesRepository.findByHashDocumentoAndFechaFirmaAndIdFirmaAplicada(hashDocumento, null, null);
		return faltantesFirmarArchivo.size();
	}
	
	public boolean validateFirmanteHaFirmado(InstEmpleado empleado, PkiCatFirmaAplicada firmaAplicada) {
		List<PkiDocumentoFirmantes> faltantesFirmarArchivo = pkiDocumentoFirmantesRepository.findByIdNumEmpleadoAndIdFirmaAplicada(
				empleado, firmaAplicada);
		if(faltantesFirmarArchivo.size()>0) {
			return true;
		}
		return false;
	}
	

	public TabDocumentosAdjuntos storeDocumento(TabDocumentos documentoStored, String docBase64, String fileType,
			Integer numDocumento, DTOResponse res) {
		utils = new CertificateUtils();

		String fecha = utils.formatDate(new Date());
		String fileName = buildFileName(fecha, documentoStored.getIdTipoDocumento().getDescTipoDocumento(),
				documentoStored.getId(), numDocumento);

		try {
			TabDocumentosAdjuntos docAdjunto = new TabDocumentosAdjuntos();
			docAdjunto.setIdDocument(documentoStored);
			docAdjunto.setDocumentoPath(documentPath + "/" + fileName);
			docAdjunto.setDocumentoHash(utils.calcularSHA256(new String(docBase64)));
			docAdjunto.setDocumentoFiletype(fileType);
			docAdjunto.setFechaCarga(new Date());
			docAdjunto.setDocumentoBase64(docBase64);

			TabDocumentosAdjuntos docAdjuntoStored = tabDocumentosAdjuntosRepository.save(docAdjunto);
			return docAdjuntoStored;
		} catch (Exception e) {
			res.setMessage(e.toString());
			res.setStatus("fail");
			return null;
		}

	}
	
	public String getSequenceWorkflow(TabDocumentos documento, DTOResponse res) {
		List<TabDocumentoWorkflow> listWorkflow = tabDocumentoWorkflowRepository.findByIdDocumentOrderByWorkflowFecha(documento);
		String etapaSequence = "";
		for(TabDocumentoWorkflow workflow : listWorkflow) {
			etapaSequence+="-"+workflow.getIdEtapaDocumento().getDescetapa();
		}
		etapaSequence = etapaSequence.replaceFirst("-","");
		
//		if(etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion())) {
//			return etapaSequence;
//		}else {
//			res.setMessage("No se puede firmar, ya que existen etapas del documento no contempladas, contacte a su administrador");
//			res.setStatus("fail");
//			return null;
//		}
		return etapaSequence;
	}
	
	public TabDocumentoWorkflow storeWorkFlow(TabDocumentos documentoStored, TabCatEtapaDocumento etapaDoc,
			InstEmpleado empleado, DTOResponse res) {
		try {
			// Se guarda el wrokflow del documento
			TabDocumentoWorkflow docWorkflow = new TabDocumentoWorkflow();
			docWorkflow.setIdEtapaDocumento(etapaDoc);
			docWorkflow.setIdDocument(documentoStored);
			docWorkflow.setUltActualizacion(new Date());
			docWorkflow.setWorkflowFecha(new Date());
			docWorkflow.setWorkflowIdNumEmpleado(empleado);

			TabDocumentoWorkflow workflowStored = tabDocumentoWorkflowRepository.save(docWorkflow);
			return workflowStored;
			
		}catch(Exception e) {
			res.setMessage("No se pudo guardar el workflow del documento");
			res.setStatus("fail");
			return null;
		}
	}
	
	public List<TabDocsFirmantes> getTabDocsFirmantes(int idTabDocumento) {
		List<TabDocsFirmantes> listTabDocFirmantes = tabDocsFirmantesRepository.findByIdDocumento(idTabDocumento);
		InstEmpleado empleado = listTabDocFirmantes.get(0).getIntEmpleado();
		return listTabDocFirmantes;
	}

	public TabDocsFirmantes storeFirmante(TabDocumentos documentoStored, InstEmpleado empleadoFirmante,
			TabCatInstFirmantes instruccionFirmante, int secuencia, DTOResponse res) {
		try {
			TabDocsFirmantes firmante = new TabDocsFirmantes();
			firmante.setIdDocumento(documentoStored.getId());
			firmante.setIdNumEmpleado(empleadoFirmante.getId());
			firmante.setIdInstFirmante(instruccionFirmante);
			firmante.setSecuencia(secuencia);
			TabDocsFirmantes firmanteStored = tabDocsFirmantesRepository.save(firmante);
			return firmanteStored;
		} catch (Exception e) {
			res.setMessage("No se pudo almacenar al empleado firmante con id: " + empleadoFirmante.getId());
			res.setStatus("fail");
			return null;
		}
	}

	public TabDocDestinatarios storeDestinatario(TabDocumentos documentoStored, InstEmpleado empleadoDestinatario,
			TabCatInstDest instruccionDestinatario, DTOResponse res) {
		try {
			TabDocDestinatarios destinatario = new TabDocDestinatarios();
			destinatario.setIdDocumento(documentoStored.getId());
			destinatario.setIdNumEmpleado(empleadoDestinatario.getId());
			destinatario.setIdnstDestinatario(instruccionDestinatario);
			TabDocDestinatarios destinatarioStored = tabDocDestinatariosRepository.save(destinatario);
			return destinatarioStored;
		} catch (Exception e) {
			res.setMessage("No se pudo almacenar al empleado destinatario con id: " + empleadoDestinatario.getId());
			res.setStatus("fail");
			return null;
		}
	}

	public TabDocConfig storeTabDocConfig(List<mx.gob.tecdmx.firmapki.api.documento.DTOConfiguracion> list,
			TabDocumentos documentoStored, DTOResponse res) {
		TabDocConfig docConfig = new TabDocConfig();
		if (list.size() > 0) { 
			for (mx.gob.tecdmx.firmapki.api.documento.DTOConfiguracion configIndex : list) {
				if (!configIndex.isConfig()) {
					Optional<TabCatDocConfig> configExist = tabCatConfigDocumentoRepository
							.findByAtributo(configIndex.getAtributo());
					if (configExist.isPresent()) {
						docConfig.setIdDocumento(documentoStored.getId());
						docConfig.setIdDocConfig(configExist.get().getId());
						// guarda en la tabla de relacion doc y config
						TabDocConfig docConfigStored = tabConfigDocumentoRepository.save(docConfig);
						return docConfigStored;
					}
				}
			}
		}

		res.setMessage("No se pudo guardar la configuración");
		res.setStatus("fail");
		return null;
	}



	public PkiDocumento createPKIDocumento(String docBase64, InstEmpleado empleadoCreador,
			InstEmpleado empleadoEnvio, Date fechaEnvio, Date fechaCreacion, String statusDocumento,
			boolean isTerminado, boolean isEnOrden, DTOResponse res) {
		try {
			utils = new CertificateUtils();
			PkiDocumento documento = new PkiDocumento();
			documento.setHashDocumento(utils.calcularSHA256(new String(docBase64)));
			documento.setIdNumEmpleadoCreador(empleadoCreador);
			documento.setIdNumEmpleadoEnvio(empleadoEnvio);
			documento.setFechaEnvio(fechaEnvio);
			documento.setFechaCreacion(fechaCreacion);
			documento.setStatusDocumento(statusDocumento);//Determiné tomar el dato de la tabla: tab_cat_etapa_documento
			documento.setTerminado(isTerminado ? 1 : 0);
			documento.setOrden(isEnOrden ? 1 : 0);
			PkiDocumento documentoSaved = pkiDocumentoRepository.save(documento);
			return documentoSaved;
		} catch (Exception e) {
			res.setMessage(e.toString());
			res.setStatus("fail");
			return null;
		}
	}
	
	public PkiDocumentoFirmantes createPKIDocumentoFirmantes(String sha256, SegOrgUsuarios usuarioFirmante,
			InstEmpleado empleadoFirmante, int secuencia, Date fechaLimite,
			PkiCatTipoFirma tipoFirma, DTOResponse res) {

		try {
			utils = new CertificateUtils();
			
			HashDocumentoIdUsuarioIdTransaccionID idCompuesta = new HashDocumentoIdUsuarioIdTransaccionID();
			idCompuesta.setHashDocumento(sha256);
			idCompuesta.setIdUsuario(usuarioFirmante.getnIdUsuario());
			
			PkiDocumentoFirmantes firmantes = new PkiDocumentoFirmantes();
			firmantes.setHashDocumento(sha256);
			firmantes.setIdUsuario(usuarioFirmante.getnIdUsuario());
//			firmantes.setTransaccion(transaccion);//La transacción se agrega en otra etapa, por eso aquí no se incluye
			firmantes.setIdNumEmpleado(empleadoFirmante);
			firmantes.setSecuencia(secuencia);
			firmantes.setFechaLimite(fechaLimite);
//			firmantes.setFechaFirma(fechaFirma);// Esta fecha se agrega en otra etapa, por eso aquí no se incluye
			firmantes.setIdTipoFirma(tipoFirma);//Simple/Múltiple
//			firmantes.setIdFirmaAplicada(firmaAplicada); // Este se agrega en otra etapa, por eso aquí no se incluye
//			firmantes.setCadenaFirma(cadenaFirma); // Este se agrega en otra etapa, por eso aquí no se incluye
			pkiDocumentoFirmantesRepository.save(firmantes);
			return firmantes;
		} catch (Exception e) {
			res.setMessage(e.toString());
			res.setStatus("fail");
			return null;
		}
	}
	
	
	public TabDocumentos findTabDocumento(int idDocumento, DTOResponse res) {
		Optional<TabDocumentos> documento = tabDocumentosRepository.findById(idDocumento);
		if(!documento.isPresent()) {
			res.setMessage("El documento con id: "+idDocumento+" no existe en la BD");
			res.setStatus("fail");
			return null;
		}
		return documento.get();
	}
	

	public TabDocumentos storeTabDocumento(TabCatDestinoDocumento tipoDestino, TabCatTipoDocumento tipoDocumento,
			TabCatPrioridad prioridad, String folioEspecial, TabExpedientes numExpediente, String asunto, String notas,
			String contenido, Date fechaLimiteFirma, boolean isEnOrden, DTOResponseUserInfo userInfo, DTOResponse res) {

		try {
			TabDocumentos documento = new TabDocumentos();

			documento.setIdTipoDestino(tipoDestino);
			documento.setIdTipoDocumento(tipoDocumento);
			documento.setIdPrioridad(prioridad);

			documento.setFolioEspecial(folioEspecial);
			documento.setCreacionDocumentoFecha(new Date());
			documento.setIdNumEmpleadoCreador(userInfo.getData().getEmpleado());
			documento.setIdUsuarioCreador(userInfo.getData().getUser());
			documento.setNumExpediente(numExpediente);
			documento.setEnOrden(isEnOrden?1:0);
			documento.setAsunto(asunto);
			documento.setNotas(notas);
			documento.setContenido(contenido);
			documento.setFechaLimiteFirma(fechaLimiteFirma);
			documento.setHashDocumento(null);// Quitar de la base de datos este campo
			Optional<TabDocumentos> docChain = tabDocumentosRepository.findTopByOrderByIdDesc();

			TabDocumentos documentoStored = null;
			if (!docChain.isPresent()) {
				documentoStored = tabDocumentosRepository.save(documento);
				documento.setChainIdDocument(documentoStored);
				documento.setFolioDocumento(documentoStored.getId());
				documentoStored = tabDocumentosRepository.save(documento);
			} else {
				documento.setChainIdDocument(docChain.get());
				documento.setFolioDocumento(docChain.get().getId() + 1);
				documentoStored = tabDocumentosRepository.save(documento);
			}
			return documentoStored;
		} catch (Exception e) {
			res.setMessage("" + e);
			res.setStatus("fail");
			return null;
		}

	}

}
