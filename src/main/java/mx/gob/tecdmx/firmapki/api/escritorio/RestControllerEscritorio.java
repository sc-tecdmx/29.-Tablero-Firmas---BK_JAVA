package mx.gob.tecdmx.firmapki.api.escritorio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.documento2.PayloadAltaDocumento;
import mx.gob.tecdmx.firmapki.api.documento2.PayloadFirma;
import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.security.ServiceSecurity;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@RestController
@RequestMapping(path = "/api/escritorio")
public class RestControllerEscritorio {
	
	@Autowired
	ServiceLoginEscritorio serviceLoginEscritorio;
	
	@Autowired
	ServiceSecurity serviceSecurity;
	
	@Autowired
	ServiceFirmarEscritorio serviceFirmarEscritorio;
	
	@Autowired
	ServiceAltaDocumentoEscritorio serviceAltaDocumentoEscritorio;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/login-escritorio", produces = "application/json")
    public ResponseEntity<DTORsponseEscritorio> loginEscritorio(@RequestBody PayloadCertLoginEscritorio payload) {
		DTORsponseEscritorio res = new DTORsponseEscritorio();
		serviceLoginEscritorio.login(payload, res);
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/firmar-documento-escritorio", produces = "application/json")
	public ResponseEntity<DTOResponse> firmarDocumentoEscritorio(@RequestBody PayloadFirma payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceFirmarEscritorio.firmarDocumentoEscritorio(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento-modo-firmar-escritorio", produces = "application/json")
	public ResponseEntity<DTOResponse> createDocumentoFirmarAhoraEscritorio(@RequestBody PayloadAltaDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		if (userInfo == null) {
			return ResponseEntity.ok().header(null).body(res);
		}
		DAOAltaDocumento documentoAlta = null;
		boolean docAltafirmado = serviceAltaDocumentoEscritorio.altaDocAndfirmarAhoraEscritorio(payload, documentoAlta, res,
				userInfo);
		if (docAltafirmado) {
			res.setData(payload);
		}
		return ResponseEntity.ok().header(null).body(res);
	}
}
