package mx.gob.tecdmx.firmapki.api.Metodos;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.api.documento2.DTODocAdjunto;
import mx.gob.tecdmx.firmapki.api.documento2.DTOFirmanteDestinatario;
import mx.gob.tecdmx.firmapki.api.documento2.PayloadAltaDocumento;
import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.api.firma.DAOFirmanteDestinatario;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoDestino;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatFirmaAplicadaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoDestinoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatEtapaDocumentoRepository;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;
@Service
public class ServiceValidationsMethods {
	
	@Autowired
	private TabCatEtapaDocumentoRepository tabCatEtapaDocumentoRepository;
	
	@Autowired
	PkiCatFirmaAplicadaRepository pkiCatFirmaAplicadaRepository;
	
	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;

	@Autowired
	PkiDocumentoDestinoRepository pkiDocumentoDestRepository;
	
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

			TabCatInstFirmantes instruccionFirmante = serviceConsultaMethods.findInstruccionFirmante(firmantes.getInstruccion(), res);
			if (instruccionFirmante == null) {
				return false;
			}
			firmante.setInstruccionFirmante(instruccionFirmante);

			documentoAlta.getFirmantes().add(firmante);
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
			boolean archivoExist = serviceConsultaMethods.documentNotExistInDataBase(docAdjuntoPayload.getDocBase64(), pos, res);
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
}
