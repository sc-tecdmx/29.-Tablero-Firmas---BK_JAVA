package mx.gob.tecdmx.firmapki.api.transaccion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.security.ServiceSecurity;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;

@RestController
@RequestMapping(path = "/api/firma/transaccion")
public class RestControllerTransaccion {
	
	@Autowired
	ServiceTransaccion serviceTransaccion;
	
	@Autowired
	ServiceSecurity serviceSecurity;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/get-transaccion", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DTOResponse> getTransaccion(@RequestBody PayloadTransaccion payload, HttpServletRequest request){
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		if(userInfo==null) {
			return ResponseEntity.ok().header(null).body(res);
		}
		boolean getTransaccion = serviceTransaccion.getTransaccion(payload.getCertificado(), payload.getHashDocumento(), res, userInfo);
		if(!getTransaccion) {
			return ResponseEntity.ok().header(null).body(res);
		}
		return ResponseEntity.ok().header(null).body(res);
	}

}
