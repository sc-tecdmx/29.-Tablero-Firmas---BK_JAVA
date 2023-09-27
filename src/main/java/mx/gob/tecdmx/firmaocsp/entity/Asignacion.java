package mx.gob.tecdmx.firmaocsp.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asignacion", schema = "public")
public class Asignacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_documento", referencedColumnName="id", insertable=false, updatable=false)
	Documento idDocumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario", referencedColumnName="id", insertable=false, updatable=false)
	Usuario idUsuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_accion", referencedColumnName="id", insertable=false, updatable=false)
	CatAccion idAccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_estadoaccion", referencedColumnName="id", insertable=false, updatable=false)
	CatEstado idEstadoaccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipoasociacion", referencedColumnName="id", insertable=false, updatable=false)
	CatTipoAsociacion idTipoasociacion;
	
	@Column(name = "obligatorio")
	Boolean obligatorio;

	@Column(name = "activo")
	Boolean activo;

	@Column(name = "fh_creacion")
	Date fhCreacion;

	@Column(name = "fh_modificacion")
	Date fhModificacion;

	@Column(name = "usu_creacion")
	int usuCreacion;

	@Column(name = "usu_modificacion")
	int usuModificacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Documento getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Documento idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public CatAccion getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(CatAccion idAccion) {
		this.idAccion = idAccion;
	}

	public CatEstado getIdEstadoaccion() {
		return idEstadoaccion;
	}

	public void setIdEstadoaccion(CatEstado idEstadoaccion) {
		this.idEstadoaccion = idEstadoaccion;
	}

	public CatTipoAsociacion getIdTipoasociacion() {
		return idTipoasociacion;
	}

	public void setIdTipoasociacion(CatTipoAsociacion idTipoasociacion) {
		this.idTipoasociacion = idTipoasociacion;
	}

	public Boolean getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(Boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public int getUsuCreacion() {
		return usuCreacion;
	}

	public void setUsuCreacion(int usuCreacion) {
		this.usuCreacion = usuCreacion;
	}

	public int getUsuModificacion() {
		return usuModificacion;
	}

	public void setUsuModificacion(int usuModificacion) {
		this.usuModificacion = usuModificacion;
	}

	

}
