package mx.gob.tecdmx.firmapki.api.tsp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.entity.pki.PkiTransaccion;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Tsp;
import mx.gob.tecdmx.firmapki.repository.pki.PkiTransaccionRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509TspRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@Service
public class ServiceTSP {
	
	@Value("${firma.url.tsa}")
	private String tsaURL;
	
	@Value("${firma.document.tsp.path}")
    private String evidenciasTspPath;
	
	@Autowired
	PkiX509TspRepository pkiX509TspRepository;
	
	@Autowired
	PkiTransaccionRepository pkiTransaccionRepository;
	
	public boolean validateTSP(PayloadTSP payload, DTOResponse res) {
		CertificateUtils utils = new CertificateUtils();
		Tsp tsp = new Tsp(tsaURL, payload.messageDigest);
		PkiX509Tsp pkiX509Tsp = new PkiX509Tsp();
		
		pkiX509Tsp.setTspResponseDerB64(tsp.getTimeStampResponseBase64());
		
		String fileName = evidenciasTspPath+"/"+utils.formatDate(tsp.getFechaUTC())+"_transaccion_"+payload.getIdTransaccion()+
				"_tsp_responseb64.txt";
		utils.storeBase64ToFile(tsp.getTimeStampResponseBase64(), fileName);
		
		pkiX509Tsp.setTspResponsePath(fileName);
		pkiX509Tsp.setX509SerialStamper(tsp.getNumSerieTimeStamp());
		pkiX509Tsp.setFechaResponse(tsp.getFechaUTC());
		pkiX509Tsp.setTspIndicador(null);
		
		PkiX509Tsp PkiX509TspGuardado = null;
		
		Optional<PkiTransaccion> transaccion = pkiTransaccionRepository.findById(payload.getIdTransaccion());
		if(transaccion.isPresent()) {
			pkiX509Tsp.setX509SerialNumber(transaccion.get().getX509SerialNumber());
			
			try {
				Optional<PkiX509Tsp> lastTSP = pkiX509TspRepository.findTopByOrderByFechaResponseDesc();
				if(lastTSP.isPresent()) {
					pkiX509Tsp.setUuidTspBlock(lastTSP.get());
					PkiX509TspGuardado = pkiX509TspRepository.save(pkiX509Tsp);
				}else {
					PkiX509TspGuardado = pkiX509TspRepository.save(pkiX509Tsp);
					pkiX509Tsp.setUuidTspBlock(PkiX509TspGuardado);
					PkiX509TspGuardado = pkiX509TspRepository.save(pkiX509Tsp);
				}
				
				try {
					transaccion.get().setIdTsp(PkiX509TspGuardado);
					pkiTransaccionRepository.save(transaccion.get());
					
					ResponseBodyTSP responseBody = new ResponseBodyTSP();
					responseBody.setFechaUTC(tsp.getFechaUTC());
					responseBody.setNombreRespondedor(tsp.getNombreRespondedor());
					responseBody.setEmisorRespondedor(tsp.getEmisorRespondedor());
					responseBody.setSecuencia(tsp.getSecuencia());
					responseBody.setDatosEstampillados(tsp.getDatosEstampillados());
					
					res.setData(responseBody);
					res.setStatus("Success");
				    res.setMessage("Se ha realizado la firma de tiempo satisfactoriamente");
				    return true;
				    
				}catch(Exception e) {
					res.setData(null);
					res.setStatus("Failed");
				    res.setMessage("Ocurrió un error al actualizar la información en la tabla de transacción");
				    return false;
				}
				
			}catch(Exception e) {
				res.setData(null);
				res.setStatus("Failed");
			    res.setMessage("Ocurrió un error al almacenar la información en la tabla de TSP");
			    return false;
			}			
		}else {
			res.setData(null);
			res.setStatus("Failed");
		    res.setMessage("Ocurrió un error al almacenar la información, no se encontró el identificador de la transacción");
		    return false;
		}
	}

}
