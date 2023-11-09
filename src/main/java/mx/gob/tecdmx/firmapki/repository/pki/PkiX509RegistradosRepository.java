package mx.gob.tecdmx.firmapki.repository.pki;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Registrados;

@Repository
public interface PkiX509RegistradosRepository extends CrudRepository<PkiX509Registrados, String> {
  
	
}