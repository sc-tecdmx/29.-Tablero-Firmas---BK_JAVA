package mx.gob.tecdmx.firmapki.api.documento;

import java.util.Date;
import java.util.List;

public class PayloadDocumento {
	
	String hashDocumento;
	int idNumEmpleadoCreador;
	int idNumEmpleadoEnvio;
	Date fechaCreacion;
	Date fechaEnvio;
	boolean  enOrden;
	List<DTOFirmanteDestinatario> firmantes;

	public String getHashDocumento() {
		return hashDocumento;
	}
	public void setHashDocumento(String hashDocumento) {
		this.hashDocumento = hashDocumento;
	}
	public int getIdNumEmpleadoCreador() {
		return idNumEmpleadoCreador;
	}
	public void setIdNumEmpleadoCreador(int idNumEmpleadoCreador) {
		this.idNumEmpleadoCreador = idNumEmpleadoCreador;
	}
	public int getIdNumEmpleadoEnvio() {
		return idNumEmpleadoEnvio;
	}
	public void setIdNumEmpleadoEnvio(int idNumEmpleadoEnvio) {
		this.idNumEmpleadoEnvio = idNumEmpleadoEnvio;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public boolean isEnOrden() {
		return enOrden;
	}
	public void setEnOrden(boolean enOrden) {
		this.enOrden = enOrden;
	}
	public List<DTOFirmanteDestinatario> getFirmantes() {
		return firmantes;
	}
	public void setFirmantes(List<DTOFirmanteDestinatario> firmantes) {
		this.firmantes = firmantes;
	}
	
	
}
