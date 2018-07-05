package forzaQuattro;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public  class InteractivePlayer implements Player {

	public String name;
	private  Color color;
	private static  Grid myField;
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


	@Override
	public void insertToken(int column) throws IllegalTokenLocation{
		Token token=new Token(this.color, 0);
		this.myField.insert(token, column);
	}

}
