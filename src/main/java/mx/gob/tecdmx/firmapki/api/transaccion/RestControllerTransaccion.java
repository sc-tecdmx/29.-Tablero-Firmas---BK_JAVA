package mx.gob.tecdmx.firmapki.api.transaccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@RestController
@RequestMapping(path = "/api/firma/transaccion")
public class RestControllerTransaccion {
	
	@Autowired
	ServiceTransaccion serviceTransaccion;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/get-transaccion", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DTOResponse> getTransaccion(@RequestBody PayloadTransaccion payload){
		DTOResponse res = new DTOResponse();
		serviceTransaccion.getTransaccion(payload.getCertificado(), res);
		return ResponseEntity.ok().header(null).body(res);
	}

}
