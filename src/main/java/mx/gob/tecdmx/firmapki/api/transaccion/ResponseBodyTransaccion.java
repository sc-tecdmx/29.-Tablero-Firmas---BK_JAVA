package mx.gob.tecdmx.firmapki.api.transaccion;

public class ResponseBodyTransaccion {
	int idTransaccion;
	String numeroSerie;
	int transaccionBlock;
	
	public int getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public int getTransaccionBlock() {
		return transaccionBlock;
	}
	public void setTransaccionBlock(int transaccionBlock) {
		this.transaccionBlock = transaccionBlock;
	}
	
	
}
