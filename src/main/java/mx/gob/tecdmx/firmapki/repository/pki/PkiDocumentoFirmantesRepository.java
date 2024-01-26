package mx.gob.tecdmx.firmapki.repository.pki;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;

@Repository
public interface PkiDocumentoFirmantesRepository extends CrudRepository<PkiDocumentoFirmantes, HashDocumentoIdUsuarioIdTransaccionID> {
	List<PkiDocumentoFirmantes> findByHashDocumento(String hashDOcumento);
	List<PkiDocumentoFirmantes> findByHashDocumentoAndFechaFirmaAndIdFirmaAplicada(String hashDOcumento, Date fechaFirma, PkiCatFirmaAplicada  idTipoFirma);
	List<PkiDocumentoFirmantes> findByIdNumEmpleadoAndIdFirmaAplicada(InstEmpleado idNumEmpleado, PkiCatFirmaAplicada  idTipoFirma);
	Optional<PkiDocumentoFirmantes> findByHashDocumentoAndIdNumEmpleadoAndIdFirmaAplicada(String hashDOcumento, InstEmpleado idNumEmpleado, PkiCatFirmaAplicada  idTipoFirma);
	
}