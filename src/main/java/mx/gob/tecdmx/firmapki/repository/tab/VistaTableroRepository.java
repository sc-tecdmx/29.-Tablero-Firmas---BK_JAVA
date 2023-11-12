package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.IddocumentoTipoID;
import mx.gob.tecdmx.firmapki.entity.tab.VistaTablero;

@Repository
public interface VistaTableroRepository  extends CrudRepository<VistaTablero, IddocumentoTipoID> {
	List<VistaTablero> findByNumEmpleado(int idEmpleado);
}
