package mx.gob.tecdmx.firmaocsp.dto;

public class TsaDTO {
	String timestamp; //19/09/2023 09:01:31 a. m.
	String status;
	String hashAlgorithm;
	String certificate;
	String tst;
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getTst() {
		return tst;
	}
	public void setTst(String tst) {
		this.tst = tst;
	}
	public String getHashAlgorithm() {
		return hashAlgorithm;
	}
	public void setHashAlgorithm(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}
	
	

}
