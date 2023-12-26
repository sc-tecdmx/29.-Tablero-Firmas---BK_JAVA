package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;

@Repository
public interface TabDocumentoWorkflowRepository extends CrudRepository<TabDocumentoWorkflow, Integer> {
  
	List<TabDocumentoWorkflow> findByIdDocumentOrderByWorkflowFecha(TabDocumentos documento);
}