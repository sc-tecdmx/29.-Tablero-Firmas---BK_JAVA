package mx.gob.tecdmx.firmapki.repository.inst;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;

@Repository
public interface InstEmpleadoRepository extends CrudRepository<InstEmpleado, Integer> {
  
	
}