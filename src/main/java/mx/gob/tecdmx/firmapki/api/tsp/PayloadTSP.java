package mx.gob.tecdmx.firmapki.api.tsp;

public class PayloadTSP {

	int idTransaccion;
	String messageDigest;
	
	public int getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getMessageDigest() {
		return messageDigest;
	}
	public void setMessageDigest(String messageDigest) {
		this.messageDigest = messageDigest;
	}
	
}
