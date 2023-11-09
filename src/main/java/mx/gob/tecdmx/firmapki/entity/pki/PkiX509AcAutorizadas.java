package mx.gob.tecdmx.firmapki.entity.pki;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "pki_x509_ac_autorizadas", schema = "public")
public class PkiX509AcAutorizadas {
	@Id
	@Column(name = "s_x509_emisor_serial", unique = true, nullable = false)
	String  id;
  
	@Column(name = "s_x509_ac_der_b64")
	String  x509AcDerB64;
  
	@Column(name = "s_x509_emisor_autoridad")
	String  x509EmisorAutoridad;
  
	@Column(name = "s_tipo_certificado")
	String  tipoCertificado;
	
	@Column(name = "s_url")
	String  url;
  
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="x509EmisorSerialParent", referencedColumnName="s_x509_emisor_serial")  
	PkiX509AcAutorizadas  x509EmisorSerialParent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getX509AcDerB64() {
		return x509AcDerB64;
	}

	public void setX509AcDerB64(String x509AcDerB64) {
		this.x509AcDerB64 = x509AcDerB64;
	}

	public String getX509EmisorAutoridad() {
		return x509EmisorAutoridad;
	}

	public void setX509EmisorAutoridad(String x509EmisorAutoridad) {
		this.x509EmisorAutoridad = x509EmisorAutoridad;
	}

	public String getTipoCertificado() {
		return tipoCertificado;
	}

	public void setTipoCertificado(String tipoCertificado) {
		this.tipoCertificado = tipoCertificado;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PkiX509AcAutorizadas getX509EmisorSerialParent() {
		return x509EmisorSerialParent;
	}

	public void setX509EmisorSerialParent(PkiX509AcAutorizadas x509EmisorSerialParent) {
		this.x509EmisorSerialParent = x509EmisorSerialParent;
	}

	
	
}