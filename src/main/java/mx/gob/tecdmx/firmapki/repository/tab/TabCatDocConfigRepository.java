package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatDocConfig;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocConfig;

@Repository
public interface TabCatDocConfigRepository extends CrudRepository<TabCatDocConfig, Integer> {
  
	Optional<TabCatDocConfig> findByAtributo(String atributo);

}