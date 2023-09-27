package mx.gob.tecdmx.firmaocsp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmaocsp.entity.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	Optional<Usuario> findByCorreo(String correo);
}
