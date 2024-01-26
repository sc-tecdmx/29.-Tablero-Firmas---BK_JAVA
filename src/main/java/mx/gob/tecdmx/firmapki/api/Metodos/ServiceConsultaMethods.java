package mx.gob.tecdmx.firmapki.api.Metodos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatFirmaAplicadaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatTipoFirmaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatEtapaDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatInstFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatPrioridadRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocsFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@Service
public class ServiceConsultaMethods {
	
	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;
	
	@Autowired
	TabCatEtapaDocumentoRepository tabCatEtapaDocumentoRepository;
	
	@Autowired
	TabCatPrioridadRepository tabCatPrioridadRepository;

	@Autowired
	TabDocsFirmantesRepository tabDocsFirmantesRepository;
	
	@Autowired
	TabCatInstFirmantesRepository tabCatInstFirmantesRepository;
	
	@Autowired
	PkiDocumentoRepository pkiDocumentoRepository;
	
	@Autowired
	PkiCatFirmaAplicadaRepository pkiCatFirmaAplicadaRepository;
	
	@Autowired
	PkiCatTipoFirmaRepository pkiCatTipoFirmaRepository;
	
	@Autowired
	InstEmpleadoRepository instEmpleadoRepository;
	
	CertificateUtils utils = null;
	
	public TabCatEtapaDocumento findEtapaDocumentoEnum(String etapa, DTOResponse res) {
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
	
	public PkiCatFirmaAplicada findFirmaAplicada(String tipoFirmaAplicada, DTOResponse res) {
		Optional<PkiCatFirmaAplicada> firmaAplicada = pkiCatFirmaAplicadaRepository
				.findByDescFirmaAplicada(tipoFirmaAplicada);// No se obtiene del payload, ya que este es un proceso de
															// firmado, las opciones se deben controlar aquí
		if (!firmaAplicada.isPresent()) {
			res.setMessage("No se encontró el tipo de firma aplicada " + tipoFirmaAplicada);
			res.setStatus("fail");
			return null;
		}
		return firmaAplicada.get();
	}
	
	public TabCatInstFirmantes findInstruccionFirmante(String instruccion, DTOResponse res) {
		Optional<TabCatInstFirmantes> instruccionFirmante = tabCatInstFirmantesRepository
				.findByDescInstrFirmante(instruccion);
		if (instruccionFirmante.isPresent()) {
			return instruccionFirmante.get();
		}
		res.setMessage("No se encontró la instrucción del firmante:" + instruccion);
		res.setStatus("fail");
		return null;
	}
	
	public TabDocumentosAdjuntos getTabDocumentoAdjuntoByHash(String hashDocumento, DTOResponse res) {
		Optional<TabDocumentosAdjuntos> docTabDocumentoAdjunto = tabDocumentosAdjuntosRepository
				.findByDocumentoHash(hashDocumento);
		if (!docTabDocumentoAdjunto.isPresent()) {
			res.setMessage(
					"No se pudo encontrar el documento de la tabla de tablero que corresponde al archivo que se está firmando");
			res.setStatus("fail");
			return null;
		}
		return docTabDocumentoAdjunto.get();
	}
	
	public List<TabDocumentosAdjuntos> getTabDocumentosAdjuntos(TabDocumentos documento) {
		return tabDocumentosAdjuntosRepository.findByIdDocument(documento);
	}
	
	public List<TabDocsFirmantes> getTabDocsFirmantes(int idTabDocumento) {
		List<TabDocsFirmantes> listTabDocFirmantes = tabDocsFirmantesRepository.findByIdDocumento(idTabDocumento);
		InstEmpleado empleado = listTabDocFirmantes.get(0).getIntEmpleado();
		return listTabDocFirmantes;
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
	
	public InstEmpleado findEmpleado(int idEmpleado, DTOResponse res) {
		Optional<InstEmpleado> empleadoFirmante = instEmpleadoRepository.findById(idEmpleado);
		if (empleadoFirmante.isPresent()) {
			return empleadoFirmante.get();
		}
		res.setMessage("No se encontró el empleado con número de identificación: " + idEmpleado);
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
	
	public boolean documentNotExistInDataBase(String docBase64, int pos, DTOResponse res) {
		utils = new CertificateUtils();
		try {
			Optional<TabDocumentosAdjuntos> documentInDB = tabDocumentosAdjuntosRepository
					.findByDocumentoHash(utils.calcularSHA256(new String(docBase64)));
			if (documentInDB.isPresent()) {
				res.setMessage("El archivo adjunto número " + pos
						+ " que cargaste, ya existe en la BD, no se pueden duplicar documentos");
				res.setStatus("fail");
				return false;
			}
			return true;
		} catch (Exception e) {
			res.setMessage(
					"La BD está corrupta. Contiene dos o más archivos repetidos iguales al archivo adjunto número "
							+ pos + " que cargaste");
			res.setStatus("fail");
			return false;
		}
	}

}
