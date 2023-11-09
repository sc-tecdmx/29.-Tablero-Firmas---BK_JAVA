package mx.gob.tecdmx.firmapki.repository.pki;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiX509DocumentoCertificado;

@Repository
public interface PkiX509FileCertificadoRepository extends CrudRepository<PkiX509DocumentoCertificado, Integer> {
  
	
}
