package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Tsp;

@Repository
public interface PkiX509TspRepository extends CrudRepository<PkiX509Tsp, String> {
Optional<PkiX509Tsp> findTopByOrderByFechaResponseDesc();

}