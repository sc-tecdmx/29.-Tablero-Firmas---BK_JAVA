package mx.gob.tecdmx.firmapki.api.transaccion;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.api.populate.CertUser;
import mx.gob.tecdmx.firmapki.api.populate.ServicePopulate;
import mx.gob.tecdmx.firmapki.entity.pki.PkiTransaccion;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Registrados;
import mx.gob.tecdmx.firmapki.repository.pki.PkiTransaccionRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509RegistradosRepository;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@Service
public class ServiceTransaccion {
	
	@Autowired
	PkiTransaccionRepository pkiTransaccionRepository;
	
	@Autowired
	PkiX509RegistradosRepository pkiX509RegistradosRepository;
	
	@Autowired
	ServicePopulate servicePopulate;
	
	public DTOResponse getTransaccion(byte[] certData, DTOResponse res) {
		InputStream inStream = new ByteArrayInputStream(certData);
		CertUser certUser = new CertUser(inStream);
		//Se guarda el certificado recuperado si aún no ha sido almacenado
		Optional<PkiX509Registrados> x509User = pkiX509RegistradosRepository.findById(certUser.getSerialnumber());
		if(!x509User.isPresent()) {
			boolean isPopulated = servicePopulate.saveCertUser(certUser);
			if(!isPopulated) {
				res.setStatus("Fail");
				res.setMessage("No se pudo crear el registro del certificado del usuario");
				return res;
			}
		}
		
		PkiTransaccion pkiTrans = new PkiTransaccion();
		pkiTrans.setX509SerialNumber(x509User.get());
		Optional<PkiTransaccion> lastTransaction = pkiTransaccionRepository.findTopByOrderByIdDesc();
		if(lastTransaction.isPresent()) {
			pkiTrans.setIdTransaccionBlock(lastTransaction.get());
		}else {
			PkiTransaccion pkiX509AcGuardado = pkiTransaccionRepository.save(pkiTrans);
			pkiTrans.setIdTransaccionBlock(pkiX509AcGuardado);
			pkiX509AcGuardado = pkiTransaccionRepository.save(pkiTrans);
			
		}
		try {
			PkiTransaccion pkiX509AcGuardado = pkiTransaccionRepository.save(pkiTrans);
			
			ResponseBodyTransaccion response = new ResponseBodyTransaccion();
			response.setIdTransaccion(pkiX509AcGuardado.getId());
			response.setNumeroSerie(pkiX509AcGuardado.getX509SerialNumber().getX509SerialNumber());
			response.setTransaccionBlock(pkiX509AcGuardado.getIdTransaccionBlock().getId());
			
		    res.setData(response);
		    res.setStatus("Success");
		    res.setMessage("Se ha creado una transacción satisfactoriamente");
		    // Verificar si se guardó correctamente
		} catch (DataAccessException ex) {
		    // Manejar la excepción, como registrar en un log
			res.setStatus("Fail");
			res.setMessage("Hubo un error al crear la transacción");
		}
		return res;
		
	}
}
