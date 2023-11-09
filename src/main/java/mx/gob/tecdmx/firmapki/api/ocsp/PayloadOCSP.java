package mx.gob.tecdmx.firmapki.api.ocsp;

public class PayloadOCSP {
	int idTransaccion;
	byte[] certificado;
	public int getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public byte[] getCertificado() {
		return certificado;
	}
	public void setCertificado(byte[] certificado) {
		this.certificado = certificado;
	}
	
	
}
