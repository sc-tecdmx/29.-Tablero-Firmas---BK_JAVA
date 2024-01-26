package mx.gob.tecdmx.firmapki.api.documento2;

public class PayloadFirma {
	int idTransaccion;
    String hashDocumento;
    String codigoFirmaAplicada;
    byte[] certificado;
    byte[] cadenaFirma;
    byte[] documento;
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
	public String getCodigoFirmaAplicada() {
		return codigoFirmaAplicada;
	}
	public void setCodigoFirmaAplicada(String codigoFirmaAplicada) {
		this.codigoFirmaAplicada = codigoFirmaAplicada;
	}
	public byte[] getCertificado() {
		return certificado;
	}
	public void setCertificado(byte[] certificado) {
		this.certificado = certificado;
	}
	public byte[] getCadenaFirma() {
		return cadenaFirma;
	}
	public void setCadenaFirma(byte[] cadenaFirma) {
		this.cadenaFirma = cadenaFirma;
	}
	public byte[] getDocumento() {
		return documento;
	}
	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}
    
}
