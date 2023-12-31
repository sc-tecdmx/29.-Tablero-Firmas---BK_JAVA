package mx.gob.tecdmx.firmapki.entity.seg;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seg_cat_estado_usuario", schema = "public")
public class SegCatEstadoUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "n_id_estado_usuario", unique = true)
	int  n_id_estado_usuario;
  
	@Column(name = "s_descripcion")
	String  descripcion;

	public int getN_id_estado_usuario() {
		return n_id_estado_usuario;
	}

	public void setN_id_estado_usuario(int n_id_estado_usuario) {
		this.n_id_estado_usuario = n_id_estado_usuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
}