package unicam.cs.pa.forzaquattro.players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Predicate;

import unicam.cs.pa.forzaquattro.core.Cell;
import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Controller;
import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.core.Token;
import unicam.cs.pa.forzaquattro.exceptions.FullColumnException;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.exceptions.WinException;

/**
 * Giocatore interattivo, richiede input all'utente fisico.
 * Possibile scegliere metodi di input e output diversi da quelli standard.
 * Per effettuare una mossa utilizzare il metodo {@code step()}
 *
 */
public  class InteractivePlayer implements Player {

	public String name;
	private Color color;
	private Grid myField;
	private BufferedReader in;
	private PrintStream out;
	
	/**
	 * Costruttore di un Player interattivo.
	 * @param name nome scelto per il giocatore
	 * @param color nome scelto per il giocatore
	 * @param grid il campo di gioco
	 * @param in InputStream da cui inserire i dati
	 * @param out PrintStream su ui visualizzare gli output
	 */
	public InteractivePlayer(String name, Color color,Grid grid, InputStream in, PrintStream out) {
		this.name=name;
		this.color=color;
		this.myField=grid;
//		in questo modo astrae l'input output dell'utente, che diventa generico
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = out;
	}
	
	/**
	 * Overload del costruttore con I/O di default
	 * @param name nome scelto per il giocatore
	 * @param color nome scelto per il giocatore
	 */
	public InteractivePlayer(String name, Color color, Grid grid) {
		this(name, color, grid, System.in, System.out);
	}


	/**
	 * Il giocatore effettua una mossa: viene richiesta una colonna in cui inserire il {@code Token} del proprio colore.
	 * A partire dalla cella in cui il {@code Token} va a finire, viene effettuato il controllo della vittoria.
	 */
	public int step() throws IllegalTokenLocation{
		int column = doInput(String.format("%s Inserisci nella colonna (valore da 1 a %d): ",(this.name),(this.myField.getColumnNumber())), this::isValidIndex, Integer::parseUnsignedInt);
		
		return column-1;
		
//		Token token=new Token(this.color);
//		Cell cell = this.myField.insert(token, column-1);
//		Controller.checkWinner(this.myField, cell); //controlli sulla cella appena inserita
	}

	/**
	 * Richiede al giocatore fisico la colonna scelta per posizionare un {@code Token}
	 * @param message da stampare per rendere comprensibile la richiesta
	 * @param condition per evitare inserimenti errati
	 * @param readFun per comprendere l'input
	 * @return il valore inserito
	 * @throws IllegalTokenLocation se l'input non è valido o non leggibile
	 */
	private <T> T doInput( String message , Predicate<String> condition , Function<String,T> readFun ) throws IllegalTokenLocation {
		while (true) {
			this.out.print(message);
			String line;
			try {
				line = this.in.readLine();
			} catch (IOException e) {
				throw new IllegalTokenLocation("Errore Input");
			}
			if (!condition.test(line)) {
				throw new IllegalTokenLocation("Colonna non valida");
			} else {
				return readFun.apply(line);
			}
		}
	}
	
	/**
	 * Determina se il valore inserito è accettabile comem indice di colona
	 * @param txt possibilmente scelto dall'utente
	 * @return {@code true} se sta nel range di indici di colonne della griglia, {@code false} altrimenti
	 */
	private boolean isValidIndex( String txt ) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return ( v> 0 && v<=this.myField.getColumnNumber());
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Griglia del Player
	 */
	@Override
	public Grid getGrid() {
		return this.myField;
	}

	/**
	 * Stringa name
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Ottiene il PrintStream dell'utente
	 */
	@Override
	public PrintStream getOutput() {
		return this.out;
	}
	
	
	public Color getColor() {
		return this.color;
	}
}
