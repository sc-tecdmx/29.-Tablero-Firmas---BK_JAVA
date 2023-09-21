package mx.gob.tecdmx.firmaocsp.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.List;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.TimeStampTokenInfo;

import mx.gob.tecdmx.firmaocsp.utils.CommonUtils;
import mx.gob.tecdmx.firmaocsp.utils.OcspTimeoutException;
import mx.gob.tecdmx.firmaocsp.utils.OcspValidationException;



public class ValidCertIntermedioService {

	

//	public static void main(String[] args) {
//		CommonUtils comU = new CommonUtils();
//		VerificarOCSPService ocpService = new VerificarOCSPService();
//		String rutaCarpeta = "./allCertificadosBanxico_pem";
//
//		String certFilePath = "./test_fiel/ieag9707064z9.cer";
//		X509Certificate certificate = comU.getCertificateX509(certFilePath);
//
//		// Get URL
//		String url = comU.getURL(certificate);
//
//		List<String> store = comU.getStore(rutaCarpeta);
//		for (String ca : store) {
//			String certCAFilePath = rutaCarpeta + "/" + ca;
//			X509Certificate certificateIssuer = comU.getCertificateX509(certCAFilePath);
//			boolean isIssuer = comU.compareTBS(certificateIssuer, certificate);
//			if (isIssuer) {
//				System.out.println("Se encontró el certificado de la autoridad certificadora: " + ca);
//				if (url != null) {// Se valida por OCP
//
////					try {
////						ocpService.check(certificateIssuer, certificate, url);
////						System.out.println("Se validó por OCSP a la URL: " + url);
////					} catch (OcspValidationException | OcspTimeoutException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//				} else {// Se valida por RCL
//					url = "https://cfdi.sat.gob.mx/edofiel";
//					String certRaizFilePath = "./allCertificadosBanxico_pem/ocsp.ac5_sat.pem";
//
//					// Ruta al certificado de la entidad emisora (CA)
//					String issuerCertPath = certCAFilePath;
//
//					// Ruta al certificado a verificar
//					String certPath = certCAFilePath;
//
//					// Ruta al certificado OCSP de la autoridad de validación (VA)
//					String vaCertPath = certRaizFilePath;
//
//					// URL del servidor OCSP
//					String ocspServerURL = url;
//
//					// Establecer el encabezado "Host"
//					String hostHeader = "cfdi.sat.gob.mx";
//					// Realizar la verificación OCSP
//					
//					X509Certificate certificateVa = comU.getCertificateX509(vaCertPath);
//
//					try {
//						ocpService.check(certificateVa, certificateIssuer, certificate, url);
//						System.out.println("Se validó por OCSP a la URL: "+url);
//					} catch (OcspValidationException | OcspTimeoutException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//
//			// System.out.println(ca);
//		}
//
//	}

}
