package mx.gob.tecdmx.firmapki;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;

public class DTOUserInfo {
	String email;
	String usuario;
	String nombre;
	String apellido1;
	String apellido2;
	Integer idUsuario;
	SegOrgUsuarios user;
	Integer idEmpleado;
	InstEmpleado empleado;
	String rol;
	ResponseBodyMenu menu;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public ResponseBodyMenu getMenu() {
		return menu;
	}
	public void setMenu(ResponseBodyMenu menu) {
		this.menu = menu;
	}
	public SegOrgUsuarios getUser() {
		return user;
	}
	public void setUser(SegOrgUsuarios user) {
		this.user = user;
	}
	public InstEmpleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(InstEmpleado empleado) {
		this.empleado = empleado;
	}
	
	
}
