package mx.gob.tecdmx.firmapki.api.documento;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.security.ServiceSecurity;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@RestController
@RequestMapping(path = "/api/documento")
public class RestControllerDocumento {
	@Autowired
	ServiceDocumento serviceDocumento;
	
	@Autowired
	ServiceSecurity serviceSecurity;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/firmar-documento", produces = "application/json")
    public ResponseEntity<DTOResponse> verifyOcsp(@RequestBody PayloadFirma payload) {
		DTOResponse res = new DTOResponse();
		serviceDocumento.firmar(payload, res);
		return ResponseEntity.ok().header(null).body(res);
	}
	
//	@CrossOrigin()
//	@RequestMapping(method = RequestMethod.POST, path = "/crear-documento", produces = "application/json")
//    public ResponseEntity<DTOResponse> createDocumento(@RequestBody PayloadDocumento payload) {
//		DTOResponse res = new DTOResponse();
//		serviceDocumento.createDocumento(payload, res);
//		return ResponseEntity.ok().header(null).body(res);
//	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento", produces = "application/json")
    public ResponseEntity<DTOResponse> createDocumento(@RequestBody PayloadAltaDocumento payload, HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request);
		serviceDocumento.altaDocumento(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

}
