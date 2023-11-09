package mx.gob.tecdmx.firmapki.repository.tab;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.JelRenapoCurps;

@Repository
public interface JelRenapoCurpsRepository extends CrudRepository<JelRenapoCurps, Integer> {
  

}