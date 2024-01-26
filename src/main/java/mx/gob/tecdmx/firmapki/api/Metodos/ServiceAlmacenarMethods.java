package mx.gob.tecdmx.firmapki.api.Metodos;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.documento2.DTODocAdjunto;
import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.api.firma.DAOFirmanteDestinatario;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumento;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocsFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentoWorkflowRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.GenerateNumOficioRandomUtils;

@Service
public class ServiceAlmacenarMethods {
	
	@Autowired
	TabDocumentoWorkflowRepository tabDocumentoWorkflowRepository;
	
	@Autowired
	TabDocumentosRepository tabDocumentosRepository;
	
	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;
	
	@Autowired
	TabDocsFirmantesRepository tabDocsFirmantesRepository;
	
	@Autowired
	PkiDocumentoRepository pkiDocumentoRepository;
	
	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;
	
	@Autowired
	InstEmpleadoRepository instEmpleadoRepository;
	
	@Value("${firma.document.pdf.path}")
	private String documentPath;
	
	CertificateUtils utils = null;
	
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

		} catch (Exception e) {
			res.setMessage("No se pudo guardar el workflow del documento");
			res.setStatus("fail");
			return null;
		}
	}
	
	public TabDocumentos storeTabDocumento(TabCatDestinoDocumento tipoDestino, TabCatTipoDocumento tipoDocumento,
			TabCatPrioridad prioridad, String folioEspecial, TabExpedientes numExpediente, String asunto, String notas,
			String contenido, Date fechaLimiteFirma, boolean isEnOrden, DTOResponseUserInfo userInfo, DTOResponse res, 
			List<mx.gob.tecdmx.firmapki.api.documento2.DTOConfiguracion> lisConfig) {
		
		GenerateNumOficioRandomUtils methodRandomUtils = new GenerateNumOficioRandomUtils();
		for(mx.gob.tecdmx.firmapki.api.documento2.DTOConfiguracion config : lisConfig) {
			if (config.getAtributo().equals("GNUMOF")) {
				folioEspecial= methodRandomUtils.generateRandomString();
				break;
			}
		}
		
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
			documento.setEnOrden(isEnOrden ? 1 : 0);
			documento.setAsunto(asunto);
			documento.setNotas(notas);
			documento.setContenido(contenido);
			documento.setFechaLimiteFirma(fechaLimiteFirma);
			documento.setHashDocumento(null);// Quitar de la base de datos este campo
			documento.setVisible(true);
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
	
	public boolean storeDocumentosAdjuntosDesktop(List<DTODocAdjunto> documentosAdjuntos, TabDocumentos documentoStored,
			DTOResponse res, DTOResponseUserInfo userInfo) {
		Integer numDocumento = 1;
		for (DTODocAdjunto docAdjuntoPayload : documentosAdjuntos) {
			TabDocumentosAdjuntos docAdjuntoStored = storeDocumento("escritorio", documentoStored,
					docAdjuntoPayload.getDocBase64(), docAdjuntoPayload.getFileType(), numDocumento, res);
			if (docAdjuntoStored == null) {
				return false;
			}
			numDocumento++;
		}
		return true;
	}
	
	public String buildFileName(String fecha, String tipoDocumento, int documentoId, int numDocumento) {
		String fileName = fecha + "_" + tipoDocumento + "_" + documentoId + "_" + numDocumento + ".pdf";
		return fileName;
	}
	
	public TabDocumentosAdjuntos storeDocumento(String escritorio, TabDocumentos documentoStored, String docBase64,
			String fileType,
			Integer numDocumento, DTOResponse res) {
		utils = new CertificateUtils();

		String fecha = utils.formatDate(new Date());
		String fileName = buildFileName(fecha, escritorio,
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
	
	public boolean storeFirmantes(TabDocumentos documentoStored, DAOAltaDocumento documentoAlta, DTOResponse res) {

		Collections.sort(documentoAlta.getFirmantes(),
				(o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

		// Se almacenan los firmantes
		for (DAOFirmanteDestinatario firmante : documentoAlta.getFirmantes()) {
			TabDocsFirmantes firmanteStored = storeFirmante(documentoStored, firmante.getEmpleado(),
					firmante.getInstruccionFirmante(), firmante.getSecuencia(), res);
			if (firmanteStored == null) {
				return false;
			}
		}
		return true;
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
	
	public boolean storePkiDocumento(List<TabDocumentosAdjuntos> documentosAdjuntos, String status, boolean isEnOrden,
			InstEmpleado empleadoCreador, InstEmpleado empleadoEnvio, Date fechaCreacion, Date fechaEnvio,
			DTOResponse res) {

		for (TabDocumentosAdjuntos docAdjunto : documentosAdjuntos) {

			PkiDocumento pkiDocStored = createPKIDocumento(docAdjunto.getDocumentoBase64(),
					empleadoCreador, empleadoEnvio, fechaEnvio, fechaCreacion, status, false, isEnOrden, res);

			if (pkiDocStored == null) {
				return false;
			}
		}
		return true;
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
			documento.setStatusDocumento(statusDocumento);// Determiné tomar el dato de la tabla:
															// tab_cat_etapa_documento
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

	public boolean storePkiDocumentoFirmantes(List<TabDocumentosAdjuntos> documentosAdjuntos,
			TabDocumentos documentoStored, PkiCatTipoFirma tipoFirma, TabDocsFirmantes currentFirmante,
			DTOResponse res) {
		Optional<InstEmpleado> empleado = null;

		for (TabDocumentosAdjuntos docAdjunto : documentosAdjuntos) {

			empleado = instEmpleadoRepository.findById(currentFirmante.getIdNumEmpleado());

			PkiDocumentoFirmantes documentoFirmantesStored = createPKIDocumentoFirmantes(
					docAdjunto.getDocumentoHash(), empleado.get().getIdUsuario(), empleado.get(),
					currentFirmante.getSecuencia(), documentoStored.getFechaLimiteFirma(), tipoFirma, res);

			if (documentoFirmantesStored == null) {
				return false;
			}
		}
		return true;
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
			// firmantes.setTransaccion(transaccion);//La transacción se agrega en otra
			// etapa, por eso aquí no se incluye
			firmantes.setIdNumEmpleado(empleadoFirmante);
			firmantes.setSecuencia(secuencia);
			firmantes.setFechaLimite(fechaLimite);
			// firmantes.setFechaFirma(fechaFirma);// Esta fecha se agrega en otra etapa,
			// por eso aquí no se incluye
			firmantes.setIdTipoFirma(tipoFirma);// Simple/Múltiple
			// firmantes.setIdFirmaAplicada(firmaAplicada); // Este se agrega en otra etapa,
			// por eso aquí no se incluye
			// firmantes.setCadenaFirma(cadenaFirma); // Este se agrega en otra etapa, por
			// eso aquí no se incluye
			pkiDocumentoFirmantesRepository.save(firmantes);
			return firmantes;
		} catch (Exception e) {
			res.setMessage(e.toString());
			res.setStatus("fail");
			return null;
		}
	}
}
