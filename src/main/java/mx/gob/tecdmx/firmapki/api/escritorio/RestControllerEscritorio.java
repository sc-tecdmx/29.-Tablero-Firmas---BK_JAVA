package mx.gob.tecdmx.firmapki.api.escritorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/escritorio")
public class RestControllerEscritorio {
	
	@Autowired
	ServiceLoginEscritorio serviceLoginEscritorio;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/login-escritorio", produces = "application/json")
    public ResponseEntity<DTORsponseEscritorio> loginEscritorio(@RequestBody PayloadCertLoginEscritorio payload) {
		DTORsponseEscritorio res = new DTORsponseEscritorio();
		serviceLoginEscritorio.login(payload, res);
		return ResponseEntity.ok().header(null).body(res);
	}

}
