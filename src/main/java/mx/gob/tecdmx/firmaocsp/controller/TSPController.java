package mx.gob.tecdmx.firmaocsp.controller;

import java.io.IOException;

import org.bouncycastle.tsp.TimeStampResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmaocsp.dto.PayloadTSA;
import mx.gob.tecdmx.firmaocsp.dto.TsaDTO;
import mx.gob.tecdmx.firmaocsp.service.TCPService;

@RestController
@RequestMapping(path = "/api/tsa-tsp")
public class TSPController {
	
	

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/", produces = "application/json")
    public TsaDTO medodoTSP(@RequestBody PayloadTSA payload) {
		
		TCPService tcpService = new TCPService();
		try {
			TimeStampResponse response = tcpService.getTimestampForPdf(payload.getDigest());
			return tcpService.timeStampResponse(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
