package mx.gob.tecdmx.firmaocsp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmaocsp.dto.PayloadTSA;
import mx.gob.tecdmx.firmaocsp.dto.TsaDTO;

@RestController
@RequestMapping(path = "/api/tsa-tsp")
public class TSAController {

	@PostMapping("/")
    public TsaDTO uploadFile(@RequestBody PayloadTSA payload) {
		
		TsaDTO tsaDto = new TsaDTO();
		Date fechaHoraActual = new Date();
		//Validar datos
		

        // Define el formato deseado
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

        // Formatea la fecha y hora en el formato deseado
        String selloDeTiempo = formato.format(fechaHoraActual);
        tsaDto.setTimestamp(selloDeTiempo);
        tsaDto.setCertificate("-----BEGIN CERTIFICATE-----\n"+payload.getCertificado()+"\n-----END CERTIFICATE-----");
        tsaDto.setStatus("Valid");

        return tsaDto;
    }
}
