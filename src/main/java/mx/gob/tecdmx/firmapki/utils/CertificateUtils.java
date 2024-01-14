package mx.gob.tecdmx.firmapki.utils;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;

import com.google.gson.Gson;

import mx.gob.tecdmx.firmapki.api.populate.DTOIssuerSubjectData;
import mx.gob.tecdmx.firmapki.entity.pki.PkiUsuariosCert;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509AcAutorizadas;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Registrados;



public class CertificateUtils {
	
	public String calcularSHA256(String texto) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] digest = md.digest(texto.getBytes(StandardCharsets.UTF_8));
	        return DatatypeConverter.printHexBinary(digest).toLowerCase();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public String objectToJson(PkiUsuariosCert objeto) {
		Gson gson = new Gson();
		String json = gson.toJson(objeto);
		return json;
	}
	
	public String objectToJson(PkiX509AcAutorizadas objeto) {
		Gson gson = new Gson();
		String json = gson.toJson(objeto);
		return json;
	}
	
	public String objectToJson(PkiX509Registrados objeto) {
		Gson gson = new Gson();
		String json = gson.toJson(objeto);
		return json;
	}

	public String objectToJson(DTOResponse objeto) {
		Gson gson = new Gson();
		String json = gson.toJson(objeto);
		return json;
	}
	
	public String[] extraerNombre(String nombreCompleto) {
        // Dividir la cadena por espacios
        String[] partes = nombreCompleto.trim().split("\\s+");

        String nombre = partes.length > 0 ? partes[0] : "";
        String apellidoPaterno = partes.length > 1 ? partes[1] : "";
        String apellidoMaterno = partes.length > 2 ? partes[2] : "";

        return new String[]{nombre, apellidoPaterno, apellidoMaterno};
    }
	
	public String formatDate(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        String fechaUTC = formatter.format(fecha);
        fechaUTC = fechaUTC.replaceAll(":", "");
        return fechaUTC;
	}
	
	public static String generateSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convertir el hash en una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static String hexToBase64(String hexString) {
        hexString = hexString.replaceAll("\\s", ""); // Eliminar espacios
        byte[] bytes = new byte[hexString.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            int index = i * 2;
            int value = Integer.parseInt(hexString.substring(index, index + 2), 16);
            bytes[i] = (byte) value;
        }

        return Base64.getEncoder().encodeToString(bytes);
    }

	public static String base64ToHex(String base64String) {
	    byte[] bytes = Base64.getDecoder().decode(base64String);
	    StringBuilder hexString = new StringBuilder();
	    int counter = 0;

	    for (byte b : bytes) {
	        String hex = Integer.toHexString(0xff & b);
	        if (hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	        counter++;

	        if (counter % 1 == 0 && counter < bytes.length) {
	            hexString.append(" ");
	        }
	    }

	    return hexString.toString();
	}
	
	public void storeBase64ToFile(String pdfBase64, String path) {
		try {
			byte[] data = Base64.getDecoder().decode(pdfBase64);
            Files.write(Paths.get(path), data, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void writePdfFile(byte[] signedPdf, String outputFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            fos.write(signedPdf);
            fos.flush(); // Asegúrate de que todos los datos se escriban en el archivo.
        }
    }
	
	public InputStream stringBase64ToInputStream(String base64String) {

        // Convierte la cadena Base64 a bytes
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);

        // Crea un InputStream a partir de los bytes decodificados
        try (InputStream inputStream = new ByteArrayInputStream(decodedBytes)) {
            // Ahora puedes usar el inputStream según tus necesidades
            // Por ejemplo, leer bytes del inputStream
            int data;
            while ((data = inputStream.read()) != -1) {
                //System.out.print((char) data);
            }
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public String convertCertToDer(X509Certificate certificado) {
		byte[] derEncoded;
		try {
			derEncoded = certificado.getEncoded();
			String base64String = Base64.getEncoder().encodeToString(derEncoded);
			return base64String;
		} catch (CertificateEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static X509Certificate convertBase64ToCert(String base64String) {
		// Decodifica la cadena Base64 a bytes
        byte[] derEncoded = Base64.getDecoder().decode(base64String);

        // Obtiene la fábrica de certificados X.509
        CertificateFactory certificateFactory;
		try {
			certificateFactory = CertificateFactory.getInstance("X.509");
			// Crea un InputStream a partir de los bytes decodificados
	        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(derEncoded)) {
	            // Lee el certificado desde el InputStream
	            return (X509Certificate) certificateFactory.generateCertificate(inputStream);
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (java.security.cert.CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
	
	public static InputStream convertCertToInputStream(X509Certificate certificado) {
        try {
            // Obtiene la representación en bytes del certificado
            byte[] derEncoded = certificado.getEncoded();

            // Crea un InputStream a partir de los bytes
            return new ByteArrayInputStream(derEncoded);
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public DTOIssuerSubjectData extractDataX500Name(X500Name xname) {
		DTOIssuerSubjectData subjectDataDTO = new DTOIssuerSubjectData();
		
        if(xname.getRDNs(BCStyle.UnstructuredName).length>=1) {
        	subjectDataDTO.setResponsable((xname.getRDNs(BCStyle.UnstructuredName)[0].getFirst().getValue().toString()).replace("responsable:", "").trim());
        }
        if(xname.getRDNs(BCStyle.UNIQUE_IDENTIFIER).length>=1) {
        	subjectDataDTO.setSerialnumberIssuer(xname.getRDNs(BCStyle.UNIQUE_IDENTIFIER)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.L).length>=1) {
        	subjectDataDTO.setIssuer_l(xname.getRDNs(BCStyle.L)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.ST).length>=1) {
        	subjectDataDTO.setIssuer_s(xname.getRDNs(BCStyle.ST)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.C).length>=1) {
        	subjectDataDTO.setIssuer_c(xname.getRDNs(BCStyle.C)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.POSTAL_CODE).length>=1) {
        	subjectDataDTO.setIssuer_postalcode(xname.getRDNs(BCStyle.POSTAL_CODE)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.STREET).length>=1) {
        	subjectDataDTO.setIssuer_street(xname.getRDNs(BCStyle.STREET)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.EmailAddress).length>=1) {
        	subjectDataDTO.setIssuer_e(xname.getRDNs(BCStyle.EmailAddress)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.OU).length>=1) {
        	subjectDataDTO.setIssuer_ou(xname.getRDNs(BCStyle.OU)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.O).length>=1) {
        	subjectDataDTO.setIssuer_o(xname.getRDNs(BCStyle.O)[0].getFirst().getValue().toString());
        }
        if(xname.getRDNs(BCStyle.CN).length>=1) {
        	subjectDataDTO.setIssuer_cn(xname.getRDNs(BCStyle.CN)[0].getFirst().getValue().toString());
        }
        
        return subjectDataDTO;
	}

}
