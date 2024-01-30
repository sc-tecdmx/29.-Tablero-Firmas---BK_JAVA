package mx.gob.tecdmx.firmapki.api.firma;

import java.util.Date;

public class ResponseBodyFirma {
	Date fechaUTC;
	String algortimo;
	String serie;
	String cadenaFirma;
	
	public Date getFechaUTC() {
		return fechaUTC;
	}
	public void setFechaUTC(Date fechaUTC) {
		this.fechaUTC = fechaUTC;
	}
	public String getAlgortimo() {
		return algortimo;
	}
	public void setAlgortimo(String algortimo) {
		this.algortimo = algortimo;
	}
	public String getCadenaFirma() {
		return cadenaFirma;
	}
	public void setCadenaFirma(String cadenaFirma) {
		this.cadenaFirma = cadenaFirma;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	
}
