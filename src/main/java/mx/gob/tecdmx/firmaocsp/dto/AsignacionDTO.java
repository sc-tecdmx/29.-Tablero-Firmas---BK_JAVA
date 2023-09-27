package mx.gob.tecdmx.firmaocsp.dto;

public class AsignacionDTO {
	String correo;
	String accionCode;
	boolean obligatorio;
	String estadoAccionCode;
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getAccionCode() {
		return accionCode;
	}
	public void setAccionCode(String accionCode) {
		this.accionCode = accionCode;
	}
	public boolean isObligatorio() {
		return obligatorio;
	}
	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}
	public String getEstadoAccionCode() {
		return estadoAccionCode;
	}
	public void setEstadoAccionCode(String estadoAccionCode) {
		this.estadoAccionCode = estadoAccionCode;
	}
	
	
	

}
