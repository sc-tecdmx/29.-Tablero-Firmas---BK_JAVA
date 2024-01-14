package mx.gob.tecdmx.firmapki.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumTipoCerificado {

	CERT_USUARIO("CertUsuario"),
	CERT_AC("CertAC"),
	CERT_INTERMEDIO("INTERMEDIO"),
	CERT_OCSP("OCSP"),
	ID_COMODIN("11111");
	
	private String opcion;
	private static Map<String, EnumTipoCerificado> dictionary;

	static {
		  dictionary = new HashMap<String, EnumTipoCerificado>();
		  for(EnumTipoCerificado state : values()) {
		      dictionary.put(state.opcion, state);
		  }
		}

	EnumTipoCerificado(String opcion){
		this.opcion = opcion;
	}

	public static EnumTipoCerificado fromString(String fromValue) {
		EnumTipoCerificado var = dictionary.get(fromValue);
		  if(var == null) {
		      return null;
		  }
		  return var;
		}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String constante) {
		this.opcion = constante;
	}

	public static Map<String, EnumTipoCerificado> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(Map<String, EnumTipoCerificado> dictionary) {
		EnumTipoCerificado.dictionary = dictionary;
	}
}