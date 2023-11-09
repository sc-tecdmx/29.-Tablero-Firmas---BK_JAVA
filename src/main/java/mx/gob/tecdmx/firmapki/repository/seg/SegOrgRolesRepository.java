package mx.gob.tecdmx.firmapki.repository.seg;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.SegOrgRoles;

@Repository
public interface SegOrgRolesRepository extends CrudRepository<SegOrgRoles, Integer> {
	Optional<SegOrgRoles> findByEtiquetaRol(String etiquetaRol);
}
