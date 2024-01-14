package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiX509AcAutorizadas;

@Repository
public interface PkiX509AcAutorizadasRepository extends CrudRepository<PkiX509AcAutorizadas, String> {
	
	List<PkiX509AcAutorizadas> findByX509EmisorSerialParentAndTipoCertificado(PkiX509AcAutorizadas serialnumber, String tipo);
	List<PkiX509AcAutorizadas> findAll();
	Optional<PkiX509AcAutorizadas> findById(String serialnumber);
	List<PkiX509AcAutorizadas> findAllByIdAndIdParent(String serialnumber, String serialnumberParent);
  
}
