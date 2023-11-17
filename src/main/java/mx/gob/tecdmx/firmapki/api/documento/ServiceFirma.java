package mx.gob.tecdmx.firmapki.api.documento;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
//import com.itextpdf.text.pdf.PdfPKCS7;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignature;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.populate.CertUser;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;

@Service
public class ServiceFirma {
	
	public void firma(byte[] documento, byte[] signature, byte[] certificate, String filePath, DTOResponseUserInfo userInfo) {
		CertificateUtils utils = new CertificateUtils();
		try {
			byte[] signedPdf = firmarPdfAvanzado(documento, signature, certificate, userInfo);
			try {
				utils.writePdfFile(signedPdf, filePath);
		        System.out.println("PDF firmado guardado con éxito.");
		    } catch (IOException e) {
		        System.err.println("Error al guardar el PDF firmado:");
		        e.printStackTrace();
		    }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Implementa esta lógica
	}
	
	public static byte[] firmarPdfAvanzado(byte[] dataDoc, byte[] firmaExterna, byte[] certificadoExterno, DTOResponseUserInfo userInfo) throws Exception {
	    try {
	        // Cargar el certificado externo
	        CertificateFactory factory = CertificateFactory.getInstance("X.509");
	        InputStream certStream = new ByteArrayInputStream(certificadoExterno);
	        Certificate cert = factory.generateCertificate(certStream);
	        
	        InputStream inStream = new ByteArrayInputStream(certificadoExterno);
			CertUser certUser = new CertUser(inStream);

	        // Cargar el documento PDF
	        PdfReader reader = new PdfReader(dataDoc);
	        ByteArrayOutputStream nuevoDocumento = new ByteArrayOutputStream();
	        PdfStamper stamper = PdfStamper.createSignature(reader, nuevoDocumento, '\000', null, true);
	        PdfSignatureAppearance sap = stamper.getSignatureAppearance();

	        // Configurar la apariencia y propiedades de la firma
	        // ... (tu código actual)
	        PdfSignature signature = new PdfSignature(PdfName.ADOBE_PPKLITE, new PdfName("adbe.pkcs7.detached"));
            signature.setReason("Firma Digital");
            signature.setLocation("CIUDAD DE MEXICO");
            
            Date fechaFirma=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaFirma);
            signature.setDate(new PdfDate(calendar));
            
            sap.setSignDate(calendar);
            sap.setCryptoDictionary(signature);
            
            String firmado = "Firmado por: "+certUser.getNombreComun();
            String noSerie = "No. Serie: " + certUser.getSerialnumber();
            SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss Z");
            String fecha = "Fecha: " + dateformatter.format(fechaFirma);
            String firmaH = firmado + '\n' + noSerie + '\n' + fecha;
            sap.setLayer2Text(firmaH);
            sap.setVisibleSignature(new Rectangle(100, 100, 350, 200), 1, null);

	        // Preparar para cerrar la firma
	        int contentEstimated = firmaExterna.length + 2;
	        HashMap<PdfName, Integer> exc = new HashMap<>();
	        exc.put(PdfName.CONTENTS, contentEstimated * 2 + 2);
	        sap.preClose(exc);

	        // Cerrar la apariencia con la firma externa
	        byte[] paddedSig = new byte[contentEstimated];
	        System.arraycopy(firmaExterna, 0, paddedSig, 0, firmaExterna.length);

	        PdfDictionary dic2 = new PdfDictionary();
	        dic2.put(PdfName.CONTENTS, new PdfString(paddedSig).setHexWriting(true));
	        sap.close(dic2);

	        // Cerrar recursos
	        reader.close();
	        nuevoDocumento.flush();
	        nuevoDocumento.close();

	        // Devolver el PDF firmado
	        return nuevoDocumento.toByteArray();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	 
	 public static void saveStringAsFile(String content, String filePath) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	            writer.write(content);
	            System.out.println("Archivo guardado con éxito en: " + filePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
