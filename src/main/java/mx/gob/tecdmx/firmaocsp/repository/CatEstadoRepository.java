package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.CatEstado;

@Repository
public interface CatEstadoRepository extends CrudRepository<CatEstado, Integer> {
	Optional<CatEstado> findByCode(String code);
}
