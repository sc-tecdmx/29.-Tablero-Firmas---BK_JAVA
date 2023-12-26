package mx.gob.tecdmx.firmapki.api.firma;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatDestinoDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatEtapaDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatInstDestRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatInstFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatPrioridadRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatTipoDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabExpedientesRepository;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@Service
public class ServiceFindDataAndCatalogos {
	
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
	InstEmpleadoRepository instEmpleadoRepository;
	
	public TabExpedientes findNumExpediente(String numExpediente, DTOTabCatalogos tabCatalogos, DTOResponse res) {
		if (numExpediente != null) {
			Optional<TabExpedientes> numExpedienteOpt = tabExpedientesRepository.findByNumExpediente(numExpediente);
			if (numExpedienteOpt.isPresent()) {
				tabCatalogos.setExpediente(numExpedienteOpt.get());
				return numExpedienteOpt.get();
			}
		}
		res.setMessage("No se encontró el número de expediente "+numExpediente);
		res.setStatus("fail");
		return null;
	}

	public TabCatTipoDocumento findTipoDocumento(String tipoDoc, DTOTabCatalogos tabCatalogos, DTOResponse res) {
		if (tipoDoc != null) {
			Optional<TabCatTipoDocumento> tipoDocOpt = tabCatTipoDocumentoRepository.findByDescTipoDocumento(tipoDoc);
			if (tipoDocOpt.isPresent()) {
				tabCatalogos.setTipoDoc(tipoDocOpt.get());
				return tipoDocOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de documento "+tipoDoc);
		res.setStatus("fail");
		return null;
	}
	
	public TabCatDestinoDocumento findTipoDestino(String tipoDest, DTOTabCatalogos tabCatalogos, DTOResponse res) {
		if (tipoDest != null) {
			Optional<TabCatDestinoDocumento> tipoDestOpt = tabCatDestinoDocumentoRepository.findByDescDestinoDocumento(tipoDest);
			if (tipoDestOpt.isPresent()) {
				tabCatalogos.setDestinoDoc(tipoDestOpt.get());
				return tipoDestOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de destino "+tipoDest);
		res.setStatus("fail");
		return null;
	}
	
	public TabCatPrioridad findTipoPrioridad(String tipoPrioridad, DTOTabCatalogos tabCatalogos, DTOResponse res) {
		if (tipoPrioridad != null) {
			Optional<TabCatPrioridad> tipoPrioridadOpt = tabCatPrioridadRepository.findByDescPrioridad(tipoPrioridad);
			if (tipoPrioridadOpt.isPresent()) {
				tabCatalogos.setPrioridad(tipoPrioridadOpt.get());
				return tipoPrioridadOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de prioridad "+tipoPrioridad);
		res.setStatus("fail");
		return null;
	}
	
	public TabCatEtapaDocumento findEtapaDocumento(String etapaDoc, DTOTabCatalogos tabCatalogos, DTOResponse res) {
		if (etapaDoc != null) {
			Optional<TabCatEtapaDocumento> etapaDocOpt = tabCatEtapaDocumentoRepository.findByDescetapa(etapaDoc);
			if (etapaDocOpt.isPresent()) {
				tabCatalogos.setEtapaDoc(etapaDocOpt.get());
				return etapaDocOpt.get();
			}
		}
		res.setMessage("No se encontró la etapa del documento "+etapaDoc);
		res.setStatus("fail");
		return null;
	}
	
	public InstEmpleado findEmpleado(int idEmpleado, DTOResponse res) {
		Optional<InstEmpleado> empleadoFirmante = instEmpleadoRepository.findById(idEmpleado);
		if (empleadoFirmante.isPresent()) {
			return empleadoFirmante.get();
		}
		res.setMessage("No se encontró el empleado firmante con Id" + idEmpleado);
		res.setStatus("fail");
		return null;
	}
	
	public TabCatInstFirmantes findInstruccionFirmante(String instruccion, DTOResponse res) {
		Optional<TabCatInstFirmantes> instruccionFirmante = tabCatInstFirmantesRepository
				.findByDescInstrFirmante(instruccion);
		if (!instruccionFirmante.isPresent()) {
			return instruccionFirmante.get();
		}
		res.setMessage("No se encontró la instrucción " + instruccion);
		res.setStatus("fail");
		return null;
	}
	
	
	public boolean getDataCatalogosFirmarAhora(String numExpedienteInp, String tipoDocumentoInp, String tipoDestinoInp,
			String tipoPrioridadInp,
			DTOTabCatalogos tabCatalogos, DTOResponse res) {
		
		TabExpedientes numExpediente = findNumExpediente(numExpedienteInp, tabCatalogos, res);
		TabCatTipoDocumento tipoDOc = findTipoDocumento(tipoDocumentoInp, tabCatalogos, res);
		TabCatDestinoDocumento tipoDestino = findTipoDestino(tipoDestinoInp, tabCatalogos, res);
		TabCatPrioridad tipoPrioridad = findTipoPrioridad(tipoPrioridadInp, tabCatalogos, res);
		if(numExpediente==null||tipoDOc==null||tipoDestino==null||tipoPrioridad==null) {
			return false;
		}
		return true;
	}
	
}
