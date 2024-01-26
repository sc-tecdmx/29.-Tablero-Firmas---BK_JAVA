package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoDestino;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;

@Repository
public interface PkiDocumentoDestinoRepository extends CrudRepository<PkiDocumentoDestino, HashDocumentoIdUsuarioIdTransaccionID> {

	List<PkiDocumentoDestino> findByHashDocumento(String documentoHash);
  
	
}