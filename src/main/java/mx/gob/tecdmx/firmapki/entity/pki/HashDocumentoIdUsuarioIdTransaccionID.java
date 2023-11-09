package mx.gob.tecdmx.firmapki.entity.pki;

import java.io.Serializable;

public class HashDocumentoIdUsuarioIdTransaccionID implements Serializable {
	
    private String hashDocumento;
    private int idUsuario;
//    private int idTransaccion;
    
	public String getHashDocumento() {
		return hashDocumento;
	}
	public void setHashDocumento(String hashDocumento) {
		this.hashDocumento = hashDocumento;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
//	public int getIdTransaccion() {
//		return idTransaccion;
//	}
//	public void setIdTransaccion(int idTransaccion) {
//		this.idTransaccion = idTransaccion;
//	}
    
	
   
}