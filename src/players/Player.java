package players;

import java.io.PrintStream;

import core.Color;
import core.Grid;
import exceptions.FullColumnException;
import exceptions.IllegalTokenLocation;
import exceptions.WinException;

public interface Player {
	
	String name = null;
	Color color = null;
	Grid myField = null; ///servono i campi?

	public void step() throws IllegalTokenLocation, WinException, FullColumnException;

	public Grid getGrid();

	public PrintStream getOutput();

}
