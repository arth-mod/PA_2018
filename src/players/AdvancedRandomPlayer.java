package players;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

import core.Cell;
import core.Color;
import core.Controller;
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
	
	public Cell checkThree(Grid grid, Cell cell) {
		Hashtable<BiFunction<Grid, Cell, ArrayList<Cell>>, Function<Cell,Integer>> methods = grid.getMethods();
		Iterator<BiFunction<Grid, Cell, ArrayList<Cell>>> t = methods.keySet().iterator();
		while(t.hasNext()) {
			BiFunction<Grid, Cell, ArrayList<Cell>> m = t.next();
			Controller.groupConsecutiveIndex(Controller.showIndex(grid, cell, m, methods.get(m)))
			.stream()
//			.forEach(l -> System.out.println(m.toString()+l));;
			.filter(l -> l.size()>=3);
//			.
//			;
		}
		return null;
	}
	
	

}
