package core;

public class Token   {

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

	
public boolean equals(Token token) {
	if(token==null)
		return false;
	else {
		return(token.getColor()==this.getColor());
	}
	
	
}
	
	/*
	 * TODO
	 * aggiungere coordinate ??
	 */
}
