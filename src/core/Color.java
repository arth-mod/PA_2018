package core;

public enum Color {

	RED,
	YELLOW;
	
	@Override
	public String toString() {
		switch(this) {
		case YELLOW: return "Y";
		case RED: return "R";
		}
		return "";
	}
}
