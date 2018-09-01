package unicam.cs.pa.forzaquattro.printer;

import java.util.Observable;

/**
 * Oggetto Printer istanziabile da utilizzare quando non si necessita di output. Contiene infatti tutti i metodi per
 * la stampa vuoti.
 *
 */
@SuppressWarnings("deprecation")
public class FakePrinter extends Printer{ //mdf nuovo

	@Override
	public void printGrid() {
		
	}

	@Override
	public void print(String message) {
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
