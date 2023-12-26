package mx.gob.tecdmx.firmapki.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumPkiCatFirmaAplicada {

	FIRMADO("Firmado"),
	RECHAZADO("Rechazado");
	
	private String opcion;
	private static Map<String, EnumPkiCatFirmaAplicada> dictionary;

	static {
		  dictionary = new HashMap<String, EnumPkiCatFirmaAplicada>();
		  for(EnumPkiCatFirmaAplicada state : values()) {
		      dictionary.put(state.opcion, state);
		  }
		}

	EnumPkiCatFirmaAplicada(String opcion){
		this.opcion = opcion;
	}

	public static EnumPkiCatFirmaAplicada fromString(String fromValue) {
		EnumPkiCatFirmaAplicada var = dictionary.get(fromValue);
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

	public static Map<String, EnumPkiCatFirmaAplicada> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(Map<String, EnumPkiCatFirmaAplicada> dictionary) {
		EnumPkiCatFirmaAplicada.dictionary = dictionary;
	}
}