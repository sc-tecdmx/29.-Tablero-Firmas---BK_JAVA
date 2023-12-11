package mx.gob.tecdmx.firmapki.entity.pki;

import java.io.Serializable;

public class IdUsuarioFirmaX509SerialNumber implements Serializable {
	 
    private int idUsuarioFirma;
    private String x509SerialNumber;
    
	public int getIdUsuarioFirma() {
		return idUsuarioFirma;
	}
	public void setIdUsuarioFirma(int idUsuarioFirma) {
		this.idUsuarioFirma = idUsuarioFirma;
	}
	public String getX509SerialNumber() {
		return x509SerialNumber;
	}
	public void setX509SerialNumber(String x509SerialNumber) {
		this.x509SerialNumber = x509SerialNumber;
	}
	
    
	
}