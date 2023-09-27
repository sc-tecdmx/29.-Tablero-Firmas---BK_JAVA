package mx.gob.tecdmx.firmaocsp.dto;

public class ConfiguracionDTO {

	String ordenFirma;
	boolean modoCaptura;
	String generaNumeroOficio;
	String fechaLimite;
	String documento;

	public String getOrdenFirma() {
		return ordenFirma;
	}

	public void setOrdenFirma(String ordenFirma) {
		this.ordenFirma = ordenFirma;
	}

	public boolean isModoCaptura() {
		return modoCaptura;
	}

	public void setModoCaptura(boolean modoCaptura) {
		this.modoCaptura = modoCaptura;
	}

	public String getGeneraNumeroOficio() {
		return generaNumeroOficio;
	}

	public void setGeneraNumeroOficio(String generaNumeroOficio) {
		this.generaNumeroOficio = generaNumeroOficio;
	}

	public String getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumentos(String documentos) {
		this.documento = documentos;
	}

}
