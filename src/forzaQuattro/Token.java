package forzaQuattro;

public class Token {

	private final Color color;

	
	//costruttore
	public Token(Color color) {
		this.color = color;
	
	}


	public Color getColor() {
		return this.color;
	}
	
	public String toString() {
		return this.color.toString();
	}
	
	
	
	/*
	 * TODO
	 * aggiungere coordinate ??
	 */
}
