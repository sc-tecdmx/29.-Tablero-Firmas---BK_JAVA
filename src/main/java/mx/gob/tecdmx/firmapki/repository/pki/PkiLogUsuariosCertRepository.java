package mx.gob.tecdmx.firmapki.repository.pki;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiLogUsuariosCert;

@Repository
public interface PkiLogUsuariosCertRepository extends CrudRepository<PkiLogUsuariosCert, Integer> {
  
	
}