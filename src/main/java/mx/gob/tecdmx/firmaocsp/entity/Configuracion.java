package mx.gob.tecdmx.firmaocsp.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuracion_firma", schema = "public")
public class Configuracion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	int id;

	@Column(name = "orden_firma")
	String ordenFirma;

	@Column(name = "modo_captura")
	boolean modoCaptura;

	@Column(name = "genera_numero_oficio")
	String generaNumeroOficio;

	@Column(name = "fecha_limite")
	String fechaLimite;

	@Column(name = "documento_firmar")
	String documentoFirmar;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getDocumentoFirmar() {
		return documentoFirmar;
	}

	public void setDocumentoFirmar(String documentoFirmar) {
		this.documentoFirmar = documentoFirmar;
	}

	

}
