package mx.gob.tecdmx.firmaocsp.dto;


public class CertificateDTO {
	String nombreComun_CN;
    String pais_C;
    String estado_ST;
    String localidad_L;
    String postalCode;
    String street;
    String unidadOrganizativa_OU;
    String organizacion_O;
    String correoElectronico_E;
    String serialnumber;
    
    
	public CertificateDTO(String nombreComun_CN, String correoElectronico_E, String serialnumber) {
		super();
		this.nombreComun_CN = nombreComun_CN;
		this.correoElectronico_E = correoElectronico_E;
		this.serialnumber = serialnumber;
	}
	
	public CertificateDTO(String nombreComun_CN, String pais_C, String estado_ST,
			String localidad_L, String postalCode, String street,
			String unidadOrganizativa_OU, String organizacion_O,
			String correoElectronico_E) {
		super();
		this.nombreComun_CN = nombreComun_CN;
		this.pais_C = pais_C;
		this.estado_ST = estado_ST;
		this.localidad_L = localidad_L;
		this.postalCode = postalCode;
		this.street = street;
		this.unidadOrganizativa_OU = unidadOrganizativa_OU;
		this.organizacion_O = organizacion_O;
		this.correoElectronico_E = correoElectronico_E;
	}
	public String getNombreComun_CN() {
		return nombreComun_CN;
	}
	public void setNombreComun_CN(String nombreComun_CN) {
		this.nombreComun_CN = nombreComun_CN;
	}
	public String getPais_C() {
		return pais_C;
	}
	public void setPais_C(String pais_C) {
		this.pais_C = pais_C;
	}
	public String getEstado_ST() {
		return estado_ST;
	}
	public void setEstado_ST(String estado_ST) {
		this.estado_ST = estado_ST;
	}
	public String getLocalidad_L() {
		return localidad_L;
	}
	public void setLocalidad_L(String localidad_L) {
		this.localidad_L = localidad_L;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getUnidadOrganizativa_OU() {
		return unidadOrganizativa_OU;
	}
	public void setUnidadOrganizativa_OU(String unidadOrganizativa_OU) {
		this.unidadOrganizativa_OU = unidadOrganizativa_OU;
	}
	public String getOrganizacion_O() {
		return organizacion_O;
	}
	public void setOrganizacion_O(String organizacion_O) {
		this.organizacion_O = organizacion_O;
	}
	public String getCorreoElectronico_E() {
		return correoElectronico_E;
	}
	public void setCorreoElectronico_E(String correoElectronico_E) {
		this.correoElectronico_E = correoElectronico_E;
	}
	
	@Override
	public String toString() {
		return "CertificateDTO [nombreComun_CN=" + this.nombreComun_CN + ", pais_C=" + this.pais_C + ", estado_ST=" + this.estado_ST
				+ ", localidad_L=" + this.localidad_L + ", postalCode=" + this.postalCode + ", street=" + this.street
				+ ", unidadOrganizativa_OU=" + this.unidadOrganizativa_OU + ", organizacion_O=" + this.organizacion_O
				+ ", correoElectronico_E=" + this.correoElectronico_E + "]";
	}
	

	public String toStringSubject() {
		return "CertificateDTO [nombreComun_CN=" + this.nombreComun_CN + ", serialnumber=" + this.serialnumber
				+ ", correoElectronico_E=" + this.correoElectronico_E + "]";
	}
	
	
    
    

}
