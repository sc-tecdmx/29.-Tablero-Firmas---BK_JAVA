package mx.gob.tecdmx.firmapki.repository.seg;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.SegCatNivelModulo;

@Repository
public interface SegCatNivelModuloRepository extends CrudRepository<SegCatNivelModulo, Integer> {
	Optional<SegCatNivelModulo> findByDescNivel(String nivelModulo);
	
}