package mx.gob.tecdmx.firmapki.api.documento2;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
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
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;

@Service
public class ServiceFirma {

    @Autowired
    TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;


    public void firma(String hashDocumento, byte[] documento, byte[] signature, byte[] certificate, String filePath,
            DTOResponseUserInfo userInfo) {
        CertificateUtils utils = new CertificateUtils();
        try {
            byte[] signedPdf = firmarPdfAvanzado(documento, signature, certificate, userInfo);
            try {
                String encoded = Base64.getEncoder().encodeToString(signedPdf);
                Optional<TabDocumentosAdjuntos> doc = tabDocumentosAdjuntosRepository
                        .findByDocumentoHash(hashDocumento);
                if (doc.isPresent()) {
                    doc.get().setDocumentoBase64(encoded);
                    tabDocumentosAdjuntosRepository.save(doc.get());
                    System.out.println("se actualizó el base64 del doc firmado.");
                }

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

    public static byte[] addSha256ToPdf(byte[] pdfContent, String sha256Hash) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(pdfContent);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, baos);

        // Configurar fuente y color
        Font blueFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLUE);

        // Recorrer todas las páginas y agregar el hash SHA256
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            Rectangle pageSize = reader.getPageSize(i);
            float width = pageSize.getWidth();
            float height = pageSize.getHeight();

            PdfContentByte over = stamper.getOverContent(i);
            Phrase phrase = new Phrase(sha256Hash, blueFont);
            ColumnText.showTextAligned(over, Element.ALIGN_LEFT, phrase, width - 10, 200, 90);
        }

        stamper.close();
        reader.close();

        return baos.toByteArray();
    }

    public static byte[] addFirmaToPdf(byte[] pdfContent, String firmado, String noSerie, String fecha, int numFirma)
            throws IOException, DocumentException {
        PdfReader reader = new PdfReader(pdfContent);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, baos);

        // Configurar fuente y color
        Font blueFont = new Font(Font.FontFamily.HELVETICA, 5, Font.NORMAL, BaseColor.BLUE);

        // Recorrer todas las páginas y agregar el hash SHA256
        PdfContentByte over = stamper.getOverContent(reader.getNumberOfPages());
        Phrase phraseFirma = new Phrase(firmado, blueFont);
        Phrase phraseNoSerie = new Phrase(noSerie, blueFont);
        Phrase phraseFecha = new Phrase(fecha, blueFont);

        // A la izquierda el primer margen es de 9 y de ahí se incrementa en x
        // Abajo el primer margen es de 12 y de ahí se incrementa en y

        // Defino las constantes
        int level = 1;
        int nextLevelIncrease_Y = 28;
        int nextLevelIncrease_X = 170;

        int y1 = 22, y2 = 17, y3 = 12;
        int x = 9;

        if (numFirma <= 3) {
            level = 1;
        } else if (numFirma > 3 && numFirma <= 6) {
            level = 2;
            numFirma -= 3;
        } else if (numFirma > 6 && numFirma <= 9) {
            level = 3;
            numFirma -= 6;
        }

        nextLevelIncrease_Y *= (level - 1);
        x = x + (nextLevelIncrease_X * (numFirma - 1));
        y1 += nextLevelIncrease_Y;
        y2 += nextLevelIncrease_Y;
        y3 += nextLevelIncrease_Y;

        ColumnText.showTextAligned(over, Element.ALIGN_LEFT, phraseFirma, x, y1, 0);
        ColumnText.showTextAligned(over, Element.ALIGN_LEFT, phraseNoSerie, x, y2, 0);
        ColumnText.showTextAligned(over, Element.ALIGN_LEFT, phraseFecha, x, y3, 0);

        stamper.close();
        reader.close();

        return baos.toByteArray();
    }

    public static byte[] addFirmaRectanleToPdf(byte[] pdfContent, byte[] firmaExterna, int numFirma)
            throws IOException, DocumentException {

        PdfReader reader = new PdfReader(pdfContent);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamper = PdfStamper.createSignature(reader, baos, '\000', null, true);
        int lastPage = reader.getNumberOfPages();

        Date fechaFirma = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaFirma);

        PdfSignature signature = new PdfSignature(PdfName.ADOBE_PPKLITE, new PdfName("adbe.pkcs7.detached"));
        signature.setReason("Firma Digital");
        signature.setLocation("CIUDAD DE MEXICO");
        signature.setDate(new PdfDate(calendar));

        // stamper.close();
        PdfSignatureAppearance sap = stamper.getSignatureAppearance();
        sap.setSignDate(calendar);
        sap.setCryptoDictionary(signature);

        if (numFirma == 1) {
            float x = 9 * (72f / 96f);
            float y = 12 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        } else if (numFirma == 2) {
            float x = 236 * (72f / 96f);
            float y = 12 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        } else if (numFirma == 3) {
            float x = 462 * (72f / 96f);
            float y = 12 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        } else if (numFirma == 4) {
            float x = 9 * (72f / 96f);
            float y = 50 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        } else if (numFirma == 5) {
            float x = 236 * (72f / 96f);
            float y = 50 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        } else if (numFirma == 6) {
            float x = 462 * (72f / 96f);
            float y = 50 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        } else if (numFirma == 7) {
            float x = 9 * (72f / 96f);
            float y = 87 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        } else if (numFirma == 8) {
            float x = 236 * (72f / 96f);
            float y = 87 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        } else if (numFirma == 9) {
            float x = 462 * (72f / 96f);
            float y = 87 * (72f / 96f);
            float width = 215 * (72f / 96f);
            float height = 28 * (72f / 96f);

            sap.setVisibleSignature(new Rectangle(x, y, x + width, y + height), lastPage, null);
        }

        PdfContentByte layer2 = sap.getLayer(2);

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

        // Cerrar recursos
        sap.close(dic2);

        reader.close();
        baos.flush();
        baos.close();

        // Devolver el PDF firmado
        return baos.toByteArray();

    }

    public static byte[] firmarPdfAvanzado(byte[] dataDoc, byte[] firmaExterna, byte[] certificadoExterno,
            DTOResponseUserInfo userInfo) throws Exception {
        try {
            PdfReader reader = new PdfReader(dataDoc);
            AcroFields fields = reader.getAcroFields();
            List<String> signatureNames = fields.getSignatureNames();
            int signatureCount = signatureNames.size();
            if (signatureCount == 0) {
                dataDoc = addSha256ToPdf(dataDoc, "9b91c7fe46cb525edf8419b4a2ae0347dfdfdd6ea217eae0bd58b61f308b695b");
            }

            // Cargar el certificado externo
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            InputStream certStream = new ByteArrayInputStream(certificadoExterno);
            Certificate cert = factory.generateCertificate(certStream);

            InputStream inStream = new ByteArrayInputStream(certificadoExterno);
            CertUser certUser = new CertUser(inStream);

            Date fechaFirma = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaFirma);

            String firmado = "Firmado por: " + certUser.getNombreComun();
            String noSerie = "No. Serie: " + certUser.getSerialnumber();
            SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss Z");
            String fecha = "Fecha: " + dateformatter.format(fechaFirma);
            String firmaH = firmado + '\n' + noSerie + '\n' + fecha;

            // Se agregan los datos de firma
            dataDoc = addFirmaToPdf(dataDoc, firmado, noSerie, fecha, signatureCount + 1);
            // Se agregan los acrofields de firma
            dataDoc = addFirmaRectanleToPdf(dataDoc, firmaExterna, signatureCount + 1);

            return dataDoc;
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
