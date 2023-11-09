package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;

@Repository
public interface TabCatInstFirmantesRepository extends CrudRepository<TabCatInstFirmantes, Integer> {
	Optional<TabCatInstFirmantes> findByDescInstrFirmante(String descInstrFirmante);
	
}