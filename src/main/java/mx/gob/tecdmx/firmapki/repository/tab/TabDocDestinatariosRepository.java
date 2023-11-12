package mx.gob.tecdmx.firmapki.repository.tab;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.tab.IddocumentoIdnumeEmpleadoID;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocDestinatarios;

@Repository
public interface TabDocDestinatariosRepository extends CrudRepository<TabDocDestinatarios, IddocumentoIdnumeEmpleadoID> {
	List<TabDocDestinatarios> findByIdNumEmpleado(int numEmpleado);
	List<TabDocDestinatarios> findByIdDocumento(int idDocumento);
}
