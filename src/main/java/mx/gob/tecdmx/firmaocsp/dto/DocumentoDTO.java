package mx.gob.tecdmx.firmaocsp.dto;

import java.util.List;

import mx.gob.tecdmx.firmaocsp.entity.Configuracion;
import mx.gob.tecdmx.firmaocsp.entity.Asignacion;

public class DocumentoDTO {

	String idDocumento;
	String tipoUsoCode;
	String areaDestinoCode;
	String areaCopiaCode;
	String tipoDocumentoCode;
	String folio;
	String folioDocumento;
	String numeroExpediente;
	String nombreExpediente;
	String fechaDocumento;
	String destinatario;
	String cargo;
	String elaboro;
	String asunto;
	String contenido;
	String nota;
	String documento;
	String estadoDocumentoCode;
	List<AsignacionDTO> firmantes;
	List<AsignacionDTO> destinatarios;
	ConfiguracionDTO configuracion;
	
	public String getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getTipoUsoCode() {
		return tipoUsoCode;
	}
	public void setTipoUsoCode(String tipoUsoCode) {
		this.tipoUsoCode = tipoUsoCode;
	}
	public String getAreaDestinoCode() {
		return areaDestinoCode;
	}
	public void setAreaDestinoCode(String areaDestinoCode) {
		this.areaDestinoCode = areaDestinoCode;
	}
	public String getAreaCopiaCode() {
		return areaCopiaCode;
	}
	public void setAreaCopiaCode(String areaCopiaCode) {
		this.areaCopiaCode = areaCopiaCode;
	}
	public String getTipoDocumentoCode() {
		return tipoDocumentoCode;
	}
	public void setTipoDocumentoCode(String tipoDocumentoCode) {
		this.tipoDocumentoCode = tipoDocumentoCode;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFolioDocumento() {
		return folioDocumento;
	}
	public void setFolioDocumento(String folioDocumento) {
		this.folioDocumento = folioDocumento;
	}
	public String getNumeroExpediente() {
		return numeroExpediente;
	}
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	public String getNombreExpediente() {
		return nombreExpediente;
	}
	public void setNombreExpediente(String nombreExpediente) {
		this.nombreExpediente = nombreExpediente;
	}
	public String getFechaDocumento() {
		return fechaDocumento;
	}
	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getElaboro() {
		return elaboro;
	}
	public void setElaboro(String elaboro) {
		this.elaboro = elaboro;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getEstadoDocumentoCode() {
		return estadoDocumentoCode;
	}
	public void setEstadoDocumentoCode(String estadoDocumentoCode) {
		this.estadoDocumentoCode = estadoDocumentoCode;
	}
	public List<AsignacionDTO> getFirmantes() {
		return firmantes;
	}
	public void setFirmantes(List<AsignacionDTO> firmantes) {
		this.firmantes = firmantes;
	}
	public List<AsignacionDTO> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(List<AsignacionDTO> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public ConfiguracionDTO getConfiguracion() {
		return configuracion;
	}
	public void setConfiguracion(ConfiguracionDTO configuracion) {
		this.configuracion = configuracion;
	}
	
	
	
	
}
