package mx.gob.tecdmx.firmaocsp.controller;

import java.security.cert.X509Certificate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.gob.tecdmx.firmaocsp.service.VerificarOCSPService;
import mx.gob.tecdmx.firmaocsp.utils.CommonUtils;
import mx.gob.tecdmx.firmaocsp.utils.OcspTimeoutException;
import mx.gob.tecdmx.firmaocsp.utils.OcspValidationException;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping(path = "/api")
public class VersionController {

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET, path = "/v", produces = "application/json")
	public String version() {
		return "v1.0";
	}
	
}
