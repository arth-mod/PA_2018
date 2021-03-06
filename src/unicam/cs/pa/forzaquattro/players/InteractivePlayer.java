package unicam.cs.pa.forzaquattro.players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Function;
import java.util.function.Predicate;
import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.printer.Printer;
import unicam.cs.pa.forzaquattro.printer.PrinterOnConsole;

/**
 * Giocatore interattivo, richiede input all'utente fisico.
 * Possibile scegliere metodi di input e output diversi da quelli standard.
 * Per effettuare una mossa utilizzare il metodo {@code step()}
 *
 */
public  class InteractivePlayer implements Player { 

	public String name;
	private Color color;
//	private Grid myField;
	private BufferedReader in;
//	private PrintStream out;
	private Printer printer;
	
	/**
	 * Costruttore di un Player interattivo.
	 * @param name nome scelto per il giocatore
	 * @param color nome scelto per il giocatore
	 * @param grid il campo di gioco
	 * @param in InputStream da cui inserire i dati
	 * @param out PrintStream su ui visualizzare gli output
	 */
	@SuppressWarnings("deprecation")
	public InteractivePlayer(String name, Color color,Grid grid, InputStream in, Printer printer) {
		this.name=name;
		this.color=color;
//		this.myField=grid;
//		in questo modo astrae l'input output dell'utente, che diventa generico
		this.in = new BufferedReader(new InputStreamReader(in));
//		this.out = out;
		this.printer = printer;
		Grid.getInstance().addObserver(printer); //mdf 
	}
	
	/**
	 * Overload del costruttore con I/O di default
	 * @param name nome scelto per il giocatore
	 * @param color nome scelto per il giocatore
	 */
	public InteractivePlayer(String name, Color color, Grid grid) {
		this(name, color, grid, System.in, new PrinterOnConsole());
	}


	/**
	 * Il giocatore effettua una mossa: viene richiesta una colonna in cui inserire il {@code Token} del proprio colore.
	 * A partire dalla cella in cui il {@code Token} va a finire, viene effettuato il controllo della vittoria.
	 */
	public int step() throws IllegalTokenLocation{ //mdf non effettua pi� l'inserimento
		int column = doInput(String.format("%s Inserisci nella colonna (valore da 1 a %d): ",(this.name),(Grid.getInstance().getColumnNumber())), this::isValidIndex, Integer::parseUnsignedInt);
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
	 * @throws IllegalTokenLocation se l'input non � valido o non leggibile
	 */
	private <T> T doInput( String message , Predicate<String> condition , Function<String,T> readFun ) throws IllegalTokenLocation {
		while (true) {
//			this.out.print(message);
			printer.print(message);
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
	 * Determina se il valore inserito � accettabile comem indice di colona
	 * @param txt possibilmente scelto dall'utente
	 * @return {@code true} se sta nel range di indici di colonne della griglia, {@code false} altrimenti
	 */
	private boolean isValidIndex( String txt ) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return ( v> 0 && v<=Grid.getInstance().getColumnNumber());
		} catch (NumberFormatException e) {
			return false;
		}
	}

//	/**
//	 * Griglia del Player
//	 */
//	@Override
//	public Grid getGrid() {
//		return this.myField;
//	}

	/**
	 * Stringa name
	 */
	@Override
	public String toString() {
		return this.name;
	}

//	/**
//	 * Ottiene il PrintStream dell'utente
//	 */
//	@Override
//	public PrintStream getOutput() {
//		return this.out;
//	}
	
	
	public Color getColor() {
		return this.color;
	}

//	@Override
//	public void insertAccepted() {
//		this.printer.printGrid();
//	}

	/**
	 * Da utilizzare per inviare messaggi al Player. Questo proceder� alla stampa attraverso il suo {@code Printer}
	 */
	@Override
	public void receiveMessage(String message) { //mdf nuovo
		this.printer.print(message);
		
	}

	/**
	 * Accessor al {@code Printer} del Player
	 */
	@Override
	public Printer getPrinter() { //mdf nuovo
		return this.printer;
	}
}
