package mx.gob.tecdmx.firmapki.repository.seg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.IDJelRolesUsuarios;
import mx.gob.tecdmx.firmapki.entity.seg.JelSegRolesUsuarios;

@Repository
public interface SegJelRolesUsuariosRepository extends CrudRepository<JelSegRolesUsuarios, IDJelRolesUsuarios> {
  
	
}