package mx.gob.tecdmx.firmapki.repository.seg;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.SegOrgRolesUsuarios;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;

@Repository
public interface SegOrgRolesUsuariosRepository extends CrudRepository<SegOrgRolesUsuarios, Integer> {
	List<SegOrgRolesUsuarios> findBynIdUsuario(SegOrgUsuarios usuario);
}
