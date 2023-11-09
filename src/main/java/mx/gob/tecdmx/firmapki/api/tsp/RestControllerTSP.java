package mx.gob.tecdmx.firmapki.api.tsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@RestController
@RequestMapping(path = "/api")
public class RestControllerTSP {
	
	@Autowired
	ServiceTSP serviceTSP;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/tsp-timestamp", produces = "application/json")
    public ResponseEntity<DTOResponse> verifyTSP(@RequestBody PayloadTSP payload) {
		DTOResponse res = new DTOResponse();
		serviceTSP.validateTSP(payload, res);
		return ResponseEntity.ok().header(null).body(res);
	}
	

}
