package mx.gob.tecdmx.firmapki.api.populate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.pdf.security.CertificateUtil;

import mx.gob.tecdmx.firmapki.utils.CertificateUtils;

public class CertCA {
	InputStream inpStream;
	X509Certificate certificate;
	
	String serialnumber;
	String derBase64;
	String nombreComunAutoridad;
	String url;
	
	DTOIssuerSubjectData subjectData;
	DTOIssuerSubjectData issuerData;
	
	public CertCA(String derBase64) {
		super();
		this.certificate =  convertDerToCert(derBase64);
		filllData();
	}

	public CertCA(InputStream inpStream) {
		super();
		this.inpStream = inpStream;
		setCertificateX509();
		filllData();
	}

	public void setCertificateX509() {
		X509Certificate certificate = null;
		if (java.security.Security.getProvider("BC") == null) {
            java.security.Security.addProvider(new BouncyCastleProvider());
        }
		
		try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");
            certificate = (X509Certificate) certFactory.generateCertificate(this.inpStream);
            this.inpStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
		this.certificate = certificate;
	}
	
	
	
	public void filllData() {
		CertificateUtils certUtils = new CertificateUtils();
		
		X500Name subjectDN = new X500Name(this.certificate.getSubjectX500Principal().getName());
		X500Name issuerDN = new X500Name(this.certificate.getIssuerX500Principal().getName());
		
		//Se obtienen campos específicos del DN
        RDN nombreComun_CN = subjectDN.getRDNs(BCStyle.CN)[0];
        
        
        this.serialnumber = this.certificate.getSerialNumber().toString();        
        this.subjectData = certUtils.extractDataX500Name(subjectDN);

        this.issuerData = certUtils.extractDataX500Name(issuerDN);;
        
        String cerBase64 = convertCertToDer(this.certificate);
        this.derBase64 = cerBase64;
        this.nombreComunAutoridad = nombreComun_CN.getFirst().getValue().toString();
        this.url = getURL(this.certificate);
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
	
	public X509Certificate convertDerToCert(String base64String) {
	    try {
	        // Decodificar la cadena Base64 a un arreglo de bytes
	        byte[] derDecoded = Base64.getDecoder().decode(base64String);

	        // Usar una fábrica de certificados para generar el certificado X509
	        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
	        X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(derDecoded));
	        
	        return cert;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
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

	public InputStream getInpStream() {
		return inpStream;
	}

	public void setInpStream(InputStream inpStream) {
		this.inpStream = inpStream;
	}

	public X509Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getDerBase64() {
		return derBase64;
	}

	public void setDerBase64(String derBase64) {
		this.derBase64 = derBase64;
	}

	public String getNombreComunAutoridad() {
		return nombreComunAutoridad;
	}

	public void setNombreComunAutoridad(String nombreComunAutoridad) {
		this.nombreComunAutoridad = nombreComunAutoridad;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DTOIssuerSubjectData getSubjectData() {
		return subjectData;
	}

	public void setSubjectData(DTOIssuerSubjectData subjectData) {
		this.subjectData = subjectData;
	}

	public DTOIssuerSubjectData getIssuerData() {
		return issuerData;
	}

	public void setIssuerData(DTOIssuerSubjectData issuerData) {
		this.issuerData = issuerData;
	}
	
	
}
