package mx.gob.tecdmx.firmapki.entity.tab;

import java.io.Serializable;

public class IddocumentoTipoID implements Serializable {
	
    private Integer idDocumento;
    private String tipo;
    
	public Integer getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
