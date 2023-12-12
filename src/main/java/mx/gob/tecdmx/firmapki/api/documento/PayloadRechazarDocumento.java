package mx.gob.tecdmx.firmapki.api.documento;

public class PayloadRechazarDocumento {
	int idDocumento;
    String codigoFirmaAplicada;
  
	public int getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getCodigoFirmaAplicada() {
		return codigoFirmaAplicada;
	}
	public void setCodigoFirmaAplicada(String codigoFirmaAplicada) {
		this.codigoFirmaAplicada = codigoFirmaAplicada;
	}
    
    
}
