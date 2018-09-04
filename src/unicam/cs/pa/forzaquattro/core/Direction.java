package unicam.cs.pa.forzaquattro.core;

/**
 * Direzioni corrispondenti ai contatori della cella 
 *
 */
public enum Direction { 

	VERT(0),
	HOR(1),
	DIAG(2),
	ANTIDIAG(3);
	
	int value;
	
	/**
	 * Converte intero in {@code Direction}. I valori associati sono {@code 0-VERT, 1-HOR, 2-DIAG, 3-ANTIDIAG}
	 * @param i
	 * @return
	 */
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
