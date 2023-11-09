package mx.gob.tecdmx.firmapki.entity.pki;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;
@Entity
@Table(name = "pki_usuarios_cert", schema = "public")
@IdClass(IdUsuarioFirmaX509SerialNumber.class)
public class PkiUsuariosCert {
	@Id
	@Column(name="n_id_usuario_firma")  
	int idUsuarioFirma;
  
	@Id
	@Column(name="s_x509_serial_number")  
	String x509SerialNumber;
  
	@Column(name = "s_curp")
	String  curp;
  
	@Column(name = "s_rfc")
	String  rfc;
  
	@Column(name = "s_sha256_registro")
	String  sha256Registro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_usuario_firma", referencedColumnName="n_id_usuario", insertable = false, updatable = false)
	SegOrgUsuarios  usuario;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="s_x509_serial_number", referencedColumnName="s_x509_serial_number", insertable = false, updatable = false)
	PkiX509Registrados  pkix509Registrado;

	public int getIdUsuarioFirma() {
		return idUsuarioFirma;
	}

	public void setIdUsuarioFirma(int idUsuarioFirma) {
		this.idUsuarioFirma = idUsuarioFirma;
	}

	public String getX509SerialNumber() {
		return x509SerialNumber;
	}

	public void setX509SerialNumber(String x509SerialNumber) {
		this.x509SerialNumber = x509SerialNumber;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getSha256Registro() {
		return sha256Registro;
	}

	public void setSha256Registro(String sha256Registro) {
		this.sha256Registro = sha256Registro;
	}

	public SegOrgUsuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(SegOrgUsuarios usuario) {
		this.usuario = usuario;
	}

	public PkiX509Registrados getPkix509Registrado() {
		return pkix509Registrado;
	}

	public void setPkix509Registrado(PkiX509Registrados pkix509Registrado) {
		this.pkix509Registrado = pkix509Registrado;
	}

	
	
}