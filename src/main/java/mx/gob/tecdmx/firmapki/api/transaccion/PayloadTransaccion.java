package mx.gob.tecdmx.firmapki.api.transaccion;

public class PayloadTransaccion {
	byte[] certificado;
	String hashDocumento;

	public byte[] getCertificado() {
		return certificado;
	}

	public void setCertificado(byte[] certificado) {
		this.certificado = certificado;
	}

	public String getHashDocumento() {
		return hashDocumento;
	}

	public void setHashDocumento(String hashDocumento) {
		this.hashDocumento = hashDocumento;
	}
	

}
