package mx.gob.tecdmx.firmapki.api.Metodos;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.api.firma.DAOFirmanteDestinatario;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoDestino;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDocConfig;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstDest;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatFirmaAplicadaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatTipoFirmaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoDestinoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatDestinoDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatDocConfigRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatEtapaDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatPrioridadRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatTipoDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabExpedientesRepository;
import mx.gob.tecdmx.firmapki.utils.dto.DTOConfiguracion;
import mx.gob.tecdmx.firmapki.utils.dto.DTODocAdjunto;
import mx.gob.tecdmx.firmapki.utils.dto.DTOFirmanteDestinatario;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.dto.PayloadAltaDocumento;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceValidationsMethods {

	@Autowired
	TabCatEtapaDocumentoRepository tabCatEtapaDocumentoRepository;
	
	@Autowired
	TabCatTipoDocumentoRepository tabCatTipoDocumentoRepository;
	
	@Autowired
	TabExpedientesRepository tabExpedientesRepository;

	@Autowired
	TabCatDestinoDocumentoRepository tabCatDestinoDocumentoRepository;

	@Autowired
	TabCatPrioridadRepository tabCatPrioridadRepository;
	
	@Autowired
	TabCatDocConfigRepository tabCatDocConfigRepository;

	@Autowired
	PkiCatFirmaAplicadaRepository pkiCatFirmaAplicadaRepository;

	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;

	@Autowired
	PkiDocumentoDestinoRepository pkiDocumentoDestRepository;
	
	@Autowired
	PkiCatTipoFirmaRepository pkiCatTipoFirmaRepository;

	@Autowired
	InstEmpleadoRepository instEmpleadoRepository;

	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;

	public boolean verificaDocumentoIsTerminadoFirmantes(List<TabDocumentosAdjuntos> docsAdjuntos) {
		boolean terminado = false;
		for (TabDocumentosAdjuntos tabDocAd : docsAdjuntos) {
			List<PkiDocumentoFirmantes> docFirm = pkiDocumentoFirmantesRepository
					.findByHashDocumento(tabDocAd.getDocumentoHash());
			for (PkiDocumentoFirmantes pkiDocFirm : docFirm) {
				if (pkiDocFirm.getIdFirmaAplicada() != null) {
					terminado = true;
				} else {
					terminado = false;
					break;
				}

			}

		}

		return true;

	}

	public boolean verificaDocumentoIsTerminadoDestinatarios(List<TabDocumentosAdjuntos> docsAdjuntos) {
		boolean terminado = false;
		for (TabDocumentosAdjuntos tabDocAd : docsAdjuntos) {
			List<PkiDocumentoDestino> docFirm = pkiDocumentoDestRepository
					.findByHashDocumento(tabDocAd.getDocumentoHash());
			for (PkiDocumentoDestino pkiDocFirm : docFirm) {
				if (pkiDocFirm.getIdFirmaAplicada() != null) {
					terminado = true;
				} else {
					terminado = false;
					break;
				}

			}

		}

		return false;

	}

	public boolean validateCatalogosEscritorio(String tipoPrioridadInp, DAOAltaDocumento documentoAlta,
			DTOResponse res) {
		TabCatPrioridad tipoPrioridad = serviceConsultaMethods.findTipoPrioridad(tipoPrioridadInp, documentoAlta, res);
		if (tipoPrioridad == null) {
			return false;
		}
		return true;
	}

	public boolean validateFirmantesExist(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res) {
		// Validamos que los datos de los firmantes existan
		DAOFirmanteDestinatario firmante = null;
		for (DTOFirmanteDestinatario firmantes : payload.getFirmantes()) {
			firmante = new DAOFirmanteDestinatario();

			firmante.setSecuencia(firmantes.getSecuencia());

			InstEmpleado empleadoFirmante = serviceConsultaMethods.findEmpleado(firmantes.getIdEmpleado(), res);
			if (empleadoFirmante == null) {
				return false;
			}
			firmante.setEmpleado(empleadoFirmante);

			TabCatInstFirmantes instruccionFirmante = serviceConsultaMethods
					.findInstruccionFirmante(firmantes.getInstruccion(), res);
			if (instruccionFirmante == null) {
				return false;
			}
			firmante.setInstruccionFirmante(instruccionFirmante);

			documentoAlta.getFirmantes().add(firmante);
		}
		return true;
	}
	
	public boolean validateDestinatariosExist(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res) {
		// Validamos que los datos de los firmantes existan
		DAOFirmanteDestinatario destinatario = null;
		for (DTOFirmanteDestinatario destinatarios : payload.getDestinatarios()) {
			destinatario = new DAOFirmanteDestinatario();

			destinatario.setSecuencia(destinatarios.getSecuencia());

			InstEmpleado empleadoFirmante = serviceConsultaMethods.findEmpleado(destinatarios.getIdEmpleado(), res);
			if (empleadoFirmante == null) {
				return false;
			}
			destinatario.setEmpleado(empleadoFirmante);

			TabCatInstDest instruccionDest = serviceConsultaMethods.findInstruccionDestinatario(destinatarios.getInstruccion(), res);
			if (instruccionDest == null) {
				return false;
			}
			destinatario.setInstruccionDest(instruccionDest);

			documentoAlta.getDestinatarios().add(destinatario);
		}
		return true;
	}

	public boolean validarArchivosUnicos(List<DTODocAdjunto> documentosAdjuntos, DTOResponse res) {
		Set<String> documentosUnicos = new HashSet<>();

		for (DTODocAdjunto documento : documentosAdjuntos) {
			if (!documentosUnicos.add(documento.getDocBase64())) {
				res.setMessage("No se puede cargar dos veces el mismo archivo");
				res.setStatus("fail");
				return false; // Se encontró un documento repetido
			}
		}
		return true; // Todos los documentos son únicos
	}

	public boolean validateArchivoNuevoInDataBase(List<DTODocAdjunto> documentosAdjuntos, DTOResponse res) {
		int pos = 1;
		for (DTODocAdjunto docAdjuntoPayload : documentosAdjuntos) {
			boolean archivoExist = serviceConsultaMethods.documentNotExistInDataBase(docAdjuntoPayload.getDocBase64(),
					pos, res);
			if (!archivoExist) {
				return false;
			}
			pos++;
		}
		return true;
	}

	public TabDocsFirmantes getCurrentFirmanteInList(List<TabDocsFirmantes> firmantes, InstEmpleado empleado,
			DTOResponse res) {
		if (firmantes == null) {
			res.setMessage("La lista de firmantes no puede estar vacía");
			res.setStatus("fail");
			return null;
		}

		TabDocsFirmantes currentFirmante = null;
		for (TabDocsFirmantes firmante : firmantes) {
			if (firmante.getIdNumEmpleado() == empleado.getId()) {
				currentFirmante = firmante;
				break;
			}
		}
		if (currentFirmante == null) {
			res.setMessage("El firmante a firmar no se encuentra dentro de la lista de firmantes");
			res.setStatus("fail");
			return null;
		}
		return currentFirmante;
	}

	public DTOResponse verificaFirmanteDestinatarioIsUsuario(List<DTOFirmanteDestinatario> list, DTOResponse res) {
		for (DTOFirmanteDestinatario firmanteDest : list) {
			Optional<InstEmpleado> empleadoExist = instEmpleadoRepository.findById(firmanteDest.getIdEmpleado());
			if (empleadoExist.isPresent()) {
				if (empleadoExist.get().getIdUsuario() != null) {
				
				} else {
					res.setStatus("fail");
					res.setMessage("La persona con nombre: <strong>" + empleadoExist.get().getNombre() + "</strong> "
						    + "<strong>" + empleadoExist.get().getApellido1() + "</strong> " + "<strong>" + empleadoExist.get().getApellido2()
						    + "</strong>, no cuenta con un registro en el sistema, por lo tanto no tiene permisos para realizar acciones sobre los documentos");
					return res;
				}

			}
		}
		res.setStatus("Success");
		return res;

	}

	public boolean validateCatalogEditDocument(List<DTOConfiguracion> configuraciones,
			List<TabCatDocConfig> listConfig) {
		for (DTOConfiguracion configDTO : configuraciones) {
			Optional<TabCatDocConfig> config = tabCatDocConfigRepository.findByAtributo(configDTO.getAtributo());
			if (config.isPresent()) {
				listConfig.add(config.get());
			} else {
				return false;
			}
		}
		return true;
	}
	
	public TabCatEtapaDocumento findEtapaDocumento(String etapa, DTOResponse res) {
		Optional<TabCatEtapaDocumento> etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa(etapa);
		if (!etapaDoc.isPresent()) {
			res.setMessage("Dentro del catálogo para el workflow no se encontró la etapa " + etapa);
			res.setStatus("fail");
			return null;
		}
		return etapaDoc.get();
	}

	public PkiCatTipoFirma findTipoFirma(String tipofirma, DTOResponse res) {
		Optional<PkiCatTipoFirma> tipoFirmaCat = pkiCatTipoFirmaRepository.findByDescTipoFirma(tipofirma);
		if (!tipoFirmaCat.isPresent()) {
			res.setMessage("No se encontró el tipo de firma: " + tipofirma);
			res.setStatus("fail");
			return null;
		}
		return tipoFirmaCat.get();
	}

	public TabExpedientes findNumExpediente(String numExpediente, DAOAltaDocumento documentoAlta, DTOResponse res) {
		if (numExpediente != null) {
			Optional<TabExpedientes> numExpedienteOpt = tabExpedientesRepository.findByNumExpediente(numExpediente);
			if (numExpedienteOpt.isPresent()) {
				documentoAlta.setExpediente(numExpedienteOpt.get());
				return numExpedienteOpt.get();
			}
		}
		res.setMessage("No se encontró el número de expediente " + numExpediente);
		res.setStatus("fail");
		return null;
	}

	public TabCatTipoDocumento findTipoDocumento(Integer tipoDoc, DAOAltaDocumento documentoAlta, DTOResponse res) {
		if (tipoDoc > 0) {
			Optional<TabCatTipoDocumento> tipoDocOpt = tabCatTipoDocumentoRepository.findById(tipoDoc);

			if (tipoDocOpt.isPresent()) {
				documentoAlta.setTipoDoc(tipoDocOpt.get());
				return tipoDocOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de documento " + tipoDoc);
		res.setStatus("fail");
		return null;
	}

	public TabCatDestinoDocumento findTipoDestino(String tipoDest, DAOAltaDocumento documentoAlta, DTOResponse res) {
		if (tipoDest != null) {
			Optional<TabCatDestinoDocumento> tipoDestOpt = tabCatDestinoDocumentoRepository
					.findByDescDestinoDocumento(tipoDest);
			if (tipoDestOpt.isPresent()) {
				documentoAlta.setDestinoDoc(tipoDestOpt.get());
				return tipoDestOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de destino " + tipoDest);
		res.setStatus("fail");
		return null;
	}

	public TabCatPrioridad findTipoPrioridad(String tipoPrioridad, DAOAltaDocumento documentoAlta, DTOResponse res) {
		if (tipoPrioridad != null) {
			Optional<TabCatPrioridad> tipoPrioridadOpt = tabCatPrioridadRepository.findByDescPrioridad(tipoPrioridad);
			if (tipoPrioridadOpt.isPresent()) {
				documentoAlta.setPrioridad(tipoPrioridadOpt.get());
				return tipoPrioridadOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de prioridad " + tipoPrioridad);
		res.setStatus("fail");
		return null;
	}
	
	public boolean validateCatalogos(String numExpedienteInp, Integer tipoDocumentoInp, String tipoDestinoInp,
			String tipoPrioridadInp, DAOAltaDocumento documentoAlta, DTOResponse res) {

		TabExpedientes numExpediente = findNumExpediente(numExpedienteInp, documentoAlta, res);
		TabCatTipoDocumento tipoDOc = findTipoDocumento(tipoDocumentoInp, documentoAlta, res);
		TabCatDestinoDocumento tipoDestino = findTipoDestino(tipoDestinoInp, documentoAlta, res);
		TabCatPrioridad tipoPrioridad = findTipoPrioridad(tipoPrioridadInp, documentoAlta, res);
		if (numExpediente == null || tipoDOc == null || tipoDestino == null || tipoPrioridad == null) {
			return false;
		}
		return true;
	}
	
	public boolean alguienYaFirmo(TabDocumentos documentoStored, DTOResponse res) {
		String etapaSequence = serviceConsultaMethods.getSequenceWorkflow(documentoStored, res);
		if (etapaSequence == null) {
			return false;
		}
		if (etapaSequence.contains(EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())) {
			return true;
		}
		return false;
	}

	public boolean validateFirmanteHaFirmado(String hashDoc, InstEmpleado empleado, PkiCatFirmaAplicada firmaAplicada) {
		Optional<PkiDocumentoFirmantes> faltantesFirmarArchivo = pkiDocumentoFirmantesRepository
				.findByHashDocumentoAndIdNumEmpleadoAndIdFirmaAplicada(hashDoc,
						empleado, firmaAplicada);
		if (faltantesFirmarArchivo.isPresent()) {
			return true;
		}
		return false;
	}

}
