package mx.gob.tecdmx.firmapki.api.populate;

import java.io.InputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import mx.gob.tecdmx.firmapki.utils.CertificateUtils;

public class CertUser {
	InputStream inpStream;
	X509Certificate certificate;
	String serialnumber;
	String derBase64;
	String certSha256;
	String emisorSerial;
	String rfc;
	String curp;
	Date fechaRegistro;
	Date fechaRevocacion;
	String nombreComun;
    String correoElectronico;
    
    
	public CertUser(InputStream inpStream) {
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
		X500Name subjectDN = new X500Name(this.certificate.getSubjectX500Principal().getName());
		X500Name issuerDN = new X500Name(this.certificate.getIssuerX500Principal().getName());
		
		//Se obtiene la info requerida del issuer
		RDN serialnumberIssuer = issuerDN.getRDNs()[1];
		
		//Se obtienen campos específicos del DN
        RDN nombreComun_CN = subjectDN.getRDNs(BCStyle.CN)[0];
        RDN serialnumber = subjectDN.getRDNs(BCStyle.SERIALNUMBER)[0];
        RDN correoElectronico_E = subjectDN.getRDNs(BCStyle.E)[0];
        RDN rfc = subjectDN.getRDNs()[1];
 
        this.nombreComun = nombreComun_CN.getFirst().getValue().toString();
        this.curp = serialnumber.getFirst().getValue().toString();
        this.nombreComun = nombreComun_CN.getFirst().getValue().toString();
        this.correoElectronico = correoElectronico_E.getFirst().getValue().toString();
        this.serialnumber = this.certificate.getSerialNumber().toString();
        this.rfc = rfc.getFirst().getValue().toString();
        
        Date fhRegistroDate = this.certificate.getNotBefore();
        Date fhRevocacionDate = this.certificate.getNotAfter();  
        this.fechaRegistro = fhRegistroDate;
        this.fechaRevocacion = fhRevocacionDate;
        
        CertificateUtils certUtils = new CertificateUtils();
        String cerBase64 = convertCertToDer(this.certificate);
        this.derBase64 = cerBase64;
        this.certSha256 = certUtils.calcularSHA256(cerBase64);
        this.emisorSerial = serialnumberIssuer.getFirst().getValue().toString();
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

	public String getCertSha256() {
		return certSha256;
	}

	public void setCertSha256(String certSha256) {
		this.certSha256 = certSha256;
	}

	public String getEmisorSerial() {
		return emisorSerial;
	}

	public void setEmisorSerial(String emisorSerial) {
		this.emisorSerial = emisorSerial;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaRevocacion() {
		return fechaRevocacion;
	}

	public void setFechaRevocacion(Date fechaRevocacion) {
		this.fechaRevocacion = fechaRevocacion;
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
