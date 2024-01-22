package mx.gob.tecdmx.firmapki.entity.tab;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "vista_tablero")
@IdClass(IddocumentoTipoID.class)
public class VistaTablero {

	@Id
	@Column(name = "n_id_documento")
	int  idDocumento;
	
	@Id
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "folio_documento")
	private String folioDocumento;
	
	@Column(name = "s_desc_etapa")
	private String etapa;
    
	@Column(name = "prioridad")
	private String prioridad;
    
	@Column(name = "creacion_documento_fecha")
	private Date creacionDocumentoFecha;
	
	@Column(name = "s_asunto")
	private String asunto;
	
	@Column(name = "num_empleado")
	private int numEmpleado;
	
	@Column(name = "visible")
	private int visible;
	
	@Column(name = "n_id_inst")
	private int idInstruccion;

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFolioDocumento() {
		return folioDocumento;
	}

	public void setFolioDocumento(String folioDocumento) {
		this.folioDocumento = folioDocumento;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public Date getCreacionDocumentoFecha() {
		return creacionDocumentoFecha;
	}

	public void setCreacionDocumentoFecha(Date creacionDocumentoFecha) {
		this.creacionDocumentoFecha = creacionDocumentoFecha;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public int getIdInstruccion() {
		return idInstruccion;
	}

	public void setIdInstruccion(int idInstruccion) {
		this.idInstruccion = idInstruccion;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	
}
