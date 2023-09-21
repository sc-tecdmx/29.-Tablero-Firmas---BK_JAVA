package mx.gob.tecdmx.firmaocsp.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.gob.tecdmx.firmaocsp.dto.OCSPResponseDTO;
import mx.gob.tecdmx.firmaocsp.dto.PayloadOCSP;
import mx.gob.tecdmx.firmaocsp.service.VerificarOCSPService;
import mx.gob.tecdmx.firmaocsp.utils.CommonUtils;
import mx.gob.tecdmx.firmaocsp.utils.OcspTimeoutException;
import mx.gob.tecdmx.firmaocsp.utils.OcspValidationException;

@RestController
@RequestMapping(path = "/api/oscpi-verification")
public class VerificationOCSPController {

	@PostMapping("/")
    public OCSPResponseDTO verifyOcspi(@RequestBody PayloadOCSP payload) {
		OCSPResponseDTO ocspResponse = new OCSPResponseDTO();
        try {
            InputStream inStream = new ByteArrayInputStream(payload.getCertificate());
            
            CommonUtils comU = new CommonUtils();
    		VerificarOCSPService ocpService = new VerificarOCSPService();
    		String rutaCarpeta = "./allCertificadosBanxico_pem";

    		X509Certificate certificate = comU.getCertificateX509(inStream);
    		
    		//Get URL
    		String url = comU.getURL(certificate);

    		List<String> store = comU.getStore(rutaCarpeta);
    		for(String ca : store) {
     			String certCAFilePath = rutaCarpeta+"/"+ca;
    			X509Certificate certificateIssuer = comU.getCertificateX509(certCAFilePath);
    			boolean isIssuer = comU.compareTBS(certificateIssuer, certificate, ocspResponse);
    			if(isIssuer) {
    				
    				System.out.println("Se encontró el certificado de la autoridad certificadora: "+ca);
    				if(url!=null) {//Se valida por OCP
    					String certRaizFilePath = "./allCertificadosBanxico_pem/ACR_PJF.pem";
    					ocspResponse.setCertificate(payload.getFileName());			
    					ocspResponse.setCaCert("ACR_PJF.pem");
    					ocspResponse.setIssuerCert(ca);
    					ocspResponse.setUrlOCSP(url);
    					String vaCertPath = certRaizFilePath; // Ruta al certificado OCSP de la autoridad de validación (VA)
    					X509Certificate certificateVa = comU.getCertificateX509(vaCertPath);
    					try {
    						ocpService.check(certificateVa, certificateIssuer, certificate, url, ocspResponse);
    						System.out.println("Se validó por OCSP a la URL: "+url);
    						return ocspResponse;
    					} catch (OcspValidationException | OcspTimeoutException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else {//Se valida por RCL
    					url = "https://cfdi.sat.gob.mx/edofiel";
    					String certRaizFilePath = "./allCertificadosBanxico_pem/ocsp.ac5_sat.pem";
    					String vaCertPath = certRaizFilePath; // Ruta al certificado OCSP de la autoridad de validación (VA)
    					ocspResponse.setCertificate(payload.getFileName()); 
    					ocspResponse.setCaCert("ocsp.ac5_sat.pem");
    					ocspResponse.setIssuerCert(ca);
    					ocspResponse.setUrlOCSP(url);
    					// Realizar la verificación OCSP
    					X509Certificate certificateVa = comU.getCertificateX509(vaCertPath);
    					try {
    						ocpService.check(certificateVa, certificateIssuer, certificate, url, ocspResponse);
    						System.out.println("Se validó por OCSP a la URL: "+url);
    						return ocspResponse;
    					} catch (OcspValidationException | OcspTimeoutException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    			}
    		}

            // Devuelve una respuesta exitosa
        } catch (Exception e) {
            // Manejo de errores si ocurre alguna excepción
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
        System.out.println(ocspResponse.toString());
        return ocspResponse;
    }
	
	@PostMapping("/upload")
    public OCSPResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
		OCSPResponseDTO ocspResponse = new OCSPResponseDTO();
        try {
            // Lee el contenido del archivo y almacénalo en memoria
        	byte[] fileBytes = file.getBytes();
            InputStream inStream = new ByteArrayInputStream(fileBytes);
            
            CommonUtils comU = new CommonUtils();
    		VerificarOCSPService ocpService = new VerificarOCSPService();
    		String rutaCarpeta = "./allCertificadosBanxico_pem";

    		X509Certificate certificate = comU.getCertificateX509(inStream);
    		
    		//Get URL
    		String url = comU.getURL(certificate);

    		List<String> store = comU.getStore(rutaCarpeta);
    		for(String ca : store) {
     			String certCAFilePath = rutaCarpeta+"/"+ca;
    			X509Certificate certificateIssuer = comU.getCertificateX509(certCAFilePath);
    			boolean isIssuer = comU.compareTBS(certificateIssuer, certificate, ocspResponse);
    			if(isIssuer) {
    				
    				System.out.println("Se encontró el certificado de la autoridad certificadora: "+ca);
    				if(url!=null) {//Se valida por OCP
    					String certRaizFilePath = "./allCertificadosBanxico_pem/ACR_PJF.pem";
    					ocspResponse.setCertificate(file.getOriginalFilename());   					
    					ocspResponse.setCaCert("ACR_PJF.pem");
    					ocspResponse.setIssuerCert(ca);
    					ocspResponse.setUrlOCSP(url);
    					String vaCertPath = certRaizFilePath; // Ruta al certificado OCSP de la autoridad de validación (VA)
    					X509Certificate certificateVa = comU.getCertificateX509(vaCertPath);
    					try {
    						ocpService.check(certificateVa, certificateIssuer, certificate, url, ocspResponse);
    						System.out.println("Se validó por OCSP a la URL: "+url);
    						return ocspResponse;
    					} catch (OcspValidationException | OcspTimeoutException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else {//Se valida por RCL
    					url = "https://cfdi.sat.gob.mx/edofiel";
    					String certRaizFilePath = "./allCertificadosBanxico_pem/ocsp.ac5_sat.pem";
    					String vaCertPath = certRaizFilePath; // Ruta al certificado OCSP de la autoridad de validación (VA)
    					System.out.println(file.getOriginalFilename());
    					ocspResponse.setCertificate(file.getOriginalFilename()); 
    					ocspResponse.setCaCert("ocsp.ac5_sat.pem");
    					ocspResponse.setIssuerCert(ca);
    					ocspResponse.setUrlOCSP(url);
    					// Realizar la verificación OCSP
    					X509Certificate certificateVa = comU.getCertificateX509(vaCertPath);
    					try {
    						ocpService.check(certificateVa, certificateIssuer, certificate, url, ocspResponse);
    						System.out.println("Se validó por OCSP a la URL: "+url);
    						return ocspResponse;
    					} catch (OcspValidationException | OcspTimeoutException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    			}
    		}

            // Devuelve una respuesta exitosa
        } catch (Exception e) {
            // Manejo de errores si ocurre alguna excepción
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
        System.out.println(ocspResponse.toString());
        return ocspResponse;
    }
}
