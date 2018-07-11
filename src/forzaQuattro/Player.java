package forzaQuattro;

import java.io.PrintStream;

public interface Player {
	
	String name = null;
	Color color = null;
	Grid myField = null; ///servono i campi?

	public void step() throws IllegalTokenLocation, WinException, FullColumnException;

	public Grid getGrid();

	public PrintStream getOutput();

}
