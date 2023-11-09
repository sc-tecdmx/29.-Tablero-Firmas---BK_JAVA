package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiX509AcAutorizadas;

@Repository
public interface PkiX509AcAutorizadasRepository extends CrudRepository<PkiX509AcAutorizadas, String> {

	List<PkiX509AcAutorizadas> findByX509EmisorSerialParentAndTipoCertificado(PkiX509AcAutorizadas serialnumber, String tipo);
	List<PkiX509AcAutorizadas> findAll();
  
}
