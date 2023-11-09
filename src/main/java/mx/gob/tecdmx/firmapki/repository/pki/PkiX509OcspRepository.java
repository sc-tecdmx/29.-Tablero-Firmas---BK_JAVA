package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Ocsp;

@Repository
public interface PkiX509OcspRepository extends CrudRepository<PkiX509Ocsp, String> {

Optional<PkiX509Ocsp> findTopByOrderByFechaResponseDesc();
}