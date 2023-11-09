package mx.gob.tecdmx.firmapki.repository.inst;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.inst.InstTitularUAdscripcion;

@Repository
public interface InstTitularUAdscripcionRepository extends CrudRepository<InstTitularUAdscripcion, Integer> {
  
	
}