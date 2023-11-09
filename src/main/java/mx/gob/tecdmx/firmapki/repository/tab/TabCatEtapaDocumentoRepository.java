package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstDest;

@Repository
public interface TabCatEtapaDocumentoRepository extends CrudRepository<TabCatEtapaDocumento, Integer> {
	Optional<TabCatEtapaDocumento> findByDescetapa(String descetapa);

}