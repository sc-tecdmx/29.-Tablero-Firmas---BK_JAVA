package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.CatTipoUso;

@Repository
public interface CatTipoUsoRepository extends CrudRepository<CatTipoUso, Integer> {
	Optional<CatTipoUso> findByCode(String code);
}
