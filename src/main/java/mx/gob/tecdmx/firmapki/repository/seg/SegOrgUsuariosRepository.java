package mx.gob.tecdmx.firmapki.repository.seg;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;

@Repository
public interface SegOrgUsuariosRepository extends CrudRepository<SegOrgUsuarios, Integer> {
	Optional<SegOrgUsuarios> findBysEmail(String email);
}
