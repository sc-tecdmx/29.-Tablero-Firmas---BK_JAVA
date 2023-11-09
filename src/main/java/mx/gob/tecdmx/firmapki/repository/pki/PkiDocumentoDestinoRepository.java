package mx.gob.tecdmx.firmapki.repository.pki;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoDestino;

@Repository
public interface PkiDocumentoDestinoRepository extends CrudRepository<PkiDocumentoDestino, String> {
  
	
}