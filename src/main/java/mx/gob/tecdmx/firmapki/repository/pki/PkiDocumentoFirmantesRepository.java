package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;

@Repository
public interface PkiDocumentoFirmantesRepository extends CrudRepository<PkiDocumentoFirmantes, HashDocumentoIdUsuarioIdTransaccionID> {
	Optional<PkiDocumentoFirmantes> findByHashDocumento(String hashDOcumento);
	
}