package mx.gob.tecdmx.firmapki.repository.inst;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.inst.InstCatAreas;

@Repository
public interface InstCatAreasRepository extends CrudRepository<InstCatAreas, Integer> {
	Optional<InstCatAreas> findByAbrevArea(String area);
	
}