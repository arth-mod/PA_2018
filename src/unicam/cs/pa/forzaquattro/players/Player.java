package unicam.cs.pa.forzaquattro.players;

import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.exceptions.FullColumnException;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.exceptions.WinException;
import unicam.cs.pa.forzaquattro.printer.Printer;

/**
 * Interfaccia {@code Player} da implementare per definire una nuova tipologia di {@code Player}
 * Per effettuare una mossa utilizzare il metodo {@code step()}
 *
 */

public interface Player { //mdf da togliere metodi commentati

	/**
	 * Il giocatore effettua una mossa di inserimento del proprio {@code Token}
	 * in una coonna della griglia di gioco.
	 * @return 
	 * 
	 * @throws IllegalTokenLocation se la colonna scelta è errata
	 * @throws WinException se l'inserimento comporta la vittoria
	 * @throws FullColumnException se la colonna scelta è piena
	 */
	public int step() throws IllegalTokenLocation;
	
	public Color getColor();

	public void receiveMessage(String message);

	public Printer getPrinter(); 

}
