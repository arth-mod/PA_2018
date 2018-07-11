package forzaQuattro;

import java.io.PrintStream;
import java.util.Random;

public class RandomPlayer implements Player{
	
	public String name;
	private Color color;
	private Grid myField;
	private PrintStream out;
	
	public RandomPlayer (String name, Color color, Grid grid) {
		this.name=name;
		this.color=color;
		this.myField=grid;
		this.out = System.out; //l'output non ci interessa -> creare printstream che non stampi nulla
	}

	@Override
	public void step() throws IllegalTokenLocation, WinException, FullColumnException {
		
		Random r = new Random();
		int column = r.nextInt(this.getGrid().getColumnNumber());
		Token token=new Token(this.color);
		Cell cell = this.myField.insert(token, column);
		Controller.check(this.myField, cell); //controlli sulla cella appena inserita
		
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
