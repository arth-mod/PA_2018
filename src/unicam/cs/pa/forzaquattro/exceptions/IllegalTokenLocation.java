package unicam.cs.pa.forzaquattro.exceptions;

/**
 * Eccezione da lanciare quando si tenta di inserire un
 * {@code Token} in una posizione non ammissibile
 *
 */
public class IllegalTokenLocation extends Exception {

	public IllegalTokenLocation () {
		super("Errore locale");
	}
	
	public IllegalTokenLocation(String m) {
		super(m);
	}
	
	private static final long serialVersionUID = 1L;
}
