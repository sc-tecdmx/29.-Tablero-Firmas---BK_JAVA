package mx.gob.tecdmx.firmapki.api.documento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.firma.ServiceFirmar;
import mx.gob.tecdmx.firmapki.api.firma.ServiceFirmarAhora;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatInstruccionDoc;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumento;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoDestino;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.pki.PkiTransaccion;
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
import mx.gob.tecdmx.firmapki.entity.tab.VistaTablero;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatFirmaAplicadaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatTipoFirmaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoDestinoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiTransaccionRepository;
import mx.gob.tecdmx.firmapki.repository.seg.SegOrgUsuariosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatDestinoDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatDocConfigRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatEtapaDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatInstDestRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatInstFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatPrioridadRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatTipoDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocConfigRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocDestinatariosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocsFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentoWorkflowRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabExpedientesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.VistaTableroRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceDocumento {

	@Autowired
	PkiDocumentoRepository pkiDocumentoRepository;

	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;

	@Autowired
	PkiDocumentoDestinoRepository pkiDocumentoDestRepository;

	@Autowired
	PkiTransaccionRepository pkiTransaccionRepository;

	@Autowired
	PkiCatTipoFirmaRepository pkiCatTipoFirmaRepository;

	@Autowired
	PkiCatFirmaAplicadaRepository pkiCatFirmaAplicadaRepository;

	@Autowired
	TabExpedientesRepository tabExpedientesRepository;

	@Autowired
	TabCatTipoDocumentoRepository tabCatTipoDocumentoRepository;

	@Autowired
	TabCatDestinoDocumentoRepository tabCatDestinoDocumentoRepository;

	@Autowired
	TabCatPrioridadRepository tabCatPrioridadRepository;

	@Autowired
	TabCatInstFirmantesRepository tabCatInstFirmantesRepository;

	@Autowired
	TabCatInstDestRepository tabCatInstDestRepository;

	@Autowired
	TabCatEtapaDocumentoRepository tabCatEtapaDocumentoRepository;

	@Autowired
	TabDocumentosRepository tabDocumentosRepository;

	@Autowired
	TabDocumentoWorkflowRepository tabDocumentoWorkflowRepository;

	@Autowired
	TabDocsFirmantesRepository tabDocsFirmantesRepository;

	@Autowired
	TabDocDestinatariosRepository tabDocDestinatariosRepository;

	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;

	@Autowired
	InstEmpleadoRepository instEmpleadoRepository;

	@Autowired
	SegOrgUsuariosRepository segOrgUsuariosRepository;

	@Autowired
	VistaTableroRepository vistaTableroRepository;

	@Autowired
	TabDocConfigRepository tabConfigDocumentoRepository;

	@Autowired
	TabCatDocConfigRepository tabCatConfigDocumentoRepository;

	@Autowired
	ServiceFirma serviceFirma;
	
	@Autowired
	ServiceFirmar serviceFirmar;
	
	@Autowired
	ServiceFirmarAhora serviceFirmarAhora;

	@Value("${firma.document.pdf.path}")
	private String documentPath;

	@Value("${firma.document.pdf.firmados.path}")
	private String documentFirmadosPath;

	@Value("${firma.document.encryption}")
	private String encryptionAlgorithm;

	public boolean validateCatalogos(Optional<TabCatTipoDocumento> tipoDoc, Optional<TabCatDestinoDocumento> tipoDest,
			Optional<TabCatPrioridad> tipoPrioridad, Optional<TabCatEtapaDocumento> etapaDoc, DTOResponse res) {

		res.setStatus("fail");
		if (!tipoDoc.isPresent()) {
			res.setMessage("No se puede encontrar el tipo de documento");
			return false;
		} else if (!tipoDest.isPresent()) {
			res.setMessage("No se puede encontrar el tipo de destino");
			return false;
		} else if (!tipoPrioridad.isPresent()) {
			res.setMessage("No se puede encontrar el tipo de prioridad");
			return false;
		} else if (!etapaDoc.isPresent()) {
			res.setMessage("No se puede encontrar la etapa del documento");
			return false;
		}

		res.setMessage(null);
		res.setStatus(null);
		return true;
	}

	public DTOResponse altaDocumentov2(PayloadAltaDocumento payload, DTOResponse res, DTOResponseUserInfo userInfo) {
		CertificateUtils utils = new CertificateUtils();
		Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(userInfo.getData().getIdEmpleado());
		if (!empleado.isPresent()) {
			res.setMessage("No se puede encontrar el empleado");
			res.setStatus("fail");
			return res;
		}

		Optional<SegOrgUsuarios> usuario = segOrgUsuariosRepository.findById(userInfo.getData().getIdUsuario());
		if (!usuario.isPresent()) {
			res.setMessage("No se puede encontrar el usuario");
			res.setStatus("fail");
			return res;
		}
		Optional<TabExpedientes> numExpediente = null;
		if (payload.getNumExpediente() != null) {
			numExpediente = tabExpedientesRepository.findByNumExpediente(payload.getNumExpediente());
			if (!numExpediente.isPresent()) {
				res.setMessage("No se puede encontrar el número de expediente");
				res.setStatus("fail");
				return res;
			}
		}

		// Se buscan los catálogos que entran para verificar que existen
		Optional<TabCatTipoDocumento> tipoDoc = tabCatTipoDocumentoRepository
				.findByDescTipoDocumento(payload.getTipoDocumento());
		Optional<TabCatDestinoDocumento> tipoDest = tabCatDestinoDocumentoRepository
				.findByDescDestinoDocumento(payload.getTipoDestino());
		Optional<TabCatPrioridad> tipoPrioridad = tabCatPrioridadRepository
				.findByDescPrioridad(payload.getTipoPrioridad());
		Optional<TabCatEtapaDocumento> etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa("Creado");
		// descomentar estas lineas si estos catálogos son requeridos
		/*
		 * boolean validCatalogos = validateCatalogos(tipoDoc, tipoDest, tipoPrioridad,
		 * etapaDoc, res); if (!validCatalogos) { return res; }
		 */

		// Se crea el registro en la tabla: tab_documentos
		TabDocumentos documento = new TabDocumentos();
		if (tipoDest.isPresent()) {
			documento.setIdTipoDestino(tipoDest.get());
		}
		if (tipoDoc.isPresent()) {
			documento.setIdTipoDocumento(tipoDoc.get());
		}
		if (tipoPrioridad.isPresent()) {
			documento.setIdPrioridad(tipoPrioridad.get());
		}

//		documento.setFolioDocumento(payload.getFolio());
		documento.setFolioEspecial(payload.getFolioEspecial());
		documento.setCreacionDocumentoFecha(new Date());
		documento.setIdNumEmpleadoCreador(empleado.get());
		documento.setIdUsuarioCreador(usuario.get());
		if (numExpediente != null) {
			documento.setNumExpediente(numExpediente.get());
		}

		documento.setAsunto(payload.getAsunto());
		documento.setNotas(payload.getNotas());
		documento.setContenido(payload.getContenido());
		documento.setFechaLimiteFirma(payload.getFechaLimiteFirma());
		documento.setHashDocumento(null);
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

		// guarda la configuracion

		TabDocConfig docConfig = new TabDocConfig();
		if (payload.getConfiguraciones().size() > 0) {
			for (DTOConfiguracion configIndex : payload.getConfiguraciones()) {
				if (configIndex.config) {
					Optional<TabCatDocConfig> configExist = tabCatConfigDocumentoRepository
							.findByAtributo(configIndex.getAtributo());
					if (configExist.isPresent()) {
						docConfig.setIdDocumento(documentoStored.getId());
						docConfig.setIdDocConfig(configExist.get().getId());
						tabConfigDocumentoRepository.save(docConfig); // guarda en la tabla de relacion doc y config
					}
				}

			}

		}

		if (documentoStored != null) {
			TabDocumentosAdjuntos docAdjunto = null;
			// Se guardan los documentos adjuntos
			int numDocumento = 1;
			for (DTODocAdjunto docAdjuntoPayload : payload.getDocumentosAdjuntos()) {
				String fileName = utils.formatDate(new Date()) + "_"
						+ documentoStored.getIdTipoDocumento().getDescTipoDocumento() + "_" + documentoStored.getId()
						+ "_" + numDocumento + ".pdf";
				utils.storeBase64ToFile(docAdjuntoPayload.getDocBase64(), documentPath + "/" + fileName);

				docAdjunto = new TabDocumentosAdjuntos();
				docAdjunto.setIdDocument(documentoStored);
				docAdjunto.setDocumentoPath(documentPath + "/" + fileName);
				docAdjunto.setDocumentoHash(utils.calcularSHA256(new String(docAdjuntoPayload.getDocBase64())));
				docAdjunto.setDocumentoFiletype(docAdjuntoPayload.getFileType());
				docAdjunto.setFechaCarga(new Date());
				docAdjunto.setDocumentoBase64(docAdjuntoPayload.getDocBase64());

				TabDocumentosAdjuntos docAdjuntoStored = tabDocumentosAdjuntosRepository.save(docAdjunto);
				numDocumento++;
			}

			// Se guardan los firmantes
			if (payload.getFirmantes().size() > 0) {
				for (DTOFirmanteDestinatario firmantePayload : payload.getFirmantes()) {

					Optional<InstEmpleado> empleadoFirmante = instEmpleadoRepository
							.findById(firmantePayload.getIdEmpleado());
					if (!empleadoFirmante.isPresent()) {
						res.setMessage("No se encontró el empleado firmante con Id" + firmantePayload.getIdEmpleado());
						res.setStatus("fail");
						return res;
					}
					Optional<TabCatInstFirmantes> instruccionFirmante = tabCatInstFirmantesRepository
							.findByDescInstrFirmante(firmantePayload.getInstruccion());
					if (!instruccionFirmante.isPresent()) {
						res.setMessage(
								"No se encontró la instrucción del firmante con Id" + firmantePayload.getIdEmpleado());
						res.setStatus("fail");
						return res;
					}

					TabDocsFirmantes firmante = new TabDocsFirmantes();
					firmante.setIdDocumento(documentoStored.getId());
					firmante.setIdNumEmpleado(empleadoFirmante.get().getId());
					firmante.setIdInstFirmante(instruccionFirmante.get());
					tabDocsFirmantesRepository.save(firmante);
				}
			}

			if (payload.getDestinatarios().size() > 0) {
				for (DTOFirmanteDestinatario destinatarioPayload : payload.getDestinatarios()) {
					Optional<InstEmpleado> empleadoDestinatario = instEmpleadoRepository
							.findById(destinatarioPayload.getIdEmpleado());
					if (!empleadoDestinatario.isPresent()) {
						res.setMessage(
								"No se encontró el empleado destinatario con Id" + destinatarioPayload.getIdEmpleado());
						res.setStatus("fail");
						return res;
					}
					Optional<TabCatInstDest> instruccionDestinatario = tabCatInstDestRepository
							.findByDescInsDest(destinatarioPayload.getInstruccion());
					if (!instruccionDestinatario.isPresent()) {
						res.setMessage("No se encontró la instrucción del destinatario con Id"
								+ destinatarioPayload.getIdEmpleado());
						res.setStatus("fail");
						return res;
					}
					TabDocDestinatarios destinatario = new TabDocDestinatarios();
					destinatario.setIdDocumento(documentoStored.getId());
					destinatario.setIdNumEmpleado(empleadoDestinatario.get().getId());
					destinatario.setIdnstDestinatario(instruccionDestinatario.get());
					tabDocDestinatariosRepository.save(destinatario);
				}
			}

			// Se guarda el wrokflow del documento
			TabDocumentoWorkflow docWorkflow = new TabDocumentoWorkflow();
			docWorkflow.setIdEtapaDocumento(etapaDoc.get());
			docWorkflow.setIdDocument(documentoStored);
			docWorkflow.setUltActualizacion(new Date());
			docWorkflow.setWorkflowFecha(new Date());
			docWorkflow.setWorkflowIdNumEmpleado(empleado.get());

			tabDocumentoWorkflowRepository.save(docWorkflow);
		}

		res.setMessage("Se ha guardado el documento satisfactoriamente");
		res.setStatus("Success");
		payload.setFolio(documentoStored.getFolioDocumento());
		res.setData(payload);

		return res;
	}
	



	public DTOResponse altaDocumentoFirmarAhora(PayloadAltaDocumento payload, DTOResponse res,
			DTOResponseUserInfo userInfo) {
		CertificateUtils utils = new CertificateUtils();
		Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(userInfo.getData().getIdEmpleado());
		if (!empleado.isPresent()) {
			res.setMessage("No se puede encontrar el empleado");
			res.setStatus("fail");
			return res;
		}

		Optional<SegOrgUsuarios> usuario = segOrgUsuariosRepository.findById(userInfo.getData().getIdUsuario());
		if (!usuario.isPresent()) {
			res.setMessage("No se puede encontrar el usuario");
			res.setStatus("fail");
			return res;
		}
		Optional<TabExpedientes> numExpediente = null;
		if (payload.getNumExpediente() != null) {
			numExpediente = tabExpedientesRepository.findByNumExpediente(payload.getNumExpediente());
			if (!numExpediente.isPresent()) {
				res.setMessage("No se puede encontrar el número de expediente");
				res.setStatus("fail");
				return res;
			}
		}
		if (payload.getFirmantes().size() < 1) {
			res.setMessage("No formas parte de los usuarios asignados como firmantes en el documento");
			res.setStatus("fail");
			return res;

		}

		// Se buscan los catálogos que entran para verificar que existen
		Optional<TabCatTipoDocumento> tipoDoc = tabCatTipoDocumentoRepository
				.findByDescTipoDocumento(payload.getTipoDocumento());
		Optional<TabCatDestinoDocumento> tipoDest = tabCatDestinoDocumentoRepository
				.findByDescDestinoDocumento(payload.getTipoDestino());
		Optional<TabCatPrioridad> tipoPrioridad = tabCatPrioridadRepository
				.findByDescPrioridad(payload.getTipoPrioridad());
		Optional<TabCatEtapaDocumento> etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa("En Firma");
		// descomentar estas lineas si estos catálogos son requeridos
		/*
		 * boolean validCatalogos = validateCatalogos(tipoDoc, tipoDest, tipoPrioridad,
		 * etapaDoc, res); if (!validCatalogos) { return res; }
		 */

		// Se crea el registro en la tabla: tab_documentos
		TabDocumentos documento = new TabDocumentos();
		if (tipoDest.isPresent()) {
			documento.setIdTipoDestino(tipoDest.get());
		}
		if (tipoDoc.isPresent()) {
			documento.setIdTipoDocumento(tipoDoc.get());
		}
		if (tipoPrioridad.isPresent()) {
			documento.setIdPrioridad(tipoPrioridad.get());
		}

//		documento.setFolioDocumento(payload.getFolio());
		documento.setFolioEspecial(payload.getFolioEspecial());
		documento.setCreacionDocumentoFecha(new Date());
		documento.setIdNumEmpleadoCreador(empleado.get());
		documento.setIdUsuarioCreador(usuario.get());
		if (numExpediente != null) {
			documento.setNumExpediente(numExpediente.get());
		}

		documento.setAsunto(payload.getAsunto());
		documento.setNotas(payload.getNotas());
		documento.setContenido(payload.getContenido());
		documento.setFechaLimiteFirma(payload.getFechaLimiteFirma());
		documento.setHashDocumento(null);
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

		// guarda la configuracion

		TabDocConfig docConfig = new TabDocConfig();
		if (payload.getConfiguraciones().size() > 0) {
			for (DTOConfiguracion configIndex : payload.getConfiguraciones()) {
				if (configIndex.config) {
					Optional<TabCatDocConfig> configExist = tabCatConfigDocumentoRepository
							.findByAtributo(configIndex.getAtributo());
					if (configExist.isPresent()) {
						docConfig.setIdDocumento(documentoStored.getId());
						docConfig.setIdDocConfig(configExist.get().getId());
						tabConfigDocumentoRepository.save(docConfig); // guarda en la tabla de relacion doc y config
					}
				}

			}

		}
		PkiDocumento pkiDocumentoStored = null;
		if (documentoStored != null) {
			TabDocumentosAdjuntos docAdjunto = null;
			// Se guardan los documentos adjuntos
			int numDocumento = 1;
			for (DTODocAdjunto docAdjuntoPayload : payload.getDocumentosAdjuntos()) {
				String fileName = utils.formatDate(new Date()) + "_"
						+ documentoStored.getIdTipoDocumento().getDescTipoDocumento() + "_" + documentoStored.getId()
						+ "_" + numDocumento + ".pdf";
				utils.storeBase64ToFile(docAdjuntoPayload.getDocBase64(), documentPath + "/" + fileName);

				docAdjunto = new TabDocumentosAdjuntos();
				docAdjunto.setIdDocument(documentoStored);
				docAdjunto.setDocumentoPath(documentPath + "/" + fileName);
				docAdjunto.setDocumentoHash(utils.calcularSHA256(new String(docAdjuntoPayload.getDocBase64())));
				docAdjunto.setDocumentoFiletype(docAdjuntoPayload.getFileType());
				docAdjunto.setFechaCarga(new Date());
				docAdjunto.setDocumentoBase64(docAdjuntoPayload.getDocBase64());

				TabDocumentosAdjuntos docAdjuntoStored = tabDocumentosAdjuntosRepository.save(docAdjunto);

				if (payload.getFirmantes().size() == 1) {
					etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa("Terminado");
				} 
				pkiDocumentoStored = createPKIDocumento(docAdjuntoStored.getDocumentoHash(), empleado.get(),
						empleado.get(), null, documentoStored.getCreacionDocumentoFecha(),
						etapaDoc.get().getDescetapa(), false, payload.isEnOrden());

				numDocumento++;
			}

			// Se guardan los firmantes

			DTOFirmanteDestinatario firmanteUsuario = new DTOFirmanteDestinatario();
			firmanteUsuario.setIdEmpleado(userInfo.getData().getIdEmpleado());
			firmanteUsuario.setInstruccion("Firma");
			firmanteUsuario.setSecuencia(1);
			List<DTOFirmanteDestinatario> firmantesNewList = new ArrayList<DTOFirmanteDestinatario>();

			// Buscamos si el que creó el documento y firma, se asignó así mismo como
			// firmante, si no lo agregamos
			boolean isInFirmantes = false;
			for (DTOFirmanteDestinatario firmantePayload : payload.getFirmantes()) {
				if (firmantePayload.getIdEmpleado() == firmanteUsuario.getIdEmpleado()) {
					isInFirmantes = true;
				}
			}
			if (!isInFirmantes) {

				firmantesNewList.add(firmanteUsuario);

				for (DTOFirmanteDestinatario firmantePayload : payload.getFirmantes()) {
					firmantePayload.setSecuencia(firmantePayload.getSecuencia() + 1);
					firmantesNewList.add(firmantePayload);
				}
			} else {
				firmantesNewList = payload.getFirmantes();
			}

			for (DTOFirmanteDestinatario firmantePayload : firmantesNewList) {

				Optional<InstEmpleado> empleadoFirmante = instEmpleadoRepository
						.findById(firmantePayload.getIdEmpleado());
				if (!empleadoFirmante.isPresent()) {
					res.setMessage("No se encontró el empleado firmante con Id" + firmantePayload.getIdEmpleado());
					res.setStatus("fail");
					return res;
				}
				Optional<TabCatInstFirmantes> instruccionFirmante = tabCatInstFirmantesRepository
						.findByDescInstrFirmante(firmantePayload.getInstruccion());
				if (!instruccionFirmante.isPresent()) {
					res.setMessage(
							"No se encontró la instrucción del firmante con Id" + firmantePayload.getIdEmpleado());
					res.setStatus("fail");
					return res;
				}

				TabDocsFirmantes firmante = new TabDocsFirmantes();
				firmante.setIdDocumento(documentoStored.getId());
				firmante.setIdNumEmpleado(empleadoFirmante.get().getId());
				firmante.setIdInstFirmante(instruccionFirmante.get());
				tabDocsFirmantesRepository.save(firmante);
			}

			String tipofirma = payload.getDocumentosAdjuntos().size() > 1 ? "Múltiple" : "Simple";

			Optional<PkiCatTipoFirma> tipoFirma = pkiCatTipoFirmaRepository.findByDescTipoFirma(tipofirma);
			if (!tipoFirma.isPresent()) {
				res.setMessage("No se encontró el tipo de firma del firmante con Id" + firmanteUsuario.getIdEmpleado());
				res.setStatus("fail");
				return res;
			}

			Optional<InstEmpleado> empleadoFirmante = instEmpleadoRepository
					.findById(userInfo.getData().getIdEmpleado());
			if (!empleadoFirmante.isPresent()) {
				res.setMessage("No se encontró el empleado firmante con Id" + userInfo.getData().getIdEmpleado());
				res.setStatus("fail");
				return res;
			}

			Optional<PkiCatFirmaAplicada> firmaAplicada = pkiCatFirmaAplicadaRepository
					.findByDescFirmaAplicada("Firmado");

			PkiDocumentoFirmantes documentoFirmantesStored = createPKIDocumentoFirmantes(
					pkiDocumentoStored.getHashDocumento(), usuario.get(), null, empleadoFirmante.get(),
					firmanteUsuario.getSecuencia(), payload.getFechaLimiteFirma(), null, tipoFirma.get(),
					firmaAplicada.get(), null);

			if (payload.getDestinatarios().size() > 0) {
				for (DTOFirmanteDestinatario destinatarioPayload : payload.getDestinatarios()) {
					Optional<InstEmpleado> empleadoDestinatario = instEmpleadoRepository
							.findById(destinatarioPayload.getIdEmpleado());
					if (!empleadoDestinatario.isPresent()) {
						res.setMessage(
								"No se encontró el empleado destinatario con Id" + destinatarioPayload.getIdEmpleado());
						res.setStatus("fail");
						return res;
					}
					Optional<TabCatInstDest> instruccionDestinatario = tabCatInstDestRepository
							.findByDescInsDest(destinatarioPayload.getInstruccion());
					if (!instruccionDestinatario.isPresent()) {
						res.setMessage("No se encontró la instrucción del destinatario con Id"
								+ destinatarioPayload.getIdEmpleado());
						res.setStatus("fail");
						return res;
					}
					TabDocDestinatarios destinatario = new TabDocDestinatarios();
					destinatario.setIdDocumento(documentoStored.getId());
					destinatario.setIdNumEmpleado(empleadoDestinatario.get().getId());
					destinatario.setIdnstDestinatario(instruccionDestinatario.get());
					tabDocDestinatariosRepository.save(destinatario);
				}
			}

			// Se guarda el wrokflow del documento
			TabDocumentoWorkflow docWorkflow = new TabDocumentoWorkflow();
			docWorkflow.setIdEtapaDocumento(etapaDoc.get());
			docWorkflow.setIdDocument(documentoStored);
			docWorkflow.setUltActualizacion(new Date());
			docWorkflow.setWorkflowFecha(new Date());
			docWorkflow.setWorkflowIdNumEmpleado(empleado.get());

			tabDocumentoWorkflowRepository.save(docWorkflow);
		}

		res.setMessage("Se ha guardado el documento satisfactoriamente");
		res.setStatus("Success");
		payload.setFolio(documentoStored.getFolioDocumento());
		res.setData(payload);

		return res;
	}

	public DTOResponse altaDocumento(PayloadAltaDocumento payload, DTOResponse res, DTOResponseUserInfo userInfo) {
		CertificateUtils utils = new CertificateUtils();
		Optional<SegOrgUsuarios> usuario = segOrgUsuariosRepository.findById(userInfo.getData().getIdUsuario());
		if (!usuario.isPresent()) {
			res.setMessage("No se puede encontrar el usuario");
			res.setStatus("fail");
			return res;
		}

		Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(userInfo.getData().getIdEmpleado());
		if (!empleado.isPresent()) {
			res.setMessage("No se puede encontrar el empleado");
			res.setStatus("fail");
			return res;
		}
		// Se crea el expediente para que pueda ser asociado
		// Dudas: 1. Se guarda aquí o ya tiene que existir el expediente para que se
		// busque y asocie??
		Optional<TabExpedientes> numExpediente = tabExpedientesRepository
				.findByNumExpediente(payload.getNumExpediente());
		TabExpedientes expedienteStored = null;
		if (!numExpediente.isPresent()) {
			TabExpedientes expediente = new TabExpedientes();
			expediente.setNumExpediente(payload.getNumExpediente());
			expediente.setDescripcion(null);
			expediente.setIdUsuarioCreador(usuario.get());
			expedienteStored = tabExpedientesRepository.save(expediente);
		} else {
			expedienteStored = numExpediente.get();
		}

		// Se buscan los catálogos que entran para verificar que existen
		Optional<TabCatTipoDocumento> tipoDoc = tabCatTipoDocumentoRepository
				.findByDescTipoDocumento(payload.getTipoDocumento());
		Optional<TabCatDestinoDocumento> tipoDest = tabCatDestinoDocumentoRepository
				.findByDescDestinoDocumento(payload.getTipoDestino());
		Optional<TabCatPrioridad> tipoPrioridad = tabCatPrioridadRepository
				.findByDescPrioridad(payload.getTipoPrioridad());
		Optional<TabCatEtapaDocumento> etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa("Creado");

		boolean validCatalogos = validateCatalogos(tipoDoc, tipoDest, tipoPrioridad, etapaDoc, res);
		if (!validCatalogos) {
			return res;
		}

		// Se crea el registro en la tabla: tab_documentos
		TabDocumentos documento = new TabDocumentos();
		documento.setIdTipoDestino(tipoDest.get());
		documento.setIdTipoDocumento(tipoDoc.get());
//		documento.setFolioDocumento(payload.getFolio());
		documento.setFolioEspecial(payload.getFolioEspecial());
		documento.setCreacionDocumentoFecha(new Date());
		documento.setIdNumEmpleadoCreador(empleado.get());
		documento.setIdUsuarioCreador(usuario.get());
		documento.setNumExpediente(expedienteStored);
		documento.setIdPrioridad(tipoPrioridad.get());
		documento.setAsunto(payload.getAsunto());
		documento.setContenido(payload.getContenido());
		documento.setFechaLimiteFirma(payload.getFechaLimiteFirma());
		documento.setHashDocumento(null);
		Optional<TabDocumentos> docChain = tabDocumentosRepository.findTopByOrderByIdDesc();

		TabDocumentos documentoStored = null;
		if (!docChain.isPresent()) {
			documentoStored = tabDocumentosRepository.save(documento);
			documento.setChainIdDocument(documentoStored);
			documentoStored = tabDocumentosRepository.save(documento);
		} else {
			documento.setChainIdDocument(docChain.get());
			documentoStored = tabDocumentosRepository.save(documento);
		}

		PkiDocumento pkiDocumentoStored = null;
		// Si se creó el registro continuamos creando los registros:
		// documentos-adjuntos, firmantes y destinatarios
		if (documentoStored != null) {
			TabDocumentosAdjuntos docAdjunto = null;
			// Se guardan los documentos adjuntos
			for (DTODocAdjunto docAdjuntoPayload : payload.getDocumentosAdjuntos()) {
				String fileName = utils.formatDate(new Date()) + "_"
						+ documentoStored.getIdTipoDocumento().getDescTipoDocumento() + "_" + documentoStored.getId()
						+ ".pdf";
				utils.storeBase64ToFile(docAdjuntoPayload.getDocBase64(), documentPath + "/" + fileName);

				docAdjunto = new TabDocumentosAdjuntos();
				docAdjunto.setIdDocument(documentoStored);
				docAdjunto.setDocumentoPath(documentPath + "/" + fileName);
				docAdjunto.setDocumentoHash(utils.calcularSHA256(new String(docAdjuntoPayload.getDocBase64())));
				docAdjunto.setDocumentoFiletype(docAdjuntoPayload.getFileType());
				docAdjunto.setFechaCarga(new Date());
				docAdjunto.setDocumentoBase64(docAdjuntoPayload.getDocBase64());

				TabDocumentosAdjuntos docAdjuntoStored = tabDocumentosAdjuntosRepository.save(docAdjunto);

				// Continuamos con los registros de las tablas PKI
				pkiDocumentoStored = createPKIDocumento(docAdjuntoStored.getDocumentoHash(), empleado.get(),
						empleado.get(), null, documentoStored.getCreacionDocumentoFecha(), "Creado", false,
						payload.isEnOrden());
			}

			// Se guardan los firmantes
			for (DTOFirmanteDestinatario firmantePayload : payload.getFirmantes()) {
				Optional<InstEmpleado> empleadoFirmante = instEmpleadoRepository
						.findById(firmantePayload.getIdEmpleado());
				if (!empleadoFirmante.isPresent()) {
					res.setMessage("No se encontró el empleado firmante con Id" + firmantePayload.getIdEmpleado());
					res.setStatus("fail");
					return res;
				}
				Optional<TabCatInstFirmantes> instruccionFirmante = tabCatInstFirmantesRepository
						.findByDescInstrFirmante(firmantePayload.getInstruccion());
				if (!instruccionFirmante.isPresent()) {
					res.setMessage(
							"No se encontró la instrucción del firmante con Id" + firmantePayload.getIdEmpleado());
					res.setStatus("fail");
					return res;
				}

				TabDocsFirmantes firmante = new TabDocsFirmantes();
				firmante.setIdDocumento(documentoStored.getId());
				firmante.setIdNumEmpleado(empleadoFirmante.get().getId());
				firmante.setIdInstFirmante(instruccionFirmante.get());
				tabDocsFirmantesRepository.save(firmante);

				String tipofirma = payload.getDocumentosAdjuntos().size() > 1 ? "Múltiple" : "Simple";

				Optional<PkiCatTipoFirma> tipoFirma = pkiCatTipoFirmaRepository.findByDescTipoFirma(tipofirma);
				if (!tipoFirma.isPresent()) {
					res.setMessage(
							"No se encontró el tipo de firma del firmante con Id" + firmantePayload.getIdEmpleado());
					res.setStatus("fail");
					return res;
				}

				PkiDocumentoFirmantes documentoFirmantesStored = createPKIDocumentoFirmantes(
						pkiDocumentoStored.getHashDocumento(), empleadoFirmante.get().getIdUsuario(), null,
						empleadoFirmante.get(), firmantePayload.getSecuencia(), payload.getFechaLimiteFirma(), null,
						tipoFirma.get(), null, null);

			}
			// Se guardan los destinatarios
			for (DTOFirmanteDestinatario destinatarioPayload : payload.getDestinatarios()) {
				Optional<InstEmpleado> empleadoDestinatario = instEmpleadoRepository
						.findById(destinatarioPayload.getIdEmpleado());
				if (!empleadoDestinatario.isPresent()) {
					res.setMessage(
							"No se encontró el empleado destinatario con Id" + destinatarioPayload.getIdEmpleado());
					res.setStatus("fail");
					return res;
				}
				Optional<TabCatInstDest> instruccionDestinatario = tabCatInstDestRepository
						.findByDescInsDest(destinatarioPayload.getInstruccion());
				if (!instruccionDestinatario.isPresent()) {
					res.setMessage("No se encontró la instrucción del destinatario con Id"
							+ destinatarioPayload.getIdEmpleado());
					res.setStatus("fail");
					return res;
				}
				TabDocDestinatarios destinatario = new TabDocDestinatarios();
				destinatario.setIdDocumento(documentoStored.getId());
				destinatario.setIdNumEmpleado(empleadoDestinatario.get().getId());
				destinatario.setIdnstDestinatario(instruccionDestinatario.get());
				tabDocDestinatariosRepository.save(destinatario);
			}

			// Se guarda el wrokflow del documento
			TabDocumentoWorkflow docWorkflow = new TabDocumentoWorkflow();
			docWorkflow.setIdEtapaDocumento(etapaDoc.get());
			docWorkflow.setIdDocument(documentoStored);
			docWorkflow.setUltActualizacion(new Date());
			docWorkflow.setWorkflowFecha(new Date());
			docWorkflow.setWorkflowIdNumEmpleado(empleado.get());

			tabDocumentoWorkflowRepository.save(docWorkflow);

		}
		res.setMessage("Se ha guardado el documento satisfactoriamente");
		res.setStatus("Success");
		res.setData(payload);

		return res;
	}

	public DTOResponse getDocumentosByUsuario(DTOResponse res, DTOResponseUserInfo userInfo) {
		List<ResponseBodyDocsUsuario> listDocsUsuario = new ArrayList<ResponseBodyDocsUsuario>();
		ResponseBodyDocsUsuario docUsuario = null;
		boolean statusFirma = false;
		// Busca todos los documentos en los que el usuario activo se encuentre
		// involucrado
		List<VistaTablero> listDocsByUsuario = vistaTableroRepository.findByNumEmpleado(
				userInfo.getData().getIdEmpleado(), Sort.by(Sort.Direction.DESC, "creacionDocumentoFecha"));
		if (listDocsByUsuario.size() > 0) {
			// si tiene documentos recorre la lista obtenida
			for (VistaTablero docsUsuarioView : listDocsByUsuario) {
				docUsuario = new ResponseBodyDocsUsuario();
				docUsuario.setIdDocumento(docsUsuarioView.getIdDocumento());
				docUsuario.setFolioDocumento(docsUsuarioView.getFolioDocumento());
				docUsuario.setEtapa(docsUsuarioView.getEtapa());
				docUsuario.setPrioridad(docsUsuarioView.getPrioridad());
				docUsuario.setCreacionDocumentoFecha(docsUsuarioView.getCreacionDocumentoFecha());
				docUsuario.setAsunto(docsUsuarioView.getAsunto());
				docUsuario.setTipo(docsUsuarioView.getTipo());
				// obtienen la lista de los firmantes
				List<DTOEmpleado> firmantes = new ArrayList<DTOEmpleado>();
				DTOEmpleado empleado = null;
				List<TabDocsFirmantes> docFirmantes = tabDocsFirmantesRepository
						.findByIdDocumento(docsUsuarioView.getIdDocumento());
				for (TabDocsFirmantes docfirmante : docFirmantes) {
					empleado = new DTOEmpleado();
					empleado.setIdEmpleado(docfirmante.getIntEmpleado().getId());
					empleado.setNombre(docfirmante.getIntEmpleado().getNombre());
					empleado.setApellido1(docfirmante.getIntEmpleado().getApellido1());
					empleado.setApellido2(docfirmante.getIntEmpleado().getApellido2());
					firmantes.add(empleado);
				}
				docUsuario.setFirmantes(firmantes);
				// obtiene la lista de destinatarios
				List<DTOEmpleado> destinatarios = new ArrayList<DTOEmpleado>();
				DTOEmpleado empleadoDest = null;
				List<TabDocDestinatarios> docDestinatarios = tabDocDestinatariosRepository
						.findByIdDocumento(docsUsuarioView.getIdDocumento());
				for (TabDocDestinatarios docDestinatario : docDestinatarios) {
					empleadoDest = new DTOEmpleado();
					empleadoDest.setIdEmpleado(docDestinatario.getIdNumEmpleado());
					empleadoDest.setNombre(docDestinatario.getEmpleado().getNombre());
					empleadoDest.setApellido1(docDestinatario.getEmpleado().getApellido1());
					empleadoDest.setApellido2(docDestinatario.getEmpleado().getApellido2());
					destinatarios.add(empleadoDest);
				}
				docUsuario.setDestinatarios(destinatarios);
				// obtiene la listya de documentos adjuntos
				List<DTODocAdjunto> documentosAdjuntos = new ArrayList<DTODocAdjunto>();
				DTODocAdjunto docAdjuntoDto = null;
				Optional<TabDocumentos> documento = tabDocumentosRepository.findById(docsUsuarioView.getIdDocumento());
				if (documento.isPresent()) {
					List<TabDocumentosAdjuntos> docAdjuntos = tabDocumentosAdjuntosRepository
							.findByIdDocument(documento.get());
					for (TabDocumentosAdjuntos docAdjunto : docAdjuntos) {
						docAdjuntoDto = new DTODocAdjunto();
						docAdjuntoDto.setDocBase64(docAdjunto.getDocumentoBase64());
						docAdjuntoDto.setFilePath(docAdjunto.getDocumentoPath());
						docAdjuntoDto.setFileType(docAdjunto.getDocumentoFiletype());

						documentosAdjuntos.add(docAdjuntoDto);

						// aqui obtiene el estado de la firma , segun si es firmante o destinatario
						HashDocumentoIdUsuarioIdTransaccionID idDocumento = new HashDocumentoIdUsuarioIdTransaccionID();
						idDocumento.setHashDocumento(docAdjunto.getDocumentoHash());
						idDocumento.setIdUsuario(userInfo.getData().getIdUsuario());
						Optional<PkiDocumentoFirmantes> docUsuFirmante = pkiDocumentoFirmantesRepository
								.findById(idDocumento);
						Optional<PkiDocumentoDestino> docUsuDestinatario = pkiDocumentoDestRepository
								.findById(idDocumento);
						if (docUsuFirmante.isPresent()) {
							if (docUsuFirmante.get().getIdFirmaAplicada() != null) {
								statusFirma = true;
							}
						}
						if (docUsuDestinatario.isPresent()) {
							if (docUsuDestinatario.get().getIdFirmaAplicada() != null) {
								statusFirma = true;
							}
						}

					}
				}
				docUsuario.setStatusFirma(statusFirma);
				docUsuario.setDocumentosAdjuntos(documentosAdjuntos);

				listDocsUsuario.add(docUsuario);

			}
			res.setData(listDocsUsuario);
			res.setMessage("Se han encontrado resultados");
			res.setStatus("Success");
		} else {
			res.setData(listDocsUsuario);
			res.setMessage("No se han encontrado resultados");
			res.setStatus("Success");
		}
		return res;
	}

	public boolean firmar(PayloadFirma payload, DTOResponse res, DTOResponseUserInfo userInfo) {
		ResponseBodyFirma responde = new ResponseBodyFirma();
		
		//Sección de actualización de PKI documentos_firmantes
		HashDocumentoIdUsuarioIdTransaccionID idDocumentoFirmantes = new HashDocumentoIdUsuarioIdTransaccionID();
		idDocumentoFirmantes.setHashDocumento(payload.getHashDocumento());
		idDocumentoFirmantes.setIdUsuario(userInfo.getData().getIdUsuario());

		Optional<PkiDocumentoFirmantes> docFirmantesPki = pkiDocumentoFirmantesRepository.findById(idDocumentoFirmantes);
		if (!docFirmantesPki.isPresent()) {
			res.setData(null);
			res.setStatus("Fail");
			res.setMessage("No puedes firmar este documento, ya que no te fue asignado para firmar");
			return false;
		}

		PkiCatFirmaAplicada firmaAplicada_firmado = serviceFirmarAhora.findFirmaAplicada(EnumPkiCatFirmaAplicada.FIRMADO.getOpcion(), res);
		if(firmaAplicada_firmado==null) {
			return false;
		}

		try {
			docFirmantesPki.get().setCadenaFirma(payload.getCadenaFirma().toString());
			docFirmantesPki.get().setFechaFirma(new Date());
			docFirmantesPki.get().setIdFirmaAplicada(firmaAplicada_firmado);
				
			pkiDocumentoFirmantesRepository.save(docFirmantesPki.get());
				
		}catch(Exception e) {
			res.setMessage("No se pudo actualizar la tabla documentos firmantes");
			res.setStatus("fail");
			return false;
		}
		
		//Sección para agregar firma al documento pdf
		String fileName = documentFirmadosPath + "/" + payload.getHashDocumento() + "_" + userInfo.getData().getIdUsuario() + ".pdf";
		
		serviceFirma.firma(payload.getHashDocumento(), payload.getDocumento(), payload.getCadenaFirma(),
					payload.getCertificado(), fileName,
					userInfo);
		
		try {
			//sección para actualizar documento PKI
			PkiDocumento documentoPki = docFirmantesPki.get().getDocumento();
			
			documentoPki.setAlgoritmo(encryptionAlgorithm);
				//
			
			TabDocumentosAdjuntos documentoAdjuntoTab = serviceFirmar.getTabDocumentoAdjuntoByHash(payload.getHashDocumento(), res);
			if(documentoAdjuntoTab==null) {
				return false;
			}
			List<TabDocumentosAdjuntos> listaDocAdjuntosTab =  serviceFirmar.getTabDocumentosAdjuntos(documentoAdjuntoTab.getIdDocument());
			List<TabDocsFirmantes> listaFirmantesTab =  serviceFirmar.getTabDocsFirmantes(documentoAdjuntoTab.getIdDocument().getId());
			

			for(TabDocumentosAdjuntos docAdjuntoTab : listaDocAdjuntosTab) {
				boolean archivoCompleted = true;
				for(TabDocsFirmantes firmanteTab : listaFirmantesTab) {
					
					Optional<PkiDocumentoFirmantes> docUsuFirmante = pkiDocumentoFirmantesRepository.
							findByHashDocumentoAndIdNumEmpleadoAndIdFirmaAplicada(docAdjuntoTab.getDocumentoHash(), firmanteTab.getIntEmpleado(), firmaAplicada_firmado);
					if(!docUsuFirmante.isPresent()) {
						archivoCompleted = false;
					}
				}
				if(archivoCompleted) {
					documentoPki.setTerminado(1);
				}
			}

//			int totalFaltantes = serviceFirmar.getNumFirmantesPendientesDeFirmarUnArchivoAdjunto(payload.getHashDocumento());
//			if(totalFaltantes==0) {
//				documentoPki.setTerminado(1);
//			}
				
			pkiDocumentoRepository.save(documentoPki);
			
		}catch(Exception e) {
			res.setMessage("No se pudo actualizar la información en la tabla documento PKI");
			res.setStatus("fail");
			return false;
		}
				
		//Validamos si podemos dar por terminado el dcumento (No el archivo adjunto, más bien el tab documento)
		//Obtenemos los elementos necesarios de la sección de la BD tablero para posterior
		TabDocumentosAdjuntos documentoAdjuntoTab = serviceFirmar.getTabDocumentoAdjuntoByHash(payload.getHashDocumento(), res);
		if(documentoAdjuntoTab==null) {
			return false;
		}
		List<TabDocumentosAdjuntos> documentosAdjuntosTabList =  serviceFirmar.getTabDocumentosAdjuntos(documentoAdjuntoTab.getIdDocument());
				
		boolean allDocsFirmados = true;
		PkiDocumento pkiDoc = null;
		for(TabDocumentosAdjuntos docAdjunto : documentosAdjuntosTabList) {
			pkiDoc = serviceFirmar.getDocumentoPKIByHash(docAdjunto.getDocumentoHash(), res);
			if(pkiDoc.getTerminado()==0) {
				allDocsFirmados = false;
			}
		}
		
		if(allDocsFirmados) {
			TabCatEtapaDocumento etapaDoc_terminado = serviceFirmarAhora.findEtapaDocumento(EnumTabCatEtapaDocumento.TERMINADO.getOpcion(), res);
			if (etapaDoc_terminado == null) {
				return false;
			}

			TabDocumentoWorkflow workflowStored_Terminado = serviceFirmar.storeWorkFlow(
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

	public DTOResponse rechazarDocumento(PayloadRechazarDocumento payload, DTOResponse res,
			DTOResponseUserInfo userInfo) {

		Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(userInfo.getData().getIdEmpleado());
		if (!empleado.isPresent()) {
			res.setMessage("No se puede encontrar el empleado");
			res.setStatus("fail");
			return res;
		}
		// obtengo el registro correspondiente a la tabla tabDocumento
		Optional<TabDocumentos> documentoExist = tabDocumentosRepository.findById(payload.getIdDocumento());
		Optional<TabCatEtapaDocumento> etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa("Rechazado");
		// obtengo los documentos relacionados de la tabla docAdjuntos para conocer el
		// hash
		List<TabDocumentosAdjuntos> docsAdjuntos = tabDocumentosAdjuntosRepository
				.findByIdDocument(documentoExist.get());

		for (TabDocumentosAdjuntos tabDocAd : docsAdjuntos) {
			HashDocumentoIdUsuarioIdTransaccionID idDocumento = new HashDocumentoIdUsuarioIdTransaccionID();
			idDocumento.setHashDocumento(tabDocAd.getDocumentoHash());
			idDocumento.setIdUsuario(userInfo.getData().getIdUsuario());

			if (payload.getTipoUsuario().equals("firmante")) {
				// Busca el documento en la tabla pki_documento_firmantes
				Optional<PkiDocumentoFirmantes> docFirmantes = pkiDocumentoFirmantesRepository.findById(idDocumento);
				if (docFirmantes.isPresent()) {
					Optional<PkiCatFirmaAplicada> firmaAplicada = pkiCatFirmaAplicadaRepository
							.findByDescFirmaAplicada(payload.codigoFirmaAplicada);
					if (!firmaAplicada.isPresent()) {
						res.setMessage("No se encontró el tipo de firma");
						res.setStatus("fail");
						return res;
					}
					// actualiza el registro del firmante con el rechazo de la firma
					docFirmantes.get().setCadenaFirma(null);
					docFirmantes.get().setFechaFirma(new Date());
					docFirmantes.get().setIdFirmaAplicada(firmaAplicada.get());
					pkiDocumentoFirmantesRepository.save(docFirmantes.get());

				}
			} else if (payload.getTipoUsuario().equals("destinatario")) {
				// Busca el documento en la tabla pki_documento_destino
				Optional<PkiDocumentoDestino> docDestinos = pkiDocumentoDestRepository.findById(idDocumento);
				if (docDestinos.isPresent()) {
					Optional<PkiCatFirmaAplicada> firmaAplicada = pkiCatFirmaAplicadaRepository
							.findByDescFirmaAplicada(payload.codigoFirmaAplicada);
					if (!firmaAplicada.isPresent()) {
						res.setMessage("No se encontró el tipo de firma");
						res.setStatus("fail");
						return res;
					}
					// actualiza el registro del destinatario con el rechazo de la firma
					docDestinos.get().setFechaLectura(new Date());
					docDestinos.get().setIdFirmaAplicada(firmaAplicada.get());
					pkiDocumentoDestRepository.save(docDestinos.get());

				}
			} else {
				res.setData(null);
				res.setStatus("Fail");
				res.setMessage("La Accion del usuario no fué reconocida");

				return res;
			}

		}
		boolean terminado = false;
		// vuelvo a recorrer el array de docs adjuntos para poder extraer el hash de los
		// documentos y
		// verificar en pki firmantes si ya tienen alguna accion
		for (TabDocumentosAdjuntos tabDocAd : docsAdjuntos) {
			Optional<PkiDocumentoFirmantes> docFirm = pkiDocumentoFirmantesRepository
					.findByHashDocumento(tabDocAd.getDocumentoHash());
			if (docFirm.isPresent()) {
				if (docFirm.get().getIdFirmaAplicada() != null) {
					terminado = true;
				} else {
					terminado = false;
					break;
				}
			}

		}
		if (terminado) {
			etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa("Terminado");
		}

		// creamos registro en tabla workflow
		TabDocumentoWorkflow docWorkflow = new TabDocumentoWorkflow();
		docWorkflow.setIdEtapaDocumento(etapaDoc.get());
		docWorkflow.setIdDocument(documentoExist.get());
		docWorkflow.setUltActualizacion(new Date());
		docWorkflow.setWorkflowFecha(new Date());
		docWorkflow.setWorkflowIdNumEmpleado(empleado.get());

		tabDocumentoWorkflowRepository.save(docWorkflow);

		res.setData(null);
		res.setStatus("Succes");
		res.setMessage("El documento ha sido Rechazado");

		return res;

	}

	public DTOResponse enviarDocumento(PayloadEnviarDocumento payload, DTOResponse res, DTOResponseUserInfo userInfo) {
		// buscamos el documento por su id
		Optional<TabDocumentos> documentoExist = tabDocumentosRepository.findById(payload.getIdDocumento());

		if (documentoExist.isPresent()) {// si existe deja enviar a firmantes y dest
			Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(userInfo.getData().getIdEmpleado());
			if (!empleado.isPresent()) { // valida el empleado
				res.setMessage("No se puede encontrar el empleado");
				res.setStatus("fail");
				return res;
			}
			// verifica que seas empleado creador para poder enviar
			if (empleado.get().getId() == documentoExist.get().getIdNumEmpleadoCreador().getId()) {
				// consulta los documentos adjuntos al documento a enviar
				List<TabDocumentosAdjuntos> listDocsAdjuntos = tabDocumentosAdjuntosRepository
						.findByIdDocument(documentoExist.get());
				Date fchEnvio = new Date();
				// consulta a los firmantes y destinatarios asignados
				List<TabDocsFirmantes> firmantesTab = tabDocsFirmantesRepository
						.findByIdDocumento(payload.getIdDocumento());

				List<TabDocDestinatarios> destinatariosTab = tabDocDestinatariosRepository
						.findByIdDocumento(payload.getIdDocumento());
				// valida el tipo de firma
				String tipofirma = firmantesTab.size() > 1 ? "Múltiple" : "Simple";
				Optional<PkiCatTipoFirma> tipoFirma = pkiCatTipoFirmaRepository.findByDescTipoFirma(tipofirma);
				if (!tipoFirma.isPresent()) {
					res.setMessage("No se encontró el tipo de firma");
					res.setStatus("fail");
					return res;
				}
				// recorre la lista de documentos adjuntos
				for (TabDocumentosAdjuntos docAdjunto : listDocsAdjuntos) {
					// valida que el documento adjunto exista en la tabla pkiDoc
					Optional<PkiDocumento> documentPkiExist = pkiDocumentoRepository
							.findById(docAdjunto.getDocumentoHash());
					if (!documentPkiExist.isPresent()) {
						// si no existe crea el registro en pkiDoc
						PkiDocumento documentPkiNew = createPKIDocumento(docAdjunto.getDocumentoHash(),
								documentoExist.get().getIdNumEmpleadoCreador(), empleado.get(), fchEnvio,
								documentoExist.get().getCreacionDocumentoFecha(), "Enviado", false, true);
					} else {
						// si existe solo edito la fecha de envío
						documentPkiExist.get().setFechaEnvio(fchEnvio);
						documentPkiExist.get().setStatusDocumento("Enviado");
						PkiDocumento documentPkiEdit = pkiDocumentoRepository.save(documentPkiExist.get());

					}
					// aqui va a llenar la tabla de firmantesPki
					int secuencia = 1;
					// recorre las listas de firmantes y destinatarios creando sus registros
					for (TabDocsFirmantes firmante : firmantesTab) {
						Optional<InstEmpleado> empleadoIns = instEmpleadoRepository
								.findById(firmante.getIdNumEmpleado());
						PkiDocumentoFirmantes firmantesPki = createPKIDocumentoFirmantes(docAdjunto.getDocumentoHash(),
								empleadoIns.get().getIdUsuario(), null, empleadoIns.get(), secuencia,
								documentoExist.get().getFechaLimiteFirma(), null, tipoFirma.get(), null, null);
						secuencia++;
					}
					// aqui va a llenar la tabla de destinatariosPki
					for (TabDocDestinatarios destinatario : destinatariosTab) {
						Optional<InstEmpleado> empleadoIns = instEmpleadoRepository
								.findById(destinatario.getIdNumEmpleado());
						PkiDocumentoDestino destinosPki = createPKIDocumentoDestinatario(docAdjunto.getDocumentoHash(),
								empleadoIns.get().getIdUsuario(), null, empleadoIns.get(), fchEnvio, null, null, null,
								null);
						secuencia++;
					}

				}
				Optional<TabCatEtapaDocumento> etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa("Enviado");
				// Se guarda el wrokflow del documento
				TabDocumentoWorkflow docWorkflow = new TabDocumentoWorkflow();
				docWorkflow.setIdEtapaDocumento(etapaDoc.get());
				docWorkflow.setIdDocument(documentoExist.get());
				docWorkflow.setUltActualizacion(new Date());
				docWorkflow.setWorkflowFecha(new Date());
				docWorkflow.setWorkflowIdNumEmpleado(empleado.get());

				tabDocumentoWorkflowRepository.save(docWorkflow);

				res.setData(null);
				res.setStatus("Succes");
				res.setMessage("Documento Enviado a firmantes y destinatarios");
				return res;

			} else {
				res.setData(null);
				res.setStatus("Fail");
				res.setMessage("No puedes enviar este documento, ya que no eres quien lo creó");
				return res;
			}
		}
		res.setData(null);
		res.setStatus("Fail");
		res.setMessage("No puedes enviar este documento, ya que no existe");
		return res;
		// TODO Auto-generated method stub

	}

	public PkiDocumento createPKIDocumento(String hashDocumento, InstEmpleado empleadoCreador,
			InstEmpleado empleadoEnvio, Date fechaEnvio, Date fechaCreacion, String statusDocumento,
			boolean isTerminado, boolean isEnOrden) {
		try {
			PkiDocumento documento = new PkiDocumento();
			documento.setHashDocumento(hashDocumento);
			documento.setIdNumEmpleadoCreador(empleadoCreador);
			documento.setIdNumEmpleadoEnvio(empleadoEnvio);
			documento.setFechaEnvio(fechaEnvio);
			documento.setAlgoritmo(encryptionAlgorithm);
			documento.setFechaCreacion(fechaCreacion);
			documento.setStatusDocumento(statusDocumento);
			documento.setTerminado(isTerminado ? 1 : 0);
			documento.setOrden(isEnOrden ? 1 : 0);
			PkiDocumento documentoSaved = pkiDocumentoRepository.save(documento);
			return documentoSaved;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public PkiDocumentoFirmantes createPKIDocumentoFirmantes(String hashDocumento, SegOrgUsuarios usuarioFirmante,
			PkiTransaccion transaccion, InstEmpleado empleadoFirmante, int secuencia, Date fechaLimite, Date fechaFirma,
			PkiCatTipoFirma tipoFirma, PkiCatFirmaAplicada firmaAplicada, String cadenaFirma) {

		try {
			PkiDocumentoFirmantes firmantes = new PkiDocumentoFirmantes();
			firmantes.setHashDocumento(hashDocumento);
			firmantes.setIdUsuario(usuarioFirmante.getnIdUsuario());
			if (transaccion != null) {
				firmantes.setTransaccion(transaccion);
			}
			firmantes.setIdNumEmpleado(empleadoFirmante);
			firmantes.setSecuencia(secuencia);
			firmantes.setFechaLimite(fechaLimite);
			firmantes.setFechaFirma(fechaFirma);
			firmantes.setIdTipoFirma(tipoFirma);
			firmantes.setIdFirmaAplicada(firmaAplicada);
			firmantes.setCadenaFirma(cadenaFirma);
			pkiDocumentoFirmantesRepository.save(firmantes);
			return firmantes;
		} catch (Exception e) {
			return null;
		}
	}

	public PkiDocumentoDestino createPKIDocumentoDestinatario(String hashDocumento, SegOrgUsuarios usuarioDest,
			PkiTransaccion transaccion, InstEmpleado empleadoDest, Date fechaNot, PkiCatFirmaAplicada firmaAplicada,
			Date acuse, Date lectura, PkiCatInstruccionDoc instruccion) {

		try {
			PkiDocumentoDestino destinatarios = new PkiDocumentoDestino();
			destinatarios.setHashDocumento(hashDocumento);
			destinatarios.setIdUsuario(usuarioDest.getnIdUsuario());
			if (transaccion != null) {
				destinatarios.setIdTransaccion(transaccion);
			}
			destinatarios.setIdNumEmpleado(empleadoDest);
			destinatarios.setFechaNotificacion(fechaNot);
			destinatarios.setIdFirmaAplicada(firmaAplicada);
			destinatarios.setFechaAcuse(acuse);
			destinatarios.setFechaLectura(lectura);
			destinatarios.setIdInstruccionDoc(instruccion);

			pkiDocumentoDestRepository.save(destinatarios);
			return destinatarios;
		} catch (Exception e) {
			return null;
		}
	}

//	public DTOResponse createDocumento(String hashDocumento, InstEmpleado empleadoCreador, InstEmpleado empleadoEnvio,
//			Date fechaEnvio, Date fechaCreacion, String statusDocumento, boolean isTerminado, boolean isEnOrden) {
//		CertificateUtils utils = new CertificateUtils();

//		PkiDocumento documento = new PkiDocumento();
//		documento.setHashDocumento(hashDocumento);
//		documento.setIdNumEmpleadoCreador(empleadoCreador);
//		documento.setIdNumEmpleadoEnvio(empleadoEnvio);
//		documento.setFechaEnvio(fechaEnvio);
//		documento.setAlgoritmo(encryptionAlgorithm);
//		documento.setFechaCreacion(fechaCreacion);
//		documento.setStatusDocumento(statusDocumento);
//		documento.setTerminado(isTerminado?1:0);
//		documento.setOrden(isEnOrden?1:0);
//		PkiDocumento documentoSaved = pkiDocumentoRepository.save(documento);
//		
//		PkiDocumentoFirmantes firmantes = null;
//		for(DTOFirmante firmante:payload.getFirmantes()) {
//			firmantes = new PkiDocumentoFirmantes();
//			firmantes.setHashDocumento(payload.getHashDocumento());
//			firmantes.setIdNumEmpleado(firmante.getIdEmpleadoFirmante());
//			firmantes.setIdUsuario(firmante.getIdUsuarioFirmante());
//			firmantes.setSecuencia(firmante.getSecuencia());
//			firmantes.setFechaLimite(firmante.getFechaLimite());
//			firmantes.setFechaFirma(null);
//			firmantes.setIdTipoFirma(firmante.getIdTipoFirma());
//			firmantes.setIdFirmaAplicada(null);
//			pkiDocumentoFirmantesRepository.save(firmantes);
//		}
//		
//		res.setData(null);
//		res.setStatus("Success");
//		res.setMessage("El documento y sus firmantes se han creado satisfactoriamente");
//		return res;

//	}

}
