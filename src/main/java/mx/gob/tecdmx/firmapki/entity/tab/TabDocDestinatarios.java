package mx.gob.tecdmx.firmapki.entity.tab;

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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_documento", referencedColumnName="n_id_documento")
	TabDocumentos  idDocumento;
  
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_num_empleado", referencedColumnName="n_id_num_empleado")
	InstEmpleado  idNumEmpleado;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_inst_dest", referencedColumnName="n_id_inst_dest")
	TabCatInstDest  idnstDestinatario;

	public TabDocumentos getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(TabDocumentos idDocumento) {
		this.idDocumento = idDocumento;
	}

	public InstEmpleado getIdNumEmpleado() {
		return idNumEmpleado;
	}

	public void setIdNumEmpleado(InstEmpleado idNumEmpleado) {
		this.idNumEmpleado = idNumEmpleado;
	}

	public TabCatInstDest getIdnstDestinatario() {
		return idnstDestinatario;
	}

	public void setIdnstDestinatario(TabCatInstDest idnstDestinatario) {
		this.idnstDestinatario = idnstDestinatario;
	}

	
	
}