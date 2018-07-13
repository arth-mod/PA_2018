package players;

import java.io.PrintStream;

import core.Color;
import core.Grid;
import exceptions.FullColumnException;
import exceptions.IllegalTokenLocation;
import exceptions.WinException;

/**
 * Interfaccia {@code Player} da implementare per definire una nuova tipologia di {@code Player}
 * Per effettuare una mossa utilizzare il metodo {@code step()}
 *
 */
public interface Player {
	
	/**
	 * Nome associato al giocatore
	 */
	String name = null;
	/**
	 * Colore dei {@code Token} lanciati dal giocatore
	 */
	Color color = null;
	/**
	 * Griglia di gioco
	 */
	Grid myField = null;

	/**
	 * Il gicatore effettua una mossa di inserimento del proprio {@code Token}
	 * in una coonna della griglia di gioco.
	 * 
	 * @throws IllegalTokenLocation se la colonna scelta è errata
	 * @throws WinException se l'inserimento comporta la vittoria
	 * @throws FullColumnException se la colonna scelta è piena
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
