package mx.gob.tecdmx.firmapki.api.ocsp;

import java.util.Date;

public class ResponseBodyOCSP {
	Date fechaUTC;
	String nombreRespondedor;
	String emisorRespondedor;
	String numeroSerie;
	String indicador;
	
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
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
	
}
