package unicam.cs.pa.forzaquattro.players;

import java.io.PrintStream;

import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.exceptions.FullColumnException;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.exceptions.WinException;

/**
 * Interfaccia {@code Player} da implementare per definire una nuova tipologia di {@code Player}
 * Per effettuare una mossa utilizzare il metodo {@code step()}
 *
 */
public interface Player {

	/**
	 * Il gicatore effettua una mossa di inserimento del proprio {@code Token}
	 * in una coonna della griglia di gioco.
	 * 
	 * @throws IllegalTokenLocation se la colonna scelta � errata
	 * @throws WinException se l'inserimento comporta la vittoria
	 * @throws FullColumnException se la colonna scelta � piena
	 */
	public void step() throws IllegalTokenLocation, WinException, FullColumnException;

	/**
	 * Accede alla griglia del giocatore
	 * @return {@code Grid}
	 */
	public Grid getGrid();

	/**
	 * Restituisce il PrintStream del giocatore
	 * @return {@code PrintStream}
	 */
	public PrintStream getOutput();

}
