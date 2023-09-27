package mx.gob.tecdmx.firmaocsp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.Documento;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento, Long> {
	
	//List<Documento> findAllByUsuCreacion(int usuarioId);

}
