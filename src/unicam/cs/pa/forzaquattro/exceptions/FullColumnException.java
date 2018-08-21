package unicam.cs.pa.forzaquattro.exceptions;

/**
 * Eccezione da lanciare quando un giocatore tenta di inserire un {@code Token} in una colonna già piena
 * @author Joyod
 *
 */
public class FullColumnException extends Exception {

	public FullColumnException(String m) {
		super(m);
	}
	
	private static final long serialVersionUID = 1L;
}
