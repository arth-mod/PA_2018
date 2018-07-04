package forzaQuattro;

public enum CellStatus {

	EMPTY,
	FULL;

	@Override
	public String toString() {
		switch ( this ) {
		case EMPTY: return " ";
		case FULL: return "O";
		}
		return "";
	}
	
	
}
