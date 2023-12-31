package mx.gob.tecdmx.firmapki.repository.seg;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.SegCatEstadoUsuario;

@Repository 
public interface SegCatEstadoUsuarioRepository extends CrudRepository<SegCatEstadoUsuario, Integer> {
  Optional<SegCatEstadoUsuario> findByDescripcion(String codigo);
	
}