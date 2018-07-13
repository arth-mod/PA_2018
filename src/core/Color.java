package core;

/**
 * Colore di un {@code Token}.
 *
 */
public enum Color {

	RED,
	YELLOW;
	
	/**
	 * Override del metodo {@code toString()}
	 */
	@Override
	public String toString() {
		switch(this) {
		case YELLOW: return "Y";
		case RED: return "R";
		}
		return "";
	}
}
