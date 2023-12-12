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
    public ResponseEntity<DTOResponse> firmarDocumento(@RequestBody PayloadFirma payload, HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request);
		serviceDocumento.firmar(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET, path = "/documentos-usuario", produces = "application/json")
	public ResponseEntity<DTOResponse> documentosUsuario(HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request);
		serviceDocumento.getDocumentosByUsuario(res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento-modo-captura", produces = "application/json")
    public ResponseEntity<DTOResponse> createDocumentoCaptura(@RequestBody PayloadAltaDocumento payload, HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request);
		serviceDocumento.altaDocumentov2(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento-modo-firmar-ahora", produces = "application/json")
    public ResponseEntity<DTOResponse> createDocumentoFirmarAhora(@RequestBody PayloadAltaDocumento payload, HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request);
		serviceDocumento.altaDocumentoFirmarAhora(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/rechazar-documento", produces = "application/json")
    public ResponseEntity<DTOResponse> rechazarDocumento(@RequestBody PayloadRechazarDocumento payload, HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request);
		serviceDocumento.rechazarDocumento(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/enviar-documento", produces = "application/json")
    public ResponseEntity<DTOResponse> enviarDocumento(@RequestBody PayloadEnviarDocumento payload, HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request);
		serviceDocumento.enviarDocumento(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento", produces = "application/json")
    public ResponseEntity<DTOResponse> createDocumento(@RequestBody PayloadAltaDocumento payload, HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request);
		serviceDocumento.altaDocumento(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

}
