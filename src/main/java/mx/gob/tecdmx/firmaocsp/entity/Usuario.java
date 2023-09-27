package mx.gob.tecdmx.firmaocsp.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", schema = "public")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	int id;

	@Column(name = "nombre")
	String nombre;

	@Column(name = "apellidop")
	String apellidop;

	@Column(name = "apellidom")
	String apellidom;
	
	@Column(name = "correo")
	String correo;

	@Column(name = "id_cargo")
	int idCcargo;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidop() {
		return apellidop;
	}

	public void setApellidop(String apellidop) {
		this.apellidop = apellidop;
	}

	public String getApellidom() {
		return apellidom;
	}

	public void setApellidom(String apellidom) {
		this.apellidom = apellidom;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getIdCcargo() {
		return idCcargo;
	}

	public void setIdCcargo(int idCcargo) {
		this.idCcargo = idCcargo;
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
