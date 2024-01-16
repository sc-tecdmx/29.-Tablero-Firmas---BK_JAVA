package mx.gob.tecdmx.firmapki.api.populate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.security.ServiceSecurity;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;


@RestController
@RequestMapping(path = "/api/x509")
public class RestControllerPopulate {
	
	@Autowired
	ServicePopulate x509Service;
	
	@Autowired
	ServiceSecurity serviceSecurity;

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/agregar-ac-autorizada", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DTOResponse> saveACAutorizada(@RequestParam("certificado") MultipartFile certificate, String serialnumber, String tipo, HttpServletResponse response) throws IOException {
		DTOResponse res = new DTOResponse();
		x509Service.saveACAutorizada(certificate.getBytes(), serialnumber, tipo, res);
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/agregar-cert-usuario", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DTOResponse> saveCertUser(@RequestParam("certificado") MultipartFile certificate, String serialnumber, HttpServletResponse response, HttpServletRequest request) throws IOException {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		
		x509Service.saveCertUser(certificate.getBytes(), serialnumber, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/agregar-certificado", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DTOResponse> addCertificate(@RequestBody PayloadAgregarCertificado payload, HttpServletResponse response, HttpServletRequest request) throws IOException {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		
		x509Service.addCertificate(payload.getCertificate(), payload.getTipoCertificado(), res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

}
