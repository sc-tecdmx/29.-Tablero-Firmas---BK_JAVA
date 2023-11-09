package mx.gob.tecdmx.firmapki.repository.tab;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;

@Repository
public interface TabDocumentoWorkflowRepository extends CrudRepository<TabDocumentoWorkflow, Integer> {
  
	
}