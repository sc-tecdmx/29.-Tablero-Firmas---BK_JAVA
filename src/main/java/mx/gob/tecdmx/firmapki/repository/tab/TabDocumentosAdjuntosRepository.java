package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;

@Repository
public interface TabDocumentosAdjuntosRepository extends CrudRepository<TabDocumentosAdjuntos, Integer> {
  List<TabDocumentosAdjuntos> findByIdDocument(TabDocumentos documento);
	
}