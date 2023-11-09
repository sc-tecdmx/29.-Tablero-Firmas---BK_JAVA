package mx.gob.tecdmx.firmapki.repository.seg;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.IDRolesModulos;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgRoles;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgRolesModulos;

@Repository
public interface SegOrgRolesModulosRepository extends CrudRepository<SegOrgRolesModulos, IDRolesModulos> {
	List<SegOrgRolesModulos> findBynIdRol(Integer nIdRol);
	List<SegOrgRolesModulos> findBySegOrgRoles(SegOrgRoles rol);
}
