package mx.gob.tecdmx.firmaocsp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class VersionController {

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET, path = "/v", produces = "application/json")
	public String version() {
		return "v1.0";
	}
	
}
