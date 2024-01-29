package mx.gob.tecdmx.firmapki.api.populate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.entity.pki.PkiUsuariosCert;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509AcAutorizadas;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Registrados;
import mx.gob.tecdmx.firmapki.repository.pki.PkiUsuariosCertRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509AcAutorizadasRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509RegistradosRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTipoCerificado;

@Service
public class ServicePopulate {
	
	@Autowired
	PkiX509RegistradosRepository pkiX509RegistradosRepository;
	
	@Autowired
	PkiX509AcAutorizadasRepository pkiX509AcAutorizadasRepository;
	
	@Autowired
	PkiUsuariosCertRepository pkiUsuariosCertRepository;
	
	public void storeUserCert(CertUser certUser, PkiX509AcAutorizadas parent, DTOResponseUserInfo userInfo, DTOResponse res) {
		CertificateUtils certUtils = new CertificateUtils();
		
		PkiX509Registrados pkiX509r = new PkiX509Registrados();

		pkiX509r.setX509SerialNumber(certUser.getSerialnumber());
		pkiX509r.setX509DerB64(certUser.getDerBase64());
		pkiX509r.setX509Sha256Cert(certUser.getCertSha256());

		pkiX509r.setX509Subject(certUser.getNombreComun());
		pkiX509r.setX509EmisorSerial(parent);
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
			}
		}
	}
	
	public PkiX509AcAutorizadas storeAC(CertCA certCA, DTOResponse res, String serialnumber, String tipo) {
		PkiX509AcAutorizadas pkiX509Ac = new PkiX509AcAutorizadas();
		pkiX509Ac.setId(certCA.getSerialnumber());
		pkiX509Ac.setX509AcDerB64(certCA.getDerBase64());
		pkiX509Ac.setX509EmisorAutoridad(certCA.getNombreComunAutoridad());
		pkiX509Ac.setUrl(certCA.getUrl());
		pkiX509Ac.setTipoCertificado(tipo);
		
		if(serialnumber==null) {
			
			Optional<PkiX509AcAutorizadas> certPadre = pkiX509AcAutorizadasRepository.findById(EnumTipoCerificado.ID_COMODIN.getOpcion());
			pkiX509Ac.setX509EmisorSerialParent(certPadre.get());
			PkiX509AcAutorizadas pkiX509AcGuardado = pkiX509AcAutorizadasRepository.save(pkiX509Ac);
			pkiX509Ac.setX509EmisorSerialParent(pkiX509AcGuardado);
			pkiX509AcGuardado = pkiX509AcAutorizadasRepository.save(pkiX509Ac);
			res.setStatus("Success");
			res.setMessage("El certificado "+tipo+"se ha guardado satisfactoriamente");
			res.setData(null);
			return pkiX509AcGuardado;
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
			return pkiX509AcGuardado;
		}
	}

	public CertCA saveACAutorizada(byte[] certData, String serialnumber, String tipo, DTOResponse res) {
		InputStream inStream = new ByteArrayInputStream(certData);
		CertCA certCA = new CertCA(inStream);
		storeAC(certCA, res, serialnumber, tipo);
		return certCA;
	}
	
	public PkiX509AcAutorizadas findParent(DTOIssuerSubjectData child, List<PkiX509AcAutorizadas> certACList){
		CertificateUtils certUtils = new CertificateUtils();
		Gson gson = new Gson();

		X509Certificate X509Certificate = null;
		InputStream inStream = null;
		CertCA certCA = null;
		for(PkiX509AcAutorizadas certAC : certACList) {
			if(certAC.getX509AcDerB64()!=null) {
				X509Certificate = certUtils.convertBase64ToCert(certAC.getX509AcDerB64());
				inStream = certUtils.convertCertToInputStream(X509Certificate);
				certCA = new CertCA(inStream);
				
		        String childJsonString = gson.toJson(child);
		        String parentJsonString = gson.toJson(certCA.getSubjectData());
		        
				if(childJsonString.equals(parentJsonString)){
					return certAC;
				}
			}
		}
		
		return null;
	}
	
	public DTOResponse addCertificate(byte[] certData, String tipoCertificado, DTOResponse res, DTOResponseUserInfo userInfo) {
			
		InputStream inStream = new ByteArrayInputStream(certData);
		List<PkiX509AcAutorizadas> certACList = pkiX509AcAutorizadasRepository.findAll();
		
		if(tipoCertificado.equals(EnumTipoCerificado.CERT_USUARIO.getOpcion())) {
			try {
				CertUser certUser = new CertUser(inStream);
				
				Optional<PkiUsuariosCert> certificateFound = pkiUsuariosCertRepository.findByX509SerialNumber(certUser.getSerialnumber());
				if(certificateFound.isPresent()) {
					res.setMessage("No se puede cargar un certificado que ya está registrado en la base de datos, contacte a su administrador");
					res.setStatus("fail");
					return res;
				}
				
				PkiX509AcAutorizadas parent = findParent(certUser.getIssuerData(), certACList);
				if(parent==null) {
					res.setMessage("No se encuentra la autoridad certificada emisora de tu certificado, solicita al administrador del sistema que la agregue");
					res.setStatus("fail");
					return res;
				}
				
				storeUserCert(certUser, parent, userInfo, res);
				
				return res;
				
			}catch(Exception e) {
				res.setMessage("Ha ocurrido un error "+e);
			}
			
		}else if(tipoCertificado.equals(EnumTipoCerificado.CERT_AC.getOpcion())) {
			
			try {
				CertCA certCA = new CertCA(inStream);
				String tipo = certCA.getUrl()!=null?EnumTipoCerificado.CERT_OCSP.getOpcion():EnumTipoCerificado.CERT_INTERMEDIO.getOpcion();
				String serialParent = null;
				Optional<PkiX509AcAutorizadas> certificateFound = pkiX509AcAutorizadasRepository.findById(certCA.getSerialnumber());
				if(certificateFound.isPresent()) {
					res.setMessage("No se puede cargar un certificado que ya está registrado en la base de datos, contacte a su administrador");
					res.setStatus("fail");
					return res;
				}
				
				PkiX509AcAutorizadas parent = findParent(certCA.getIssuerData(), certACList);
				if(parent!=null) {
					serialParent = parent.getId();
				}
				PkiX509AcAutorizadas pkiX509AcGuardado = storeAC(certCA, res, serialParent, tipo);
				
				updateChildsBelogsToCurrentAC(certACList, certCA, pkiX509AcGuardado);
				
				return res;
			}catch(Exception e) {
				res.setMessage("Ha ocurrido un error "+e);
			}
		}
		res.setMessage("El tipo de certificado que introduciste es incorrecto");
		res.setStatus("fail");
		
		return res;
	}
	
	public void updateChildsBelogsToCurrentAC(List<PkiX509AcAutorizadas> certACList, CertCA certCAParent, PkiX509AcAutorizadas pkiX509AcGuardado) {
		X509Certificate X509Certificate = null;
		InputStream inStream = null;
		CertCA certCA = null;
		CertificateUtils certUtils = new CertificateUtils();
		Gson gson = new Gson();
		for(PkiX509AcAutorizadas certACObject : certACList) {
			if(!certACObject.getId().equals(EnumTipoCerificado.ID_COMODIN.getOpcion())) {
				if(certACObject.getId().equals(certACObject.getIdParent())) {
					X509Certificate = certUtils.convertBase64ToCert(certACObject.getX509AcDerB64());
					inStream = certUtils.convertCertToInputStream(X509Certificate);
					certCA = new CertCA(inStream);
					
			        String childJsonString = gson.toJson(certCA.getIssuerData());
			        String parentJsonString = gson.toJson(certCAParent.getSubjectData());
			        
					if(childJsonString.equals(parentJsonString)){
						certACObject.setX509EmisorSerialParent(pkiX509AcGuardado);
						pkiX509AcAutorizadasRepository.save(certACObject);
						System.out.println("Se actualiza el certificado padre de: "+certACObject.getId()+" a "+certCAParent.getSerialnumber());
					}
				}else {
					System.out.println("AC asociados: "+certACObject.getId());
				}
			}
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

//		pkiX509r.setX509SerialNumber(certUser.getSerialnumber());
//		pkiX509r.setX509DerB64(certUser.getDerBase64());
//		pkiX509r.setX509Sha256Cert(certUser.getCertSha256());
//		pkiX509r.setX509EmisorSerial(certUser.getEmisorSerial());
//		pkiX509r.setX509Rfc(certUser.getRfc());
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
