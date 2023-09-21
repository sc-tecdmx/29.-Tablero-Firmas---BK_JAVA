package mx.gob.tecdmx.firmaocsp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.pdf.security.CertificateUtil;

import mx.gob.tecdmx.firmaocsp.dto.CertificateDTO;
import mx.gob.tecdmx.firmaocsp.dto.OCSPResponseDTO;

public class CommonUtils {
	
	
	public static CertificateDTO getDataFromIssuerDN(X509Certificate certificate) {
		X500Name issuerDN = new X500Name(certificate.getIssuerX500Principal().getName());

		// Obtén campos específicos del DN
        RDN nombreComun_CN = issuerDN.getRDNs(BCStyle.CN)[0];
        RDN pais_C = issuerDN.getRDNs(BCStyle.C)[0];
        RDN estado_ST = issuerDN.getRDNs(BCStyle.ST)[0];
        RDN localidad_L = issuerDN.getRDNs(BCStyle.L)[0];
        RDN postalCode = issuerDN.getRDNs(BCStyle.POSTAL_CODE)[0];
        RDN street = issuerDN.getRDNs(BCStyle.STREET)[0];
        RDN unidadOrganizativa_OU = issuerDN.getRDNs(BCStyle.OU)[0];
        RDN organizacion_O = issuerDN.getRDNs(BCStyle.O)[0];
        RDN correoElectronico_E = issuerDN.getRDNs(BCStyle.E)[0];

        CertificateDTO emisor = new CertificateDTO(nombreComun_CN.getFirst().getValue().toString(), pais_C.getFirst().getValue().toString(), estado_ST.getFirst().getValue().toString(),
			localidad_L.getFirst().getValue().toString(), postalCode.getFirst().getValue().toString(), street.getFirst().getValue().toString(),
			unidadOrganizativa_OU.getFirst().getValue().toString(), organizacion_O.getFirst().getValue().toString(),
			correoElectronico_E.getFirst().getValue().toString());
        
        //System.out.println(emisor.toString());
        return emisor;
	}
	
	public static CertificateDTO getDataFromSubjectDN(X509Certificate certificate) {
		X500Name subjectDN = new X500Name(certificate.getSubjectX500Principal().getName());

		// Obtén campos específicos del DN
        RDN nombreComun_CN = subjectDN.getRDNs(BCStyle.CN)[0];
        RDN serialnumber = subjectDN.getRDNs(BCStyle.SERIALNUMBER)[0];
        RDN correoElectronico_E = subjectDN.getRDNs(BCStyle.E)[0];
        
        CertificateDTO asunto = new CertificateDTO(nombreComun_CN.getFirst().getValue().toString(), correoElectronico_E.getFirst().getValue().toString(), serialnumber.getFirst().getValue().toString());
        //System.out.println(asunto.toStringSubject());
        return asunto;
	}
	
	
	
	public static boolean compareTBS(X509Certificate issuerCert,
			X509Certificate x509Cert, OCSPResponseDTO ocspResponse) {
		
		String emisorCer = x509Cert.getIssuerDN().toString();
		String asuntoCA = issuerCert.getSubjectDN().toString();
		
        
        
        if(emisorCer.equals(asuntoCA)) {
        	System.out.println("emisorCer: " + emisorCer);
            System.out.println("asuntoCAo: " + asuntoCA);
            System.out.println("Número de serie del certificado: " + x509Cert.getSerialNumber());
            ocspResponse.setSerialNumber(x509Cert.getSerialNumber().toString());
        	return true;
        }
        
        
		
		//CertificateDTO emisor = getDataFromIssuerDN(x509Cert);
        //CertificateDTO asunto = getDataFromSubjectDN(x509Cert);
		
        //CertificateDTO emisorCA = getDataFromIssuerDN(issuerCert);
        //CertificateDTO asuntoCA = getDataFromSubjectDN(issuerCert);
		
        //System.out.println(emisor.toString());
        //System.out.println(asuntoCA.toString());
        
		return false;
	}
	
	public static X509Certificate getCertificateX509(InputStream inStream) {
		X509Certificate certificate = null;
		if (java.security.Security.getProvider("BC") == null) {
            java.security.Security.addProvider(new BouncyCastleProvider());
        }
		
		try {
            // Crea un flujo de entrada para leer el archivo .cer
            
            // Crea un objeto CertificateFactory para cargar el certificado
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");

            // Lee el certificado desde el flujo de entrada
            certificate = (X509Certificate) certFactory.generateCertificate(inStream);

            // Cierra el flujo de entrada
            inStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
		return certificate;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
		return certificate;
	}
	
	public static String getURL(X509Certificate x509Cert){
		String ocspUrl = null;
		try {
			ocspUrl = CertificateUtil.getOCSPURL(x509Cert);
		}catch(Exception e) {
			System.out.println(e);
		}
		return ocspUrl;
	}
	
	public static List<String> getStore(String rutaCarpeta) {
		// Especifica la ruta de la carpeta que deseas explorar
        

        // Crea un objeto File que representa la carpeta
        File carpeta = new File(rutaCarpeta);

        // Verifica si la carpeta existe y es un directorio
        if (carpeta.exists() && carpeta.isDirectory()) {
            // Lista para almacenar los nombres de archivos con extensión .cer
            List<String> archivosCer = new ArrayList<>();

            // Obtiene todos los archivos en la carpeta
            File[] archivos = carpeta.listFiles();

            // Itera a través de los archivos y verifica la extensión
            for (File archivo : archivos) {
                if (archivo.isFile() && (archivo.getName().toLowerCase().endsWith(".crt")||archivo.getName().toLowerCase().endsWith(".cer")||archivo.getName().toLowerCase().endsWith(".pem"))) {
                    archivosCer.add(archivo.getName());
                }
            }

            // Imprime los nombres de los archivos .cer encontrados
            //System.out.println("Archivos .crt en la carpeta:");
            for (String nombreArchivo : archivosCer) {
                //System.out.println(nombreArchivo);
            }
            return archivosCer;
        } else {
            System.err.println("La carpeta especificada no existe o no es un directorio.");
        }
        return null;
	}

}
