package mx.gob.tecdmx.firmapki.repository.seg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.seg.SegOrgModulos;

@Repository
public interface SegOrgModulosRepository extends CrudRepository<SegOrgModulos, Integer> {
  
	
}