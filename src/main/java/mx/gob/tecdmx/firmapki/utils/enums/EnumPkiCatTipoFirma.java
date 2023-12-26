package mx.gob.tecdmx.firmapki.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumPkiCatTipoFirma {

	SIMPLE("Simple"),
	MULTIPLE("Múltiple");
	
	private String opcion;
	private static Map<String, EnumPkiCatTipoFirma> dictionary;

	static {
		  dictionary = new HashMap<String, EnumPkiCatTipoFirma>();
		  for(EnumPkiCatTipoFirma state : values()) {
		      dictionary.put(state.opcion, state);
		  }
		}

	EnumPkiCatTipoFirma(String opcion){
		this.opcion = opcion;
	}

	public static EnumPkiCatTipoFirma fromString(String fromValue) {
		EnumPkiCatTipoFirma var = dictionary.get(fromValue);
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

	public static Map<String, EnumPkiCatTipoFirma> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(Map<String, EnumPkiCatTipoFirma> dictionary) {
		EnumPkiCatTipoFirma.dictionary = dictionary;
	}
}