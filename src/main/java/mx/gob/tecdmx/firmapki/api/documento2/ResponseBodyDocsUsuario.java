package mx.gob.tecdmx.firmapki.api.documento2;

import java.util.Date;
import java.util.List;

import mx.gob.tecdmx.firmapki.utils.dto.DTODocAdjunto;

public class ResponseBodyDocsUsuario {
	int idDocumento;
	String folioDocumento;
	String etapa;
	String prioridad;
	Date creacionDocumentoFecha;
	String asunto;
	String tipo;
	List<DTOEmpleado> firmantes;
	List<DTOEmpleado> destinatarios;
	List<DTODocAdjunto> documentosAdjuntos;
	Boolean statusFirma;
	
	public int getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getFolioDocumento() {
		return folioDocumento;
	}
	public void setFolioDocumento(String folioDocumento) {
		this.folioDocumento = folioDocumento;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public Date getCreacionDocumentoFecha() {
		return creacionDocumentoFecha;
	}
	public void setCreacionDocumentoFecha(Date creacionDocumentoFecha) {
		this.creacionDocumentoFecha = creacionDocumentoFecha;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<DTOEmpleado> getFirmantes() {
		return firmantes;
	}
	public void setFirmantes(List<DTOEmpleado> firmantes) {
		this.firmantes = firmantes;
	}
	public List<DTOEmpleado> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(List<DTOEmpleado> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public List<DTODocAdjunto> getDocumentosAdjuntos() {
		return documentosAdjuntos;
	}
	public void setDocumentosAdjuntos(List<DTODocAdjunto> documentosAdjuntos) {
		this.documentosAdjuntos = documentosAdjuntos;
	}
	public Boolean getStatusFirma() {
		return statusFirma;
	}
	public void setStatusFirma(Boolean statusFirma) {
		this.statusFirma = statusFirma;
	}
	
}
