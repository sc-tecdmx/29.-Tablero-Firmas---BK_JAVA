package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;

@Repository
public interface PkiCatTipoFirmaRepository extends CrudRepository<PkiCatTipoFirma, Integer> {
	Optional<PkiCatTipoFirma> findByDescTipoFirma(String descTipoFirma);
}