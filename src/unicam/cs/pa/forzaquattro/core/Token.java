package unicam.cs.pa.forzaquattro.core;

/**
 * Rappresenta il gettone da inserire nella {@code Grid}.
 * Ogni {@code Token} è caratterizzato da un colore.
 * 
 *
 */
public class Token   {

	private final Color color;

	
	/**
	 * Costruttore
	 * @param color il colore da assegnare al {@code Token}
	 */
	public Token(Color color) {
		this.color = color;
	
	}

	/**
	 * Accede al campo {@code color} del {@code Token}
	 * @return colore
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Stampa un {@code Token} in base al suo colore.
	 */
	@Override
	public String toString() {
		return this.color.toString();
	}

	
	/**
	 * Stabilisce se due {@code Token} sono uguali, cioè se hanno lo stesso colore
	 * @param token da confrontare
	 * @return {@code true} se hanno stesso colore, {@code false} altrimenti
	 */
	public boolean equals(Token token) {
		if(token==null)
			return false;
		else {
			return(token.getColor()==this.getColor());
		}
	}
	
}
