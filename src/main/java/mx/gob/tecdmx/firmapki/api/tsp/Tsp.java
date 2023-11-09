package mx.gob.tecdmx.firmapki.api.tsp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.tsp.TSPAlgorithms;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.util.encoders.Base64;

public class Tsp {

	String tsaURL;
	String messageDigest;
	TimeStampResponse response;
	int status;
	String numSerieTimeStamp;
	String datosEstampillados;
	Date fechaUTC;
	String nombreRespondedor;
	String emisorRespondedor;
	String secuencia;
	String timeStampResponseBase64;
	String uuIdTSP;

	public Tsp(String tsaURL, String messageDigest) {
		super();
		this.tsaURL = tsaURL;
		this.messageDigest = messageDigest;
		try {
			this.response = getTimestampForMessageDigest();
			timeStampResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TimeStampResponse getTimestampForMessageDigest() throws Exception {

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] sha256Digest = digest.digest(this.messageDigest.getBytes());

		TimeStampRequestGenerator reqGen = new TimeStampRequestGenerator();
		reqGen.setCertReq(true);
		TimeStampRequest timeStampRequest = reqGen.generate(TSPAlgorithms.SHA256, sha256Digest);

		byte[] reqData = timeStampRequest.getEncoded();

		// 2. Enviar la solicitud al servidor TSA
		URL url = new URL(this.tsaURL);
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
				response.validate(timeStampRequest); // Verificar la respuesta contra la solicitud original
				return response;
			}
		}
	}

	public void timeStampResponse() {
		// Status response
		this.status = this.response.getStatus();

		// Serial number,
		SignerId signerId = this.response.getTimeStampToken().getSID();
		BigInteger serialNumber = signerId.getSerialNumber();
		this.numSerieTimeStamp = serialNumber.toString();

		// Stamper data:
		byte[] messageImprint = this.response.getTimeStampToken().getTimeStampInfo().getMessageImprintDigest();
		String hex = bytesToHex(messageImprint);
		this.datosEstampillados = hex;

		// Fecha
		this.fechaUTC = this.response.getTimeStampToken().getTimeStampInfo().getGenTime();

		// Nombre del Respondedor:
		ASN1Encodable nameTSA = this.response.getTimeStampToken().getTimeStampInfo().getTsa().getName();
		X500Name x500name = (X500Name) nameTSA;
		RDN cnRDN = x500name.getRDNs(BCStyle.CN)[0];
		String commonName = IETFUtils.valueToString(cnRDN.getFirst().getValue());
		this.nombreRespondedor = commonName;

		ASN1Encodable issuerNameTSA = signerId.getIssuer();
		X500Name x500issuerName = (X500Name) issuerNameTSA;
		RDN cnRDNIssuer = x500issuerName.getRDNs(BCStyle.CN)[0];
		String commonIssuerName = IETFUtils.valueToString(cnRDNIssuer.getFirst().getValue());
		this.emisorRespondedor = commonIssuerName;

		this.secuencia = this.response.getTimeStampToken().getTimeStampInfo().getSerialNumber().toString();

		this.timeStampResponseBase64 = convertTimeStampRespToBase64(this.response);
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();

		for (byte b : bytes) {
			String hex = Integer.toHexString(0xFF & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString.toString().toUpperCase();
	}

	public static String convertTimeStampRespToBase64(TimeStampResponse timeStampResponse) {

		byte[] responseBytes;
		try {
			responseBytes = timeStampResponse.getEncoded();
			// Convierte los bytes a una representación Base64
			String base64Response = new String(Base64.encode(responseBytes));

			// Imprime la representación en Base64
			System.out.println("Base64 Response: " + base64Response);
			return base64Response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String getTsaURL() {
		return tsaURL;
	}

	public void setTsaURL(String tsaURL) {
		this.tsaURL = tsaURL;
	}

	public String getMessageDigest() {
		return messageDigest;
	}

	public void setMessageDigest(String messageDigest) {
		this.messageDigest = messageDigest;
	}

	public TimeStampResponse getResponse() {
		return response;
	}

	public void setResponse(TimeStampResponse response) {
		this.response = response;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNumSerieTimeStamp() {
		return numSerieTimeStamp;
	}

	public void setNumSerieTimeStamp(String numSerieTimeStamp) {
		this.numSerieTimeStamp = numSerieTimeStamp;
	}

	public String getDatosEstampillados() {
		return datosEstampillados;
	}

	public void setDatosEstampillados(String datosEstampillados) {
		this.datosEstampillados = datosEstampillados;
	}

	public Date getFechaUTC() {
		return fechaUTC;
	}

	public void setFechaUTC(Date fechaUTC) {
		this.fechaUTC = fechaUTC;
	}

	public String getNombreRespondedor() {
		return nombreRespondedor;
	}

	public void setNombreRespondedor(String nombreRespondedor) {
		this.nombreRespondedor = nombreRespondedor;
	}

	public String getEmisorRespondedor() {
		return emisorRespondedor;
	}

	public void setEmisorRespondedor(String emisorRespondedor) {
		this.emisorRespondedor = emisorRespondedor;
	}

	public String getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	public String getTimeStampResponseBase64() {
		return timeStampResponseBase64;
	}

	public void setTimeStampResponseBase64(String timeStampResponseBase64) {
		this.timeStampResponseBase64 = timeStampResponseBase64;
	}

	public String getUuIdTSP() {
		return uuIdTSP;
	}

	public void setUuIdTSP(String uuIdTSP) {
		this.uuIdTSP = uuIdTSP;
	}
	
	

}
