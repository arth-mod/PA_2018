package players;

import java.io.PrintStream;

import core.Color;
import core.Grid;
import exceptions.FullColumnException;
import exceptions.IllegalTokenLocation;
import exceptions.WinException;

public class AdvancedRandomPlayer implements Player {
	
	public String name;
	private Color color;
	private Grid myField;
	private PrintStream out;
	
	
	public AdvancedRandomPlayer (String name, Color color, Grid grid) {
		this.name=name;
		this.color=color;
		this.myField=grid;
		this.out = System.out;
	}

	@Override
	public void step() throws IllegalTokenLocation, WinException, FullColumnException {
		// TODO Auto-generated method stub

	}

	@Override
	public Grid getGrid() {
		return this.myField;
	}

	@Override
	public PrintStream getOutput() {
		return this.out;
	}
	
//	private Cell checkThree(Grid grid, Cell cell) {
//		
//	}
	
	

}
