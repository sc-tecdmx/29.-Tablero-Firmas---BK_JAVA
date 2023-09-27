package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.CatTipoDocumento;

@Repository
public interface CatTipoDocumentoRepository extends CrudRepository<CatTipoDocumento, Integer> {
	Optional<CatTipoDocumento> findByCode(String code);
}
