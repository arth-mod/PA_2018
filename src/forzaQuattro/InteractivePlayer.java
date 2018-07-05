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


//	@Override
//	public void insertToken(int column) throws IllegalTokenLocation{
//		Token token=new Token(this.color, 0);
//		this.myField.insert(token, column);
//	}
	
	public void step() throws IllegalTokenLocation{
		int column = doInput(String.format("Insert column (a value from 0 to %d): ",(this.myField.column -1)), this::isValidIndex, Integer::parseUnsignedInt);
		Token token=new Token(this.color, 0);
		this.myField.insert(token, column);
	}

	private <T> T doInput( String message , Predicate<String> condition , Function<String,T> readFun ) throws IllegalTokenLocation {
		while (true) {
			System.out.print(message);
			String line;
			try {
				line = this.in.readLine();
			} catch (IOException e) {
				throw new IllegalTokenLocation();
			}
			if (!condition.test(line)) {
				System.out.println("Input Error!");
			} else {
				return readFun.apply(line);
			}
		}
	}
	
	private boolean isValidIndex( String txt ) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return (v<this.myField.column);
		} catch (NumberFormatException e) {
			return false;
		}
	}

}