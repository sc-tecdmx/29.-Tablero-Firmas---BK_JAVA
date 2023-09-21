package mx.gob.tecdmx.firmaocsp.dto;

public class PayloadOCSP {
	byte[] certificate;
	String fileName;
	public byte[] getCertificate() {
		return certificate;
	}
	public void setCertificate(byte[] certificate) {
		this.certificate = certificate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
