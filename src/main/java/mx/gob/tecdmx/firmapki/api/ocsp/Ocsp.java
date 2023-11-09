package mx.gob.tecdmx.firmapki.api.ocsp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.CertificateStatus;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPReq;
import org.bouncycastle.cert.ocsp.OCSPReqBuilder;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.RevokedStatus;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.bouncycastle.cert.ocsp.UnknownStatus;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.encoders.Base64;

import mx.gob.tecdmx.firmapki.api.populate.CertCA;
import mx.gob.tecdmx.firmapki.api.populate.CertUser;

public class Ocsp {

	CertUser certUser;
	CertCA certIssuer;
	CertCA certOCSP;
	boolean isValid;
	String message;
	String basicResponse;
	Date fechaUTC;
	String status = "";
	String indicador;
	String uuidOCSP = "UNKNOWN";

	public Ocsp(CertUser certUser, CertCA certIssuer, CertCA certOCSP) {
		super();
		this.certUser = certUser;
		this.certIssuer = certIssuer;
		this.certOCSP = certOCSP;
		try {
			this.isValid = check();
		} catch (OcspValidationException | OcspTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean check() throws OcspValidationException, OcspTimeoutException {
		try {
			BigInteger serialNumber = this.certUser.getCertificate().getSerialNumber();
			X509CertificateHolder holder;
			try {
				holder = new X509CertificateHolder(this.certIssuer.getCertificate().getEncoded());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			CertificateID id = new CertificateID(new JcaDigestCalculatorProviderBuilder()
					.setProvider(BouncyCastleProvider.PROVIDER_NAME).build().get(CertificateID.HASH_SHA1), holder,
					serialNumber);

			OCSPReqBuilder ocspGen = new OCSPReqBuilder();
			ocspGen.addRequest(id);

			OCSPReq ocspReq = ocspGen.build();

			// Ir al OCSP
			
			if (this.certOCSP.getUrl() == null) {
				this.message = "URL de OCSP is null";
				this.isValid = false;
				return false;
			}

			URL url;
			try {
				url = new URL(this.certOCSP.getUrl());
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}

			HttpURLConnection con;
			OCSPResp ocspResponse;

			try {
				con = (HttpURLConnection) url.openConnection();
				con.setRequestProperty("Content-Type", "application/ocsp-request");
				con.setRequestProperty("Accept", "application/ocsp-response");
				con.setDoOutput(true);

				OutputStream out = con.getOutputStream();
				DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(out));
				dataOut.write(ocspReq.getEncoded());

				dataOut.flush();
				dataOut.close();

				InputStream in = (InputStream) con.getContent();
				byte[] resp = read(in); // Read the reponse
				ocspResponse = new OCSPResp(resp);

			} catch (IOException e) {
				this.message = "Error de conexión con OCSP";
				throw new OcspTimeoutException(url);
			}
			//
			//int status = ocspResponse.getStatus();
			BasicOCSPResp basicResponse = (BasicOCSPResp) ocspResponse.getResponseObject();
			this.basicResponse = convertBasicOCSPRespToBase64(basicResponse);

			// issuerCert, x509Cert
			try {
				boolean statusResponse = checkOcspSignature(this.certOCSP.getCertificate(), basicResponse);
				this.status = statusResponse ? "VALID" : "INVALID";
				if(!statusResponse) {
					return false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (basicResponse != null) {
				SingleResp[] responses = basicResponse.getResponses();
				SingleResp response = responses[0];
				CertificateStatus certStatus = response.getCertStatus();

				if (certStatus instanceof RevokedStatus) {
					RevokedStatus revokedStatus = (RevokedStatus) certStatus;
					this.message = "(REVOKED) - Reason: " + revokedStatus.getRevocationReason()+" Date: " + revokedStatus.getRevocationTime();
					this.indicador = "REVOKED";
					throw new OcspValidationException(revokedStatus.getRevocationReason(),
							revokedStatus.getRevocationTime());
				} else if (certStatus instanceof UnknownStatus) {
					this.message = "El estado del certificado es desconocido.";
					this.status = this.status+=" "+"UNKNOWN";
					this.indicador = "UNKNOWN";
				}
				if (certStatus == CertificateStatus.GOOD) {
					this.message = "El certificado está en buen estado (Good).";
					this.status = this.status+=" "+"GOOD";
					this.indicador = "GOOD";
					this.isValid = true;
					return true;
				} else {
					this.status = this.status+=" "+"No se encuentra el motivo de certificado no válido " + certStatus;
				}
			}
		} catch (OCSPException e) {
			throw new RuntimeException(e);
		} catch (CertificateEncodingException e) {
			throw new RuntimeException(e);
		} catch (OperatorCreationException e) {
			throw new RuntimeException(e);
		}
		this.isValid = false;
		return false;
	}

	private boolean checkOcspSignature(X509Certificate certificate, BasicOCSPResp basicResponse)
			throws OCSPException, IOException {
		try {
			ContentVerifierProvider verifier = new JcaContentVerifierProviderBuilder()
					.setProvider(java.security.Security.getProvider(BouncyCastleProvider.PROVIDER_NAME))
					.build(certificate);
			if (!basicResponse.isSignatureValid(verifier)) {
				this.message = "OCSP-Signature is not valid!";
				this.isValid = false;
				this.fechaUTC = basicResponse.getProducedAt();
				return false;
			} else {
				this.message = "OCSP-Signature is valid!";
				this.isValid = true;
				this.fechaUTC = basicResponse.getProducedAt();
				return true;
			}
		} catch (OperatorCreationException e) {
			throw new OCSPException("Error checking Ocsp-Signature", e);
		}
	}

	public String convertBasicOCSPRespToBase64(BasicOCSPResp basicResponse) {
		byte[] responseBytes;
		try {
			responseBytes = basicResponse.getEncoded();
			String base64Response = new String(Base64.encode(responseBytes));
			return base64Response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private static byte[] read(InputStream in) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int next = in.read();
		while (next > -1) {
			bos.write(next);
			next = in.read();
		}
		bos.flush();
		return bos.toByteArray();
	}

	public CertUser getCertUser() {
		return certUser;
	}

	public void setCertUser(CertUser certUser) {
		this.certUser = certUser;
	}

	public CertCA getCertIssuer() {
		return certIssuer;
	}

	public void setCertIssuer(CertCA certIssuer) {
		this.certIssuer = certIssuer;
	}

	public CertCA getCertOCSP() {
		return certOCSP;
	}

	public void setCertOCSP(CertCA certOCSP) {
		this.certOCSP = certOCSP;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBasicResponse() {
		return basicResponse;
	}

	public void setBasicResponse(String basicResponse) {
		this.basicResponse = basicResponse;
	}

	public Date getFechaUTC() {
		return fechaUTC;
	}

	public void setFechaUTC(Date fechaUTC) {
		this.fechaUTC = fechaUTC;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUuidOCSP() {
		return uuidOCSP;
	}

	public void setUuidOCSP(String uuidOCSP) {
		this.uuidOCSP = uuidOCSP;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

}
