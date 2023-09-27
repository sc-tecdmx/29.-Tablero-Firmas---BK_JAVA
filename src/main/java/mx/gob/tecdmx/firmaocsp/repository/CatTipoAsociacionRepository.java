package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.CatTipoAsociacion;

@Repository
public interface CatTipoAsociacionRepository extends CrudRepository<CatTipoAsociacion, Integer> {
	Optional<CatTipoAsociacion> findByCode(String code);
}
