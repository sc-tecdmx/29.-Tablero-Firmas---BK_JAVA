package mx.gob.tecdmx.firmapki.repository.inst;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.inst.InstLogEmpleado;

@Repository
public interface InstLogEmpleadoRepository extends CrudRepository<InstLogEmpleado, Integer> {
  
	
}