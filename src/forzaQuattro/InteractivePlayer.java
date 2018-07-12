package forzaQuattro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Predicate;


public  class InteractivePlayer implements Player {

	public String name;
	private Color color;
	private Grid myField;
	private BufferedReader in;
	private PrintStream out;
	
	
	public InteractivePlayer(String name, Color color,Grid grid, InputStream in, PrintStream out) {
		this.name=name;
		this.color=color;
		this.myField=grid;
//		in questo modo astrae l'input output dell'utente, che diventa generico
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = out;
	}
	
	public InteractivePlayer(String name, Color color, Grid grid) {
		this(name, color, grid, System.in, System.out);
	}


	public void step() throws IllegalTokenLocation, FullColumnException, WinException{
		int column = doInput(String.format("%s Inserisci nella colonna (valore da 1 a %d): ",(this.name),(this.myField.getColumnNumber())), this::isValidIndex, Integer::parseUnsignedInt);
		Token token=new Token(this.color);
		Cell cell = this.myField.insert(token, column-1);
		Controller.checkWinner(this.myField, cell); //controlli sulla cella appena inserita
	}

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
	
	private boolean isValidIndex( String txt ) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return ( v> 0 && v<=this.myField.getColumnNumber());
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public Grid getGrid() {
		return this.myField;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public PrintStream getOutput() {
		return this.out;
	}
}
