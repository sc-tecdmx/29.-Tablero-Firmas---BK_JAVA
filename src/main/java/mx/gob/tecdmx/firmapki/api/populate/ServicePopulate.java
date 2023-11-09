package mx.gob.tecdmx.firmapki.api.populate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.entity.pki.PkiUsuariosCert;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509AcAutorizadas;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Registrados;
import mx.gob.tecdmx.firmapki.repository.pki.PkiUsuariosCertRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509AcAutorizadasRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509RegistradosRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@Service
public class ServicePopulate {
	
	@Autowired
	PkiX509RegistradosRepository pkiX509RegistradosRepository;
	
	@Autowired
	PkiX509AcAutorizadasRepository pkiX509AcAutorizadasRepository;
	
	@Autowired
	PkiUsuariosCertRepository pkiUsuariosCertRepository;

	public CertCA saveACAutorizada(byte[] certData, String serialnumber, String tipo, DTOResponse res) {
		InputStream inStream = new ByteArrayInputStream(certData);
		CertCA certCA = new CertCA(inStream);
		
		PkiX509AcAutorizadas pkiX509Ac = new PkiX509AcAutorizadas();
		pkiX509Ac.setId(certCA.getSerialnumber());
		pkiX509Ac.setX509AcDerB64(certCA.getDerBase64());
		pkiX509Ac.setX509EmisorAutoridad(certCA.getNombreComunAutoridad());
		pkiX509Ac.setUrl(certCA.getUrl());
		pkiX509Ac.setTipoCertificado(tipo);
		
		if(serialnumber==null) {
			
			Optional<PkiX509AcAutorizadas> certPadre = pkiX509AcAutorizadasRepository.findById("11111");
			pkiX509Ac.setX509EmisorSerialParent(certPadre.get());
			PkiX509AcAutorizadas pkiX509AcGuardado = pkiX509AcAutorizadasRepository.save(pkiX509Ac);
			pkiX509Ac.setX509EmisorSerialParent(pkiX509AcGuardado);
			pkiX509AcGuardado = pkiX509AcAutorizadasRepository.save(pkiX509Ac);
			res.setStatus("Success");
			res.setMessage("El certificado "+tipo+"se ha guardado satisfactoriamente");
			res.setData(null);
			return certCA;
		}else {
			Optional<PkiX509AcAutorizadas> certPadre = pkiX509AcAutorizadasRepository.findById(serialnumber);
			if(!certPadre.isPresent()) {
				res.setStatus("Fail");
				res.setMessage("No se encontró el certificado padre");
				return null;
			}
			pkiX509Ac.setX509EmisorSerialParent(certPadre.get());
			PkiX509AcAutorizadas pkiX509AcGuardado = pkiX509AcAutorizadasRepository.save(pkiX509Ac);
			res.setStatus("Success");
			res.setMessage("El certificado "+tipo+"se ha guardado satisfactoriamente");
			res.setData(null);
			return certCA;
		}
	}
	
	public PkiX509Registrados saveCertUser(byte[] certData, String serialnumber, DTOResponse res, DTOResponseUserInfo userInfo) {
		CertificateUtils certUtils = new CertificateUtils();
		InputStream inStream = new ByteArrayInputStream(certData);
		CertUser certUser = new CertUser(inStream);

		PkiX509Registrados pkiX509r = new PkiX509Registrados();

		pkiX509r.setX509SerialNumber(certUser.getSerialnumber());
		pkiX509r.setX509DerB64(certUser.getDerBase64());
		pkiX509r.setX509Sha256Cert(certUser.getCertSha256());
		
		Optional<PkiX509AcAutorizadas> certPadre = pkiX509AcAutorizadasRepository.findById(serialnumber);
		if(!certPadre.isPresent()) {
			res.setStatus("Fail");
			res.setMessage("No se encontró el certificado padre dentro de los autorizados");
			return null;
		}
		pkiX509r.setX509Subject(certUser.getNombreComun());
		pkiX509r.setX509EmisorSerial(certPadre.get());
		pkiX509r.setX509Rfc(certUser.getRfc());
		pkiX509r.setX509Curp(certUser.getCurp());
		
		
		pkiX509r.setX509Nombre(userInfo.getData().getNombre());
		pkiX509r.setX509Apellido1(userInfo.getData().getApellido1());
		pkiX509r.setX509Apellido2(userInfo.getData().getApellido2());
		pkiX509r.setTokenVigencia(null);
		pkiX509r.setFechaRegistro(certUser.getFechaRegistro());
		pkiX509r.setFechaRevocacion(certUser.getFechaRevocacion());
		
		String registroJson = certUtils.objectToJson(pkiX509r);
		String registroSha256 = certUtils.calcularSHA256(registroJson);
		pkiX509r.setSha256Registro(registroSha256);
		
		PkiX509Registrados pkiX509rGuardado = pkiX509RegistradosRepository.save(pkiX509r);
		if(pkiX509rGuardado!=null) {
			
			PkiUsuariosCert usuariosCert = new PkiUsuariosCert();
			usuariosCert.setIdUsuarioFirma(userInfo.getData().getIdUsuario());
			usuariosCert.setX509SerialNumber(pkiX509rGuardado.getX509SerialNumber());
			usuariosCert.setCurp(certUser.getCurp());
			usuariosCert.setRfc(certUser.getRfc());
			
			String registroJsonUsuariosCert = certUtils.objectToJson(usuariosCert);
			String registroSha256_uc = certUtils.calcularSHA256(registroJsonUsuariosCert);
			usuariosCert.setSha256Registro(registroSha256_uc);
			
			
			PkiUsuariosCert usuariosCertStored = pkiUsuariosCertRepository.save(usuariosCert);
			if(usuariosCertStored!=null) {
				res.setData(null);
			    res.setStatus("Success");
			    res.setMessage("El certificado se ha guardado correctamente");
			    return pkiX509r;
			}
		}
	    
	    
		
		
		
		res.setStatus("Fail");
		res.setMessage("Hubo un error al guardar el certificado");
		return pkiX509r;
	}
	
	public boolean saveCertUser(CertUser certUser) {
		CertificateUtils certUtils = new CertificateUtils();
		PkiX509Registrados pkiX509r = new PkiX509Registrados();

//		pkiX509r.setsX509SerialNumber(certUser.getSerialnumber());
//		pkiX509r.setsX509DerB64(certUser.getDerBase64());
//		pkiX509r.setsX509Sha256Cert(certUser.getCertSha256());
//		pkiX509r.setsX509EmisorSerial(certUser.getEmisorSerial());
//		pkiX509r.setsX509Rfc(certUser.getRfc());
//		pkiX509r.setsX509Curp(certUser.getCurp());
//		
//		String[] nombreParts = certUtils.extraerNombre(certUser.getNombreComun());
//		
//		pkiX509r.setsX509Nombre(nombreParts[0]);
//		pkiX509r.setsX509Apellido1(nombreParts[1]);
//		pkiX509r.setsX509Apellido2(nombreParts[2]);
//		pkiX509r.setsTokenVigencia(null);
//		pkiX509r.setdFechaRegistro(certUser.getFechaRegistro());
//		pkiX509r.setdFechaRevocacion(certUser.getFechaRevocacion());
//		pkiX509r.setsX509EmisorSerial("275106190556405255191281850864241509582310027318");
//		
//		String registroJson = certUtils.objectToJson(pkiX509r);
//		String registroSha256 = certUtils.calcularSHA256(registroJson);
//		pkiX509r.setsSha256Registro(registroSha256);
//		
//		try {
//		    PkiX509Registrados pkiX509rGuardado = pkiX509RegistradosRepository.save(pkiX509r);
//		    return true;
//		    // Verificar si se guardó correctamente
//		} catch (DataAccessException ex) {
//		    // Manejar la excepción, como registrar en un log
//		}
		return true;
	}

}
