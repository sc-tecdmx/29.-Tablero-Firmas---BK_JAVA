package mx.gob.tecdmx.firmapki.repository.seg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.SegLogUsuario;

@Repository
public interface SegLogUsuarioRepository extends CrudRepository<SegLogUsuario, Integer> {
  
	
}