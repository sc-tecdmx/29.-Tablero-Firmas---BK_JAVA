package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.IddocumentoTipoID;
import mx.gob.tecdmx.firmapki.entity.tab.VistaTablero;
import org.springframework.data.domain.Sort;
@Repository
public interface VistaTableroRepository  extends CrudRepository<VistaTablero, IddocumentoTipoID> {
	List<VistaTablero> findByNumEmpleado(int idEmpleado, Sort sort);
	List<VistaTablero> findByNumEmpleadoAndVisible(int idEmpleado,int visible, Sort sort);
}
