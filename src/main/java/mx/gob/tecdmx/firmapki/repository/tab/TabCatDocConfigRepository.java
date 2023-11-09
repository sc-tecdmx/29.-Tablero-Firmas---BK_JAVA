package mx.gob.tecdmx.firmapki.repository.tab;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatDocConfig;

@Repository
public interface TabCatDocConfigRepository extends CrudRepository<TabCatDocConfig, Integer> {
  
	
}