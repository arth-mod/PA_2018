package unicam.cs.pa.forzaquattro.core;

/**
 * Elenca i possibili tipi di {@code Grid}. DEFAULT (6X7) o CUSTOM
 *
 */
public enum GridType {
	DEFAULT,
	CUSTOM;
	
	public static GridType fromInt(int i) {
		switch(i) {
		case 0: return DEFAULT;
		case 1: return CUSTOM;
		}
		return DEFAULT;
	}

}
