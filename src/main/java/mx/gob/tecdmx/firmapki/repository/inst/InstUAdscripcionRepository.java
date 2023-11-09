package mx.gob.tecdmx.firmapki.repository.inst;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.inst.InstUAdscripcion;

@Repository
public interface InstUAdscripcionRepository extends CrudRepository<InstUAdscripcion, Integer> {
  
	
}