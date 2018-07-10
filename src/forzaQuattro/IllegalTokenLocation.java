package forzaQuattro;

public class IllegalTokenLocation extends Exception {

	public IllegalTokenLocation () {
		super("Errore locale");
	}
	
	public IllegalTokenLocation(String m) {
		super(m);
	}
	
}
