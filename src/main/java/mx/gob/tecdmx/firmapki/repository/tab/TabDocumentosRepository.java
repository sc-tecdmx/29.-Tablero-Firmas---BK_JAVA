package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;

@Repository
public interface TabDocumentosRepository extends CrudRepository<TabDocumentos, Integer> {
	Optional<TabDocumentos> findTopByOrderByIdDesc();
}