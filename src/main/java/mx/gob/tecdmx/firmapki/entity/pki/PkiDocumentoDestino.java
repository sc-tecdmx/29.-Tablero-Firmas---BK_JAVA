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
@Table(name = "pki_documento_destinos", schema = "public")
@IdClass(HashDocumentoIdUsuarioIdTransaccionID.class)
public class PkiDocumentoDestino {
	@Id
	@Column(name="s_hash_documento")  
	String hashDocumento;
	
	@Id
	@Column(name="n_id_usuario")
	int idUsuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_transaccion", referencedColumnName="n_id_transaccion")
	PkiTransaccion  idTransaccion;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_num_empleado", referencedColumnName="n_id_num_empleado")
	InstEmpleado  idNumEmpleado;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_instruccion_doc", referencedColumnName="id_instruccion_doc")
	PkiCatInstruccionDoc  idInstruccionDoc;
  
	@Column(name = "fecha_notificacion")
	Date  fechaNotificacion;
  
	@Column(name = "fecha_lectura")
	Date  fechaLectura;
  
	@Column(name = "fecha_acuse")
	Date  fechaAcuse;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_firma_aplicada", referencedColumnName="id_firma_aplicada")
	PkiCatFirmaAplicada  idFirmaAplicada;
	
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

	public PkiTransaccion getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(PkiTransaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public InstEmpleado getIdNumEmpleado() {
		return idNumEmpleado;
	}

	public void setIdNumEmpleado(InstEmpleado idNumEmpleado) {
		this.idNumEmpleado = idNumEmpleado;
	}

	public PkiCatInstruccionDoc getIdInstruccionDoc() {
		return idInstruccionDoc;
	}

	public void setIdInstruccionDoc(PkiCatInstruccionDoc idInstruccionDoc) {
		this.idInstruccionDoc = idInstruccionDoc;
	}

	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public Date getFechaLectura() {
		return fechaLectura;
	}

	public void setFechaLectura(Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}

	public Date getFechaAcuse() {
		return fechaAcuse;
	}

	public void setFechaAcuse(Date fechaAcuse) {
		this.fechaAcuse = fechaAcuse;
	}

	public PkiCatFirmaAplicada getIdFirmaAplicada() {
		return idFirmaAplicada;
	}

	public void setIdFirmaAplicada(PkiCatFirmaAplicada idFirmaAplicada) {
		this.idFirmaAplicada = idFirmaAplicada;
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