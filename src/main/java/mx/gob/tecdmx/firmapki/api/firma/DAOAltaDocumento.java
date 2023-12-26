package mx.gob.tecdmx.firmapki.api.firma;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.tecdmx.firmapki.api.documento.DTOConfiguracion;
import mx.gob.tecdmx.firmapki.api.documento.DTODocAdjunto;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;

public class DAOAltaDocumento {

	String folioEspecial;
	TabExpedientes expediente;
	TabCatDestinoDocumento destinoDoc;
	TabCatTipoDocumento tipoDoc;
	TabCatPrioridad prioridad;
	String asunto;
	String notas;
	String contenido;
	Date fechaLimiteFirma;
	List<DTOConfiguracion> configuraciones;
	List<String> notificaciones;
	boolean  enOrden;
	List<DAOFirmanteDestinatario> firmantes = new ArrayList<DAOFirmanteDestinatario>();
	List<DAOFirmanteDestinatario> destinatarios = new ArrayList<DAOFirmanteDestinatario>();
	List<DTODocAdjunto> documentosAdjuntos;
	
	public DAOAltaDocumento(String folioEspecial, String asunto, String notas, String contenido,
			Date fechaLimiteFirma, boolean enOrden) {
		super();
		this.folioEspecial = folioEspecial;
		this.asunto = asunto;
		this.notas = notas;
		this.contenido = contenido;
		this.fechaLimiteFirma = fechaLimiteFirma;
		this.enOrden = enOrden;
	}
	
	public DAOAltaDocumento() {
		// TODO Auto-generated constructor stub
	}

	public String getFolioEspecial() {
		return folioEspecial;
	}
	public void setFolioEspecial(String folioEspecial) {
		this.folioEspecial = folioEspecial;
	}
	public TabExpedientes getExpediente() {
		return expediente;
	}
	public void setExpediente(TabExpedientes expediente) {
		this.expediente = expediente;
	}
	public TabCatDestinoDocumento getDestinoDoc() {
		return destinoDoc;
	}
	public void setDestinoDoc(TabCatDestinoDocumento destinoDoc) {
		this.destinoDoc = destinoDoc;
	}
	public TabCatTipoDocumento getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(TabCatTipoDocumento tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public TabCatPrioridad getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(TabCatPrioridad prioridad) {
		this.prioridad = prioridad;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFechaLimiteFirma() {
		return fechaLimiteFirma;
	}
	public void setFechaLimiteFirma(Date fechaLimiteFirma) {
		this.fechaLimiteFirma = fechaLimiteFirma;
	}
	public List<DTOConfiguracion> getConfiguraciones() {
		return configuraciones;
	}
	public void setConfiguraciones(List<DTOConfiguracion> configuraciones) {
		this.configuraciones = configuraciones;
	}
	public List<String> getNotificaciones() {
		return notificaciones;
	}
	public void setNotificaciones(List<String> notificaciones) {
		this.notificaciones = notificaciones;
	}
	public boolean isEnOrden() {
		return enOrden;
	}
	public void setEnOrden(boolean enOrden) {
		this.enOrden = enOrden;
	}
	public List<DAOFirmanteDestinatario> getFirmantes() {
		return firmantes;
	}
	public void setFirmantes(List<DAOFirmanteDestinatario> firmantes) {
		this.firmantes = firmantes;
	}
	public List<DAOFirmanteDestinatario> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(List<DAOFirmanteDestinatario> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public List<DTODocAdjunto> getDocumentosAdjuntos() {
		return documentosAdjuntos;
	}
	public void setDocumentosAdjuntos(List<DTODocAdjunto> documentosAdjuntos) {
		this.documentosAdjuntos = documentosAdjuntos;
	}
	
	
	
}
