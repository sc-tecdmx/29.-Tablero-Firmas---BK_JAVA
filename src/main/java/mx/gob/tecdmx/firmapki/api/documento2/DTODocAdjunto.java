package mx.gob.tecdmx.firmapki.api.documento2;

public class DTODocAdjunto {

	String fileType;
	String docBase64;
	String filePath;
	String originalHash;
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getDocBase64() {
		return docBase64;
	}
	public void setDocBase64(String docBase64) {
		this.docBase64 = docBase64;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOriginalHash() {
		return originalHash;
	}
	public void setOriginalHash(String originalHash) {
		this.originalHash = originalHash;
	}
	
	
}
