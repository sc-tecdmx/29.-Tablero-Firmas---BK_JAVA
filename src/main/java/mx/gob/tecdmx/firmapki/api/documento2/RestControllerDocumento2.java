package mx.gob.tecdmx.firmapki.api.documento2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.firma.PayloadFirmar;
import mx.gob.tecdmx.firmapki.api.firma.ServiceFirmarAhora;
import mx.gob.tecdmx.firmapki.security.ServiceSecurity;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.dto.PayloadAltaDocumento;


@RestController
@RequestMapping(path = "/api/documento")
public class RestControllerDocumento2 {
	@Autowired
	ServiceDocumento serviceDocumento;

	@Autowired
	ServiceSecurity serviceSecurity;

	@Autowired
	ServiceFirmarAhora serviceFirmarAhora;

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/firmar-documento", produces = "application/json")
	public ResponseEntity<DTOResponse> firmarDocumento(@RequestBody PayloadFirma payload, HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceDocumento.firmar(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET, path = "/documentos-usuario", produces = "application/json")
	public ResponseEntity<DTOResponse> documentosUsuario(HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceDocumento.getDocumentosByUsuario(res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento", produces = "application/json")
	public ResponseEntity<DTOResponse> createDocumento(@RequestBody PayloadAltaDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceDocumento.altaDocumento(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/go-to-firmar", produces = "application/json")
	public ResponseEntity<DTOResponse> createDocumentoCaptura(@RequestBody PayloadFirmar payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		if (userInfo == null) {
			return ResponseEntity.ok().header(null).body(res);
		}
		boolean docFirmado = serviceFirmarAhora.gotoFirmar(payload.getIdDocumento(), userInfo, res);
		if (docFirmado) {
			res.setData(payload);
		}
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET, path = "/numero-serie", produces = "application/json")
	public ResponseEntity<DTOResponse> getNumeroSerieUser(HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceDocumento.getUserSerial(userInfo, res);
		return ResponseEntity.ok().header(null).body(res);
	}

}
	
