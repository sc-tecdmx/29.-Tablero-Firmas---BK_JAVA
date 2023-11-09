package mx.gob.tecdmx.firmapki.repository.seg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.JelSegModulos;

@Repository
public interface SegJelModulosRepository extends CrudRepository<JelSegModulos, Integer> {
  
	
}
