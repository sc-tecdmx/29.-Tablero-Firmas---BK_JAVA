package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.CatCargo;

@Repository
public interface CatCargoRepository extends CrudRepository<CatCargo, Integer> {
	Optional<CatCargo> findByCode(String code);
}
