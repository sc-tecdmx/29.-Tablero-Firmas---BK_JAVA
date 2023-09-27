package mx.gob.tecdmx.firmaocsp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.Asignacion;

@Repository
public interface AsignacionRepository extends CrudRepository<Asignacion, Integer> {
	/*	
	List<Asignacion> findByDocumentoIdAndAccion(int documentoId, int accion);
	

	List<Firma> findByUsuarioId(int usuarioId);
	List<Firma> findByDocumentoIdOrderByTimeStampDesc(int documentoId);
	Optional<Firma> findByDocumentoIdAndUsuarioId(int documentoId, int usuarioId);*/
}
