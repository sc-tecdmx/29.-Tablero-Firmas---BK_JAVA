package mx.gob.tecdmx.firmapki.entity.tab;

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
@Table(name = "tab_documentos_adjuntos", schema = "public")
public class TabDocumentosAdjuntos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_documento_adjunto", unique = true, nullable = false)
	int id;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_document", referencedColumnName="n_id_documento")
	TabDocumentos  idDocument;
  
	@Column(name = "documento_path")
	String  documentoPath;
  
	@Column(name = "documento_hash")
	String  documentoHash;
  
	@Column(name = "documento_filetype")
	String  documentoFiletype;
	
	@Column(name = "documento_base64")
	String  documentoBase64;
  
	@Column(name = "fecha_carga")
	Date  fechaCarga;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TabDocumentos getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(TabDocumentos idDocument) {
		this.idDocument = idDocument;
	}

	public String getDocumentoPath() {
		return documentoPath;
	}

	public void setDocumentoPath(String documentoPath) {
		this.documentoPath = documentoPath;
	}

	public String getDocumentoHash() {
		return documentoHash;
	}

	public void setDocumentoHash(String documentoHash) {
		this.documentoHash = documentoHash;
	}

	public String getDocumentoFiletype() {
		return documentoFiletype;
	}

	public void setDocumentoFiletype(String documentoFiletype) {
		this.documentoFiletype = documentoFiletype;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getDocumentoBase64() {
		return documentoBase64;
	}

	public void setDocumentoBase64(String documentoBase64) {
		this.documentoBase64 = documentoBase64;
	}

	
	
	
}