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
@Table(name = "documento", schema = "public")
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	long id;
	
	@Column(name = "id_documento")
	String idDocumento;
	
	@Column(name = "folio")
	String folio;

	@Column(name = "folio_documento")
	String folioDocumento;

	@Column(name = "numero_expediente")
	String numeroExpediente;

	@Column(name = "nombre_expediente")
	String nombreExpediente;

	@Column(name = "fecha_documento")
	String fechaDocumento;
	
	@Column(name = "asunto")
	String asunto;
	
	@Column(name = "contenido")
	String contenido;

	@Column(name = "nota")
	String nota;
	
	@Column(name = "documento")
	String documento;
	
	@Column(name = "porcentaje_firma")
	float porcentajeFirma;
	
	@Column(name = "cargo")
	String cargo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipouso", referencedColumnName="id")
	CatTipoUso idTipouso;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="id_areadestino", referencedColumnName="id")
	CatArea idAreadestino;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_areacopia", referencedColumnName="id")
	CatArea idAreacopia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipodocumento", referencedColumnName="id")
	CatTipoDocumento idTipodocumento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_destinatario", referencedColumnName="id")
	Usuario idDestinatario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_elaboro", referencedColumnName="id")
	Usuario idElaboro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_estadodocumento", referencedColumnName="id")
	CatEstado idEstadodocumento;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFolioDocumento() {
		return folioDocumento;
	}

	public void setFolioDocumento(String folioDocumento) {
		this.folioDocumento = folioDocumento;
	}

	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public String getNombreExpediente() {
		return nombreExpediente;
	}

	public void setNombreExpediente(String nombreExpediente) {
		this.nombreExpediente = nombreExpediente;
	}

	public String getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public float getPorcentajeFirma() {
		return porcentajeFirma;
	}

	public void setPorcentajeFirma(float porcentajeFirma) {
		this.porcentajeFirma = porcentajeFirma;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public CatTipoUso getIdTipouso() {
		return idTipouso;
	}

	public void setIdTipouso(CatTipoUso idTipouso) {
		this.idTipouso = idTipouso;
	}

	public CatArea getIdAreadestino() {
		return idAreadestino;
	}

	public void setIdAreadestino(CatArea idAreadestino) {
		this.idAreadestino = idAreadestino;
	}

	public CatArea getIdAreacopia() {
		return idAreacopia;
	}

	public void setIdAreacopia(CatArea idAreacopia) {
		this.idAreacopia = idAreacopia;
	}

	public CatTipoDocumento getIdTipodocumento() {
		return idTipodocumento;
	}

	public void setIdTipodocumento(CatTipoDocumento idTipodocumento) {
		this.idTipodocumento = idTipodocumento;
	}

	public Usuario getIdDestinatario() {
		return idDestinatario;
	}

	public void setIdDestinatario(Usuario idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public Usuario getIdElaboro() {
		return idElaboro;
	}

	public void setIdElaboro(Usuario idElaboro) {
		this.idElaboro = idElaboro;
	}

	public CatEstado getIdEstadodocumento() {
		return idEstadodocumento;
	}

	public void setIdEstadodocumento(CatEstado idEstadodocumento) {
		this.idEstadodocumento = idEstadodocumento;
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
