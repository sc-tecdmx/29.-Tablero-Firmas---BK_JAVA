package mx.gob.tecdmx.firmapki.entity.pki;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;

@Entity
@Table(name = "pki_documento_firmantes", schema = "public")
@IdClass(HashDocumentoIdUsuarioIdTransaccionID.class)
public class PkiDocumentoFirmantes {
	@Id
	@Column(name="s_hash_documento")  
	String hashDocumento;
	
	@Id
	@Column(name="n_id_usuario")
	int idUsuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_transaccion", referencedColumnName="n_id_transaccion")
	PkiTransaccion transaccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_num_empleado", referencedColumnName="n_id_num_empleado")
	InstEmpleado idNumEmpleado;
  
	@Column(name = "secuencia")
	int secuencia;
  
	@Column(name = "fecha_limite")
	Date fechaLimite;
  
	@Column(name = "fecha_firma")
	Date  fechaFirma;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_firma", referencedColumnName="id_tipo_firma")
	PkiCatTipoFirma  idTipoFirma;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_firma_aplicada", referencedColumnName="id_firma_aplicada")
	PkiCatFirmaAplicada  idFirmaAplicada;
	
	@Column(name = "s_cadena_firma")
	String  cadenaFirma;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="s_hash_documento", referencedColumnName="s_hash_documento", insertable = false, updatable = false)
	PkiDocumento documento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_usuario", referencedColumnName="n_id_usuario", insertable = false, updatable = false)
	SegOrgUsuarios usuario;

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

	public PkiTransaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(PkiTransaccion transaccion) {
		this.transaccion = transaccion;
	}

	public InstEmpleado getIdNumEmpleado() {
		return idNumEmpleado;
	}

	public void setIdNumEmpleado(InstEmpleado idNumEmpleado) {
		this.idNumEmpleado = idNumEmpleado;
	}

	public int getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Date getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(Date fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public PkiCatTipoFirma getIdTipoFirma() {
		return idTipoFirma;
	}

	public void setIdTipoFirma(PkiCatTipoFirma idTipoFirma) {
		this.idTipoFirma = idTipoFirma;
	}

	public PkiCatFirmaAplicada getIdFirmaAplicada() {
		return idFirmaAplicada;
	}

	public void setIdFirmaAplicada(PkiCatFirmaAplicada idFirmaAplicada) {
		this.idFirmaAplicada = idFirmaAplicada;
	}

	public String getCadenaFirma() {
		return cadenaFirma;
	}

	public void setCadenaFirma(String cadenaFirma) {
		this.cadenaFirma = cadenaFirma;
	}

	public PkiDocumento getDocumento() {
		return documento;
	}

	public void setDocumento(PkiDocumento documento) {
		this.documento = documento;
	}

	public SegOrgUsuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(SegOrgUsuarios usuario) {
		this.usuario = usuario;
	}

	
	
}