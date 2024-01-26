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
	ServiceSecurity serviceSecurity;
	
	@Autowired
	ServiceRechazarDocumento serviceRechazarDocumento;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/rechazar-documento", produces = "application/json")
	public ResponseEntity<DTOResponse> rechazarDocumento(@RequestBody PayloadRechazarDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceRechazarDocumento.rechazarDocumento(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}
}
