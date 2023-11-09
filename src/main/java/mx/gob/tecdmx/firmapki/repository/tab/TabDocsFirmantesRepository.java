package mx.gob.tecdmx.firmapki.repository.tab;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;

@Repository
public interface TabDocsFirmantesRepository extends CrudRepository<TabDocsFirmantes, Integer> {
  
	
}