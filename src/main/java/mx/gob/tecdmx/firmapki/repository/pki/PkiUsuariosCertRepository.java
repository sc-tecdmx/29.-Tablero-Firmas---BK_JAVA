package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.pki.PkiUsuariosCert;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;

@Repository
public interface PkiUsuariosCertRepository extends CrudRepository<PkiUsuariosCert, Integer> {
  Optional<PkiUsuariosCert> findByX509SerialNumber(String serialNumber);
  List<PkiUsuariosCert> findByUsuario(SegOrgUsuarios usuario);
	
}