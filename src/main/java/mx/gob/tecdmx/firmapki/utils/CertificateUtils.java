package mx.gob.tecdmx.firmapki.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;

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
	
	public void storeBase64ToFile(byte[] decodedBytes, String path) {
		try {
            Files.write(Paths.get(path), decodedBytes, StandardOpenOption.CREATE);
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

}
