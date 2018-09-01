package unicam.cs.pa.forzaquattro.printer;

import java.util.Observer;

/**
 * Printer è l'oggetto attraverso il quale il giocatore riceve aggiornamenti. In particolare permette la stampa dei messaggi
 * e la stampa della griglia di gioco. Il Printer è definito come classe astratta. Inoltre Printer implementa l'interfaccia {@code Observer}.
 * In questo modo il Printer viene notificato in caso di modifiche alla griglia di gioco e può automaticamente procedere alla sua stampa.
 *
 */
@SuppressWarnings("deprecation")
public abstract class Printer implements Observer { //mdf nuovo

	public abstract void printGrid();

	public abstract void print(String message);
	

}
