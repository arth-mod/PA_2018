package exceptions;

public class IllegalTokenLocation extends Exception {

	public IllegalTokenLocation () {
		super("Errore locale");
	}
	
	public IllegalTokenLocation(String m) {
		super(m);
	}
	
	private static final long serialVersionUID = 1L;
}
