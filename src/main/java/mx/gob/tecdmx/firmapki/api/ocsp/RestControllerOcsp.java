package mx.gob.tecdmx.firmapki.api.ocsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;

@RestController
@RequestMapping(path = "/api")
public class RestControllerOcsp {
	
	@Autowired
	ServiceOCSP serviceOCSP;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/ocsp-validar", produces = "application/json")
    public ResponseEntity<DTOResponse> verifyOcsp(@RequestBody PayloadOCSP payload) {
		DTOResponse res = new DTOResponse();
		boolean validated = serviceOCSP.validateOCSP(payload, res);
		if(!validated) {
			return ResponseEntity.ok().header(null).body(res); 
		}
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/validar-certificado-vigente-by-ocsp", produces = "application/json")
    public ResponseEntity<String> verifyCertificadoVigenciaOcsp(@RequestBody PayloadCertVigenciaOCSP payload) {
		CertificateUtils utils = new CertificateUtils();
		DTOResponse res = new DTOResponse();
		serviceOCSP.validateOCSP(payload, res);
		return ResponseEntity.ok().header(null).body(utils.objectToJson(res));
	}

}
