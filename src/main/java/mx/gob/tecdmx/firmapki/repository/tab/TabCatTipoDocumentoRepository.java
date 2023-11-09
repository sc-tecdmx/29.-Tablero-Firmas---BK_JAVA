package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;

@Repository
public interface TabCatTipoDocumentoRepository extends CrudRepository<TabCatTipoDocumento, Integer> {
	Optional<TabCatTipoDocumento> findByDescTipoDocumento(String descTipoDocumento);
}