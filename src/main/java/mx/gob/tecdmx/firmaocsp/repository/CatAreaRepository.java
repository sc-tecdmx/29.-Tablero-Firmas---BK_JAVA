package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.CatArea;

@Repository
public interface CatAreaRepository extends CrudRepository<CatArea, Integer> {
	Optional<CatArea> findByCode(String code);
}
