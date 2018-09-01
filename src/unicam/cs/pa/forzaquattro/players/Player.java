package unicam.cs.pa.forzaquattro.players;


import java.util.Observer;

import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Grid;
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
	 * @throws IllegalTokenLocation se la colonna scelta � errata
	 * @throws WinException se l'inserimento comporta la vittoria
	 * @throws FullColumnException se la colonna scelta � piena
	 */
	public int step() throws IllegalTokenLocation;

//	/**
//	 * Accede alla griglia del giocatore
//	 * @return {@code Grid}
//	 */
//	public Grid getGrid();

	/**
	 * Restituisce il PrintStream del giocatore
	 * @return {@code PrintStream}
	 */
//	public PrintStream getOutput();
	
	public Color getColor();

//	public void insertAccepted();

	public void receiveMessage(String message); //mdf nuovo

	public Printer getPrinter(); //mdf nuovo

}
