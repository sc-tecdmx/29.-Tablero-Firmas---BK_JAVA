package mx.gob.tecdmx.firmapki.api.documento;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.firma.DAOAltaDocumento;
import mx.gob.tecdmx.firmapki.api.firma.PayloadFirmar;
import mx.gob.tecdmx.firmapki.api.firma.ServiceFirmarAhora;
import mx.gob.tecdmx.firmapki.security.ServiceSecurity;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@RestController
@RequestMapping(path = "/api/documento")
public class RestControllerDocumento {
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
	@RequestMapping(method = RequestMethod.POST, path = "/firmar-documento-escritorio", produces = "application/json")
	public ResponseEntity<DTOResponse> firmarDocumentoEscritorio(@RequestBody PayloadFirma payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceDocumento.firmarEscritorio(payload, res, userInfo);
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
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento-modo-captura-deprecated", produces = "application/json")
	public ResponseEntity<DTOResponse> createDocumentoCaptura_(@RequestBody PayloadAltaDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceDocumento.altaDocumentov2(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento-modo-captura", produces = "application/json")
	public ResponseEntity<DTOResponse> createDocumentoCaptura(@RequestBody PayloadAltaDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		if (userInfo == null) {
			return ResponseEntity.ok().header(null).body(res);
		}
		DAOAltaDocumento documentoAlta = null;
		boolean docAlta = serviceFirmarAhora.altaDocumentoModoCaptura(payload, documentoAlta, res, userInfo);
		if (docAlta) {
			res.setData(payload);
		}
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/alta-documento-modo-firmar-ahora-deprecated", produces = "application/json")
	public ResponseEntity<DTOResponse> createDocumentoFirmarAhora_(@RequestBody PayloadAltaDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		if (userInfo == null) {
			return ResponseEntity.ok().header(null).body(res);
		}
		serviceDocumento.altaDocumentoFirmarAhora(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

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
	@RequestMapping(method = RequestMethod.POST, path = "/rechazar-documento", produces = "application/json")
	public ResponseEntity<DTOResponse> rechazarDocumento(@RequestBody PayloadRechazarDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceDocumento.rechazarDocumento(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/enviar-documento-deprecated", produces = "application/json")
	public ResponseEntity<DTOResponse> enviarDocumento_(@RequestBody PayloadEnviarDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		serviceDocumento.enviarDocumento(payload, res, userInfo);
		return ResponseEntity.ok().header(null).body(res);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/enviar-documento", produces = "application/json")
	public ResponseEntity<DTOResponse> enviarDocumento(@RequestBody PayloadEnviarDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		boolean enviado = serviceFirmarAhora.enviarDocumento(payload.getIdDocumento(), userInfo, res);
		if (!enviado) {
			return ResponseEntity.ok().header(null).body(res);
		}
		return ResponseEntity.ok().header(null).body(res);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.PUT, path = "/editar-documento/{idDocumento}", produces = "application/json")
	public ResponseEntity<DTOResponse> editarDocumento(@PathVariable("idDocumento") int idDocumento,
			@RequestBody PayloadAltaDocumento payload,
			HttpServletRequest request) {
		DTOResponse res = new DTOResponse();
		DTOResponseUserInfo userInfo = serviceSecurity.getUserInfo(request, res);
		if (userInfo == null) { 
			return ResponseEntity.ok().header(null).body(res);
		}
		DAOAltaDocumento documentoAlta = null;
		boolean docAlta = serviceFirmarAhora.editarDocumento(idDocumento, payload, documentoAlta, res, userInfo);
		if (docAlta) {
			res.setData(payload);
		}
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
	
