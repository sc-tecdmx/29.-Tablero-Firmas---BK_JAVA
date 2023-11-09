package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;

@Repository
public interface TabCatDestinoDocumentoRepository extends CrudRepository<TabCatDestinoDocumento, Integer> {

	Optional<TabCatDestinoDocumento> findByDescDestinoDocumento(String descDestinoDocumento);
}