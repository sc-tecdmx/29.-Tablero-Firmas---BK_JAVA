package mx.gob.tecdmx.firmapki.repository.tab;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.IddocumentoIddocconfigID;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocConfig;

@Repository
public interface TabDocConfigRepository extends CrudRepository<TabDocConfig, IddocumentoIddocconfigID> {
  
	
}