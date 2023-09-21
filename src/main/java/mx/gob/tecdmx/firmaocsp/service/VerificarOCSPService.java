package mx.gob.tecdmx.firmaocsp.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

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

import mx.gob.tecdmx.firmaocsp.dto.OCSPResponseDTO;
import mx.gob.tecdmx.firmaocsp.utils.OcspTimeoutException;
import mx.gob.tecdmx.firmaocsp.utils.OcspValidationException;

public class VerificarOCSPService {
	
	public static String bytesToHex(byte[] bytes) {
	    StringBuilder hexString = new StringBuilder(2 * bytes.length);
	    for (byte b : bytes) {
	        hexString.append(String.format("%02X", b));
	    }
	    return hexString.toString();
	}
	
	
	private static boolean checkOcspSignature(X509Certificate certificate, BasicOCSPResp basicResponse)
		    throws OCSPException, IOException
		{
		  try
		  {
		    ContentVerifierProvider verifier = new JcaContentVerifierProviderBuilder()
		        .setProvider(java.security.Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)).build(certificate);
		    System.out.println(basicResponse.isSignatureValid(verifier));
		    if (!basicResponse.isSignatureValid(verifier))
		    {
		    	System.out.println("OCSP-Signature is not valid!");
		    }else {
		    	System.out.println("OCSP-Signature is valid!");
		    	return true;
		    }
		  }
		  catch (OperatorCreationException e)
		  {
		    throw new OCSPException("Error checking Ocsp-Signature", e);
		  }
		  return false;
		}

	public static boolean check(X509Certificate vaCert, X509Certificate issuerCert,
			X509Certificate x509Cert, String urlPath, OCSPResponseDTO ocspResponseDTO) throws OcspValidationException,
			OcspTimeoutException {
		try {
			BigInteger serialNumber = x509Cert.getSerialNumber();
			X509CertificateHolder holder;
			X509CertificateHolder holderVa;
			
			try {
				holder = new X509CertificateHolder(issuerCert.getEncoded());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			CertificateID id = new CertificateID(
					new JcaDigestCalculatorProviderBuilder()
							.setProvider(BouncyCastleProvider.PROVIDER_NAME)
							.build().get(CertificateID.HASH_SHA1), holder,
					serialNumber);

			OCSPReqBuilder ocspGen = new OCSPReqBuilder();
			ocspGen.addRequest(id);
	       
			
			
			OCSPReq ocspReq = ocspGen.build();

			// Ir al OCSP
			String ocspUrl = urlPath;

			if (ocspUrl == null) {
				System.out.println("URL de OCSP is null");
				return false;
			}

			URL url;

			try {
				url = new URL(ocspUrl);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}

			HttpURLConnection con;
			OCSPResp ocspResponse;

			try {
				con = (HttpURLConnection) url.openConnection();

				con.setRequestProperty("Content-Type",
						"application/ocsp-request");
				con.setRequestProperty("Accept", "application/ocsp-response");
				con.setDoOutput(true);

				OutputStream out = con.getOutputStream();
				DataOutputStream dataOut = new DataOutputStream(
						new BufferedOutputStream(out));
				dataOut.write(ocspReq.getEncoded());

				dataOut.flush();
				dataOut.close();

				/*
				 * Se parsea la respuesta y se obtiene el estado del certificado
				 * retornado por el OCSP
				 */
				InputStream in = (InputStream) con.getContent();
				byte[] resp = read(in); // Read the reponse
				ocspResponse = new OCSPResp(resp);
				
			} catch (IOException e) {
				throw new OcspTimeoutException(url);
			}
			//
			int status = ocspResponse.getStatus();
			System.out.println("status=" + ocspResponse.getStatus());
			
			BasicOCSPResp basicResponse = (BasicOCSPResp) ocspResponse
					.getResponseObject();
			System.out.println(basicResponse.getCerts()[0].getSubject().toString());
			
			ocspResponseDTO.setBasicResponse(basicResponse.getResponses()[0].toString());
			
			//issuerCert, x509Cert
			try {
				boolean statusResponse = checkOcspSignature(vaCert, basicResponse);
				ocspResponseDTO.setStatus(statusResponse?"Valid":"Invalid");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			if (basicResponse != null) {
				SingleResp[] responses = basicResponse.getResponses();
				SingleResp response = responses[0];
				CertificateStatus certStatus = response.getCertStatus();

				if (certStatus instanceof RevokedStatus) {
					System.out.println("REVOKED");
					RevokedStatus revokedStatus = (RevokedStatus) certStatus;
					System.out.println("Reason: "
							+ revokedStatus.getRevocationReason());
					System.out.println("Date: "
							+ revokedStatus.getRevocationTime());

					throw new OcspValidationException(
							revokedStatus.getRevocationReason(),
							revokedStatus.getRevocationTime());
				} else if (certStatus instanceof UnknownStatus) {
				    System.out.println("El estado del certificado es desconocido.");
				} if (certStatus == CertificateStatus.GOOD) {
				    System.out.println("El certificado está en buen estado (Good).");
				}else {
				    System.out.println("No se encuentra el motivo de certificado no válido "+certStatus);
				}
			}
		} catch (OCSPException e) {
			throw new RuntimeException(e);
		} catch (CertificateEncodingException e) {
			throw new RuntimeException(e);
		} catch (OperatorCreationException e) {
			throw new RuntimeException(e);
		}
		
		return false;
		
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
	
	public static X509Certificate getCertificateX509(String certFilePath) {
		X509Certificate certificate = null;
		if (java.security.Security.getProvider("BC") == null) {
            java.security.Security.addProvider(new BouncyCastleProvider());
        }
		
		try {
            // Crea un flujo de entrada para leer el archivo .cer
            InputStream inStream = new FileInputStream(certFilePath);
            
            // Crea un objeto CertificateFactory para cargar el certificado
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");

            // Lee el certificado desde el flujo de entrada
            certificate = (X509Certificate) certFactory.generateCertificate(inStream);

            // Cierra el flujo de entrada
            inStream.close();

            // Ahora puedes trabajar con el objeto X509Certificate
            System.out.println("Sujeto del certificado: " + certificate.getSubjectDN());
            System.out.println("Emisor del certificado: " + certificate.getIssuerDN());
            System.out.println("Número de serie del certificado: " + certificate.getSerialNumber());

        } catch (Exception e) {
            e.printStackTrace();
        }
		return certificate;
	}
	
}
