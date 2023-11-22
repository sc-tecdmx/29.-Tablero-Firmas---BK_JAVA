package mx.gob.tecdmx.firmapki.entity.tab;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "tab_doc_config", schema = "public")
@IdClass(IddocumentoIddocconfigID.class)
public class TabDocConfig {
	@Id
	@Column(name="n_id_documento")
	Integer  idDocumento;
  
	@Id
	@Column(name="n_id_doc_config")
	Integer  idDocConfig;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_documento", referencedColumnName="n_id_documento", insertable = false, updatable = false)
	TabDocumentos  documento;
  
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="n_id_doc_config", referencedColumnName="n_id_doc_config", insertable = false, updatable = false)
	TabCatDocConfig  docConfig;


	public Integer getIdDocumento() {
		return idDocumento;
	}


	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}


	public Integer getIdDocConfig() {
		return idDocConfig;
	}


	public void setIdDocConfig(Integer idDocConfig) {
		this.idDocConfig = idDocConfig;
	}


	public TabDocumentos getDocumento() {
		return documento;
	}


	public void setDocumento(TabDocumentos documento) {
		this.documento = documento;
	}


	public TabCatDocConfig getDocConfig() {
		return docConfig;
	}


	public void setDocConfig(TabCatDocConfig docConfig) {
		this.docConfig = docConfig;
	}


	
}