package unicam.cs.pa.forzaquattro.core;

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
