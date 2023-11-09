package mx.gob.tecdmx.firmapki.entity.tab;

import java.io.Serializable;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;

public class IddocumentoIdnumeEmpleadoID implements Serializable {
	 
    private int idDocumento;
    private int idNumEmpleado;
	public int getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}
	public int getIdNumEmpleado() {
		return idNumEmpleado;
	}
	public void setIdNumEmpleado(int idNumEmpleado) {
		this.idNumEmpleado = idNumEmpleado;
	}
    
	
}
