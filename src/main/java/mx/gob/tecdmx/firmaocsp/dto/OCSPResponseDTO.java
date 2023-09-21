package mx.gob.tecdmx.firmaocsp.dto;

import org.bouncycastle.cert.ocsp.BasicOCSPResp;

public class OCSPResponseDTO {
	String status;
	String urlOCSP;
	String issuerCert;
	String caCert;
	String certificate;
	String serialNumber;
	String basicResponse;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrlOCSP() {
		return urlOCSP;
	}
	public void setUrlOCSP(String urlOCSP) {
		this.urlOCSP = urlOCSP;
	}
	public String getIssuerCert() {
		return issuerCert;
	}
	public void setIssuerCert(String issuerCert) {
		this.issuerCert = issuerCert;
	}
	public String getCaCert() {
		return caCert;
	}
	public void setCaCert(String caCert) {
		this.caCert = caCert;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getBasicResponse() {
		return basicResponse;
	}
	public void setBasicResponse(String basicResponse) {
		this.basicResponse = basicResponse;
	}
	@Override
	public String toString() {
		return "OCSPResponseDTO [status=" + status + ", urlOCSP=" + urlOCSP + ", issuerCert=" + issuerCert + ", caCert="
				+ caCert + ", certificate=" + certificate + ", serialNumber=" + serialNumber + "]";
	}
	
	
	
}
