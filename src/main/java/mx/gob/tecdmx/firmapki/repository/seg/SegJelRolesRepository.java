package mx.gob.tecdmx.firmapki.repository.seg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.JelSegRoles;

@Repository
public interface SegJelRolesRepository extends CrudRepository<JelSegRoles, Integer> {
  
	
}