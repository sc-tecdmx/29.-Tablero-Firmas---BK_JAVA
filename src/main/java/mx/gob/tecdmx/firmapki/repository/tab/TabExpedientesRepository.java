package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;

@Repository
public interface TabExpedientesRepository extends CrudRepository<TabExpedientes, Integer> {
  Optional<TabExpedientes> findByNumExpediente(String numExpediente);
}