package mx.gob.tecdmx.firmapki.repository.pki;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiX509JelAutorizacion;

@Repository
public interface PkiX509JelAutorizacionRepository extends CrudRepository<PkiX509JelAutorizacion, Integer> {
  
	
}