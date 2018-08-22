package unicam.cs.pa.forzaquattro.players;

import java.io.PrintStream;
import java.util.Random;

import unicam.cs.pa.forzaquattro.core.Cell;
import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Controller;
import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.core.Token;
import unicam.cs.pa.forzaquattro.exceptions.FullColumnException;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.exceptions.WinException;

/**
 * Giocatore che effettua inserimenti casuali.
 * Per effettuare mossa utilizzare il metodo {@code step()}
 * @author Joyod
 *
 */
public class RandomPlayer implements Player{
	
	public String name;
	private Color color;
	private Grid myField;
	private PrintStream out;
	
	
	/**
	 * Costruttore fi giocatore Random automatico
	 * @param name nome
	 * @param color colore dei suoi {@code Token}
	 * @param grid griglia di gioco
	 */
	public RandomPlayer (String name, Color color, Grid grid) {
		this.name=name;
		this.color=color;
		this.myField=grid;
		this.out = System.out;
	}

	/**
	 * Sceglie a caso una colonna in cui effettuare l'inserimento del gettone
	 */
	public void step() throws IllegalTokenLocation, WinException, FullColumnException {
		Random r = new Random();
		int column = r.nextInt(this.getGrid().getColumnNumber());
		Token token=new Token(this.color);
		Cell cell = this.myField.insert(token, column);
		Controller.checkWinner(this.myField, cell); //controlli sulla cella appena inserita
	}

	@Override
	public Grid getGrid() {
		return this.myField;
	}

	@Override
	public PrintStream getOutput() {
		return this.out;
	}
	
	@Override
	public String toString() {
		return "RandomPlayer "+this.name;
	}

	
	
}