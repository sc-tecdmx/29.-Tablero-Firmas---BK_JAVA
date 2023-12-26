package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumento;

@Repository
public interface PkiDocumentoRepository extends CrudRepository<PkiDocumento, String> {
  Optional<PkiDocumento> findByHashDocumento(String hasDocumento);
	
}