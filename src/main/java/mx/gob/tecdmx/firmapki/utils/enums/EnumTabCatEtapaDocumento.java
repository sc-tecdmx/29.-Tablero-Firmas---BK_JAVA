package mx.gob.tecdmx.firmapki.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumTabCatEtapaDocumento {

	CREADO("Creado"),
	ENVIADO("Enviado"),
	EN_FIRMA("En Firma"),
	RECHAZADO("Rechazado"),
	TERMINADO("Terminado");
	
	private String opcion;
	private static Map<String, EnumTabCatEtapaDocumento> dictionary;

	static {
		  dictionary = new HashMap<String, EnumTabCatEtapaDocumento>();
		  for(EnumTabCatEtapaDocumento state : values()) {
		      dictionary.put(state.opcion, state);
		  }
		}

	EnumTabCatEtapaDocumento(String opcion){
		this.opcion = opcion;
	}

	public static EnumTabCatEtapaDocumento fromString(String fromValue) {
		EnumTabCatEtapaDocumento var = dictionary.get(fromValue);
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

	public static Map<String, EnumTabCatEtapaDocumento> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(Map<String, EnumTabCatEtapaDocumento> dictionary) {
		EnumTabCatEtapaDocumento.dictionary = dictionary;
	}
}