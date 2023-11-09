package mx.gob.tecdmx.firmapki.api.tsp;

import java.util.Date;

public class ResponseBodyTSP {
	Date fechaUTC;
	String nombreRespondedor;
	String emisorRespondedor;
	String secuencia;
	String datosEstampillados;
	public Date getFechaUTC() {
		return fechaUTC;
	}
	public void setFechaUTC(Date fechaUTC) {
		this.fechaUTC = fechaUTC;
	}
	public String getNombreRespondedor() {
		return nombreRespondedor;
	}
	public void setNombreRespondedor(String nombreRespondedor) {
		this.nombreRespondedor = nombreRespondedor;
	}
	public String getEmisorRespondedor() {
		return emisorRespondedor;
	}
	public void setEmisorRespondedor(String emisorRespondedor) {
		this.emisorRespondedor = emisorRespondedor;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getDatosEstampillados() {
		return datosEstampillados;
	}
	public void setDatosEstampillados(String datosEstampillados) {
		this.datosEstampillados = datosEstampillados;
	}
	
	
}
