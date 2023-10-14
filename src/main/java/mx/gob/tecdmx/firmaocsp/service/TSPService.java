package mx.gob.tecdmx.firmaocsp.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import org.bouncycastle.tsp.TSPAlgorithms;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.json.JSONObject;

import mx.gob.tecdmx.firmaocsp.dto.TsaDTO;

public class TSPService {
	
	private static final String TSA_URL = "http://firel.uncocefi.cjf.gob.mx/tsacjf/";
	
	
	public TimeStampResponse getTimestampForPdf(byte[] pdfData) throws Exception {
    	
    	MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	byte[] sha256Digest = digest.digest(pdfData);
    	
    	TimeStampRequestGenerator reqGen = new TimeStampRequestGenerator();
    	reqGen.setCertReq(true);
    	TimeStampRequest timeStampRequest = reqGen.generate(TSPAlgorithms.SHA256, sha256Digest);

    	
   
        byte[] reqData = timeStampRequest.getEncoded();

        // 2. Enviar la solicitud al servidor TSA
        URL url = new URL(TSA_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/timestamp-query");
        conn.setRequestProperty("Content-Transfer-Encoding", "binary");

        try (OutputStream out = conn.getOutputStream()) {
            out.write(reqData);

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("TSA server returned HTTP response code: " + responseCode);
            }

            try (InputStream in = conn.getInputStream()) {
                // 3. Procesar la respuesta de la TSA
                TimeStampResponse response = new TimeStampResponse(in);
                response.validate(timeStampRequest);  // Verificar la respuesta contra la solicitud original
                return response;
            }
        }
    }
	
	public TsaDTO timeStampResponse(TimeStampResponse response) {
        JSONObject json = new JSONObject();
        TsaDTO tsaDto = new TsaDTO();

        // Estado de la respuesta
        json.put("status", response.getStatus());
        tsaDto.setStatus(response.getStatus()+"");

        if (response.getTimeStampToken() != null) {
            // Sello de tiempo
            json.put("timestamp", response.getTimeStampToken().getTimeStampInfo().getGenTime());
            tsaDto.setTimestamp(response.getTimeStampToken().getTimeStampInfo().getGenTime()+"");

            // Algoritmo de hash utilizado
            json.put("hashAlgorithm", response.getTimeStampToken().getTimeStampInfo().getMessageImprintAlgOID().getId());
            tsaDto.setHashAlgorithm(response.getTimeStampToken().getTimeStampInfo().getMessageImprintAlgOID().getId()+"");
            //tsaDto.setCertificate(response.get);
        } else {
            // En caso de error, agregar detalles del error
            json.put("errorDetails", response.getStatusString());
        }

        return tsaDto;
    } 

}
