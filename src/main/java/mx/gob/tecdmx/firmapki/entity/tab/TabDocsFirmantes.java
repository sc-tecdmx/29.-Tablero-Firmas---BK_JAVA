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
@Table(name = "tab_docs_firmantes", schema = "public")
@IdClass(IddocumentoIdnumeEmpleadoID.class)
public class TabDocsFirmantes {
	
	@Id
	@Column(name="n_id_documento")
	int  idDocumento;
  
	@Id
	@Column(name="n_id_num_empleado")
	int  idNumEmpleado;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_inst_firmante", referencedColumnName="n_id_inst_firmante")
	TabCatInstFirmantes  idInstFirmante;
	
	@ManyToOne()
	@JoinColumn(name="n_id_documento", referencedColumnName="n_id_documento", insertable = false, updatable = false)
	TabDocumentos  documento;
  
	@ManyToOne()
	@JoinColumn(name="n_id_num_empleado", referencedColumnName="n_id_num_empleado", insertable = false, updatable = false)
	InstEmpleado  intEmpleado;
	
	@Column(name="secuencia")
	int secuencia;

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public int getIdNumEmpleado() {
		return idNumEmpleado;
	}

	public void setIdNumEmpleado(int idNumEmpleado) {
		this.idNumEmpleado = idNumEmpleado;
	}

	public TabCatInstFirmantes getIdInstFirmante() {
		return idInstFirmante;
	}

	public void setIdInstFirmante(TabCatInstFirmantes idInstFirmante) {
		this.idInstFirmante = idInstFirmante;
	}

	public TabDocumentos getDocumento() {
		return documento;
	}

	public void setDocumento(TabDocumentos documento) {
		this.documento = documento;
	}

	public InstEmpleado getIntEmpleado() {
		return intEmpleado;
	}

	public void setIntEmpleado(InstEmpleado intEmpleado) {
		this.intEmpleado = intEmpleado;
	}

	public int getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}

	
	
}