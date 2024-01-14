package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiUsuariosCert;

@Repository
public interface PkiUsuariosCertRepository extends CrudRepository<PkiUsuariosCert, Integer> {
  Optional<PkiUsuariosCert> findByX509SerialNumber(String serialNumber);
	
}