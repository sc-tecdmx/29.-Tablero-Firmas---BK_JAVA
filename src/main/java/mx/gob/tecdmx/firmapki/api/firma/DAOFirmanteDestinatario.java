package mx.gob.tecdmx.firmapki.api.firma;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstDest;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;

public class DAOFirmanteDestinatario {
	int secuencia;
	InstEmpleado empleado;
	TabCatInstFirmantes instruccionFirmante;
	TabCatInstDest instruccionDest;
	
	public int getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}
	public InstEmpleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(InstEmpleado empleado) {
		this.empleado = empleado;
	}
	public TabCatInstFirmantes getInstruccionFirmante() {
		return instruccionFirmante;
	}
	public void setInstruccionFirmante(TabCatInstFirmantes instruccionFirmante) {
		this.instruccionFirmante = instruccionFirmante;
	}
	public TabCatInstDest getInstruccionDest() {
		return instruccionDest;
	}
	public void setInstruccionDest(TabCatInstDest instruccionDest) {
		this.instruccionDest = instruccionDest;
	}
	
	
	
		
}
