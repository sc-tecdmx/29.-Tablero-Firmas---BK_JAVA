package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.CatAccion;

@Repository
public interface CatAccionRepository extends CrudRepository<CatAccion, Integer> {
	Optional<CatAccion> findByCode(String code);
}
