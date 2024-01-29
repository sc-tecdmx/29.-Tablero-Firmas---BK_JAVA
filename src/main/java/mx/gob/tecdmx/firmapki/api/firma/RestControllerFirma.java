package mx.gob.tecdmx.firmapki.api.firma;

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
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.dto.PayloadAltaDocumento;

@RestController
@RequestMapping(path = "/api/firma")
public class RestControllerFirma {

	@Autowired
	ServiceSecurity serviceSecurity;

	@Autowired
	ServiceFirmarAhora serviceFirmarAhora;

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento-modo-firmar-ahora", produces = "application/json")
	public ResponseEntity<DTOResponse> createDocumentoFirmarAhora(@RequestBody PayloadAltaDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		if (userInfo == null) {
			return ResponseEntity.ok().header(null).body(res);
		}
		DAOAltaDocumento documentoAlta = null;
		boolean docAltafirmado = serviceFirmarAhora.altaDocAndfirmarAhora(payload, documentoAlta, res, userInfo);
		if (docAltafirmado) {
			res.setData(payload);
		}
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

	
}
