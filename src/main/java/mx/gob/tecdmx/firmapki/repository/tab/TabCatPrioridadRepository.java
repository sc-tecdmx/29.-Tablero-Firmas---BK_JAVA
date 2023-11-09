package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;

@Repository
public interface TabCatPrioridadRepository extends CrudRepository<TabCatPrioridad, Integer> {
	Optional<TabCatPrioridad> findByDescPrioridad(String descPrioridad);
}