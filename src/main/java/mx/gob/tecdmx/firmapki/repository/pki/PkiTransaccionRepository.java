package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiTransaccion;

@Repository
public interface PkiTransaccionRepository extends CrudRepository<PkiTransaccion, Integer> {

	Optional<PkiTransaccion> findTopByOrderByIdDesc();



}