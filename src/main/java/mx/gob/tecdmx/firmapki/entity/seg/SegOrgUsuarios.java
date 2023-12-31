package mx.gob.tecdmx.firmapki.entity.seg;

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
@Table(name = "seg_org_usuarios", schema = "public")
public class SegOrgUsuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "n_id_usuario", unique = true, nullable = false)
	int  nIdUsuario;
  
	@Column(name = "s_usuario")
	String  sUsuario;
  
	@Column(name = "s_contrasenia")
	String  sContrasenia;
  
	@Column(name = "s_desc_usuario")
	String  sDescUsuario;
  
	@Column(name = "s_email")
	String  sEmail;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_estado_usuario", referencedColumnName="n_id_estado_usuario")
	SegCatEstadoUsuario  nIdEstadoUsuario;
  
	@Column(name = "s_token")
	String  sToken;

	public int getnIdUsuario() {
		return nIdUsuario;
	}

	public void setnIdUsuario(int nIdUsuario) {
		this.nIdUsuario = nIdUsuario;
	}

	public String getsUsuario() {
		return sUsuario;
	}

	public void setsUsuario(String sUsuario) {
		this.sUsuario = sUsuario;
	}

	public String getsContrasenia() {
		return sContrasenia;
	}

	public void setsContrasenia(String sContrasenia) {
		this.sContrasenia = sContrasenia;
	}

	public String getsDescUsuario() {
		return sDescUsuario;
	}

	public void setsDescUsuario(String sDescUsuario) {
		this.sDescUsuario = sDescUsuario;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public SegCatEstadoUsuario getnIdEstadoUsuario() {
		return nIdEstadoUsuario;
	}

	public void setnIdEstadoUsuario(SegCatEstadoUsuario nIdEstadoUsuario) {
		this.nIdEstadoUsuario = nIdEstadoUsuario;
	}

	public String getsToken() {
		return sToken;
	}

	public void setsToken(String sToken) {
		this.sToken = sToken;
	}

	
	
}