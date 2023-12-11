package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstDest;

@Repository
public interface TabCatInstDestRepository extends CrudRepository<TabCatInstDest, Integer> {
	Optional<TabCatInstDest> findByDescInsDest(String descInsDest);
	
}