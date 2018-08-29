package unicam.cs.pa.forzaquattro.core;

public enum Direction {

	VERT(0),
	HOR(1),
	DIAG(2),
	ANTIDIAG(3);
	
	int value;
	
	public static Direction fromInt(int i) {
		switch(i) {
		case 0: return VERT;
		case 1: return HOR;
		case 2: return DIAG;
		case 3: return ANTIDIAG;
		}
		return null;
	}
	
	private Direction(int i) {
		value = i;
	}
}
