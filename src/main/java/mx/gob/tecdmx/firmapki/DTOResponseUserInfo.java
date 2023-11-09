package mx.gob.tecdmx.firmapki;



public class DTOResponseUserInfo {
	String status;
	String message;
	DTOUserInfo data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DTOUserInfo getData() {
		return data;
	}
	public void setData(DTOUserInfo data) {
		this.data = data;
	}
	
}
