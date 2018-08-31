package unicam.cs.pa.forzaquattro.printer;

import java.util.Observer;

public abstract class Printer implements Observer {

	public abstract void printGrid();

	public abstract void print(String message);
	

}
