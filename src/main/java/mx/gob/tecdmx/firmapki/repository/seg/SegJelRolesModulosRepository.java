package mx.gob.tecdmx.firmapki.repository.seg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.IDJelRolesModulos;
import mx.gob.tecdmx.firmapki.entity.seg.JelSegRolesModulos;

@Repository
public interface SegJelRolesModulosRepository extends CrudRepository<JelSegRolesModulos, IDJelRolesModulos> {
  
	
}