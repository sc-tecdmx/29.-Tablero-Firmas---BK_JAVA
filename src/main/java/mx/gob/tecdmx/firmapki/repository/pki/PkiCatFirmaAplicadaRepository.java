package mx.gob.tecdmx.firmapki.repository.pki;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;

@Repository
public interface PkiCatFirmaAplicadaRepository extends CrudRepository<PkiCatFirmaAplicada, Integer> {
  
	
}