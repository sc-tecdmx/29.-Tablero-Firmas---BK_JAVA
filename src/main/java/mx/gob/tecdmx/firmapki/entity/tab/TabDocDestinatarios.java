package mx.gob.tecdmx.firmapki.entity.tab;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
@Entity
@Table(name = "tab_doc_destinatarios", schema = "public")
@IdClass(IddocumentoIdnumeEmpleadoID.class)
public class TabDocDestinatarios {
	@Id
	@Column(name="n_id_documento")
	Integer  idDocumento;
  
	@Id
	@Column(name="n_id_num_empleado")
	Integer  idNumEmpleado;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_documento", referencedColumnName="n_id_documento", insertable = false, updatable = false)
	TabDocumentos  documento;
  
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_num_empleado", referencedColumnName="n_id_num_empleado", insertable = false, updatable = false)
	InstEmpleado  empleado;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_inst_dest", referencedColumnName="n_id_inst_dest")
	TabCatInstDest  idnstDestinatario;

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Integer getIdNumEmpleado() {
		return idNumEmpleado;
	}

	public void setIdNumEmpleado(Integer idNumEmpleado) {
		this.idNumEmpleado = idNumEmpleado;
	}

	public TabDocumentos getDocumento() {
		return documento;
	}

	public void setDocumento(TabDocumentos documento) {
		this.documento = documento;
	}

	public InstEmpleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(InstEmpleado empleado) {
		this.empleado = empleado;
	}

	public TabCatInstDest getIdnstDestinatario() {
		return idnstDestinatario;
	}

	public void setIdnstDestinatario(TabCatInstDest idnstDestinatario) {
		this.idnstDestinatario = idnstDestinatario;
	}

	
	
}