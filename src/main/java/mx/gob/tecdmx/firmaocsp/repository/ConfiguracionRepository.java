package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.Configuracion;


@Repository
public interface ConfiguracionRepository extends CrudRepository<Configuracion, Integer> {
	/*	
	Optional<Configuracion> findByDocumentoId(int documentoId);
	
	

	List<Firma> findByUsuarioId(int usuarioId);
	List<Firma> findByDocumentoIdOrderByTimeStampDesc(int documentoId);
	Optional<Firma> findByDocumentoIdAndUsuarioId(int documentoId, int usuarioId);*/
}
