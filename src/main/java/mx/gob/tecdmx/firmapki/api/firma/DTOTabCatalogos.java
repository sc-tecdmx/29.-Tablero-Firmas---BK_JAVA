package mx.gob.tecdmx.firmapki.api.firma;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;

public class DTOTabCatalogos {

	TabExpedientes expediente;
	TabCatTipoDocumento tipoDoc;
	TabCatDestinoDocumento destinoDoc;
	TabCatPrioridad prioridad;
	TabCatEtapaDocumento etapaDoc;
	
	public TabExpedientes getExpediente() {
		return expediente;
	}
	public void setExpediente(TabExpedientes expediente) {
		this.expediente = expediente;
	}
	public TabCatTipoDocumento getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(TabCatTipoDocumento tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public TabCatDestinoDocumento getDestinoDoc() {
		return destinoDoc;
	}
	public void setDestinoDoc(TabCatDestinoDocumento destinoDoc) {
		this.destinoDoc = destinoDoc;
	}
	public TabCatPrioridad getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(TabCatPrioridad prioridad) {
		this.prioridad = prioridad;
	}
	public TabCatEtapaDocumento getEtapaDoc() {
		return etapaDoc;
	}
	public void setEtapaDoc(TabCatEtapaDocumento etapaDoc) {
		this.etapaDoc = etapaDoc;
	}
	
	
}
