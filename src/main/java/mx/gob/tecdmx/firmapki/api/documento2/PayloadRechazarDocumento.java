package mx.gob.tecdmx.firmapki.api.documento2;

public class PayloadRechazarDocumento {
	int idDocumento;
    String codigoFirmaAplicada;
    String tipoUsuario;
    String descripcion;
  
	public int getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getCodigoFirmaAplicada() {
		return codigoFirmaAplicada;
	}
	public void setCodigoFirmaAplicada(String codigoFirmaAplicada) {
		this.codigoFirmaAplicada = codigoFirmaAplicada;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}