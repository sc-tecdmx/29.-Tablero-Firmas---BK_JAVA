package mx.gob.tecdmx.firmapki.api.populate;

public class PayloadAgregarCertificado {
	
	byte[] certificate;
	String tipoCertificado;
	
	public byte[] getCertificate() {
		return certificate;
	}
	public void setCertificate(byte[] certificate) {
		this.certificate = certificate;
	}
	public String getTipoCertificado() {
		return tipoCertificado;
	}
	public void setTipoCertificado(String tipoCertificado) {
		this.tipoCertificado = tipoCertificado;
	}
	
	

}
