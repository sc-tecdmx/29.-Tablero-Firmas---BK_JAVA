package mx.gob.tecdmx.firmapki.api.documento;

public class PayloadFirma {
	int idTransaccion;
    String hashDocumento;
    int idFirmaAplicada;
    String cadenaFirma;
    String documentoFirmado;
    
	public int getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getHashDocumento() {
		return hashDocumento;
	}
	public void setHashDocumento(String hashDocumento) {
		this.hashDocumento = hashDocumento;
	}
	public int getIdFirmaAplicada() {
		return idFirmaAplicada;
	}
	public void setIdFirmaAplicada(int idFirmaAplicada) {
		this.idFirmaAplicada = idFirmaAplicada;
	}
	public String getCadenaFirma() {
		return cadenaFirma;
	}
	public void setCadenaFirma(String cadenaFirma) {
		this.cadenaFirma = cadenaFirma;
	}
	public String getDocumentoFirmado() {
		return documentoFirmado;
	}
	public void setDocumentoFirmado(String documentoFirmado) {
		this.documentoFirmado = documentoFirmado;
	}
    
	

}
