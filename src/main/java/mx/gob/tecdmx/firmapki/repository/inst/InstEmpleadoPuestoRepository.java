package mx.gob.tecdmx.firmapki.repository.inst;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleadoPuesto;

@Repository
public interface InstEmpleadoPuestoRepository extends CrudRepository<InstEmpleadoPuesto, Integer> {
  
	
}