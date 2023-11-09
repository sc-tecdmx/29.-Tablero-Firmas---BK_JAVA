package mx.gob.tecdmx.firmapki.api.documento;

public class DTODocAdjunto {

	String fileType;
	byte[] docBase64;
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getDocBase64() {
		return docBase64;
	}
	public void setDocBase64(byte[] docBase64) {
		this.docBase64 = docBase64;
	}
	
}
