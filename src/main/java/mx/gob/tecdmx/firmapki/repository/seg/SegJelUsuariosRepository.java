package mx.gob.tecdmx.firmapki.repository.seg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.JelSegUsuarios;

@Repository
public interface SegJelUsuariosRepository extends CrudRepository<JelSegUsuarios, Integer> {
  
	
}