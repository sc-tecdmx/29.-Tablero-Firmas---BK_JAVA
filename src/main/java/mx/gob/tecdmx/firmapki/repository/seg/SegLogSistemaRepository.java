package mx.gob.tecdmx.firmapki.repository.seg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.SegLogSistema;

@Repository
public interface SegLogSistemaRepository extends CrudRepository<SegLogSistema, Integer> {
  
	
}