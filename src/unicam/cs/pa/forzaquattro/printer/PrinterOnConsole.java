package unicam.cs.pa.forzaquattro.printer;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

import unicam.cs.pa.forzaquattro.core.Grid;

/**
 * Metodi statici per la stampa della griglia di gioco
 *
 */
@SuppressWarnings("deprecation")
public class PrinterOnConsole extends Printer implements Observer{
	
	PrintStream writer = System.out;

	/**
	 * Stampa i divisori di riga di una griglia
	 * @param writer PrintStream
	 * @param size dimensione
	 */
	private void printRowDelimiter(int size ) {
		for( int i=0 ; i<size ; i++ ) {
			writer.print("+---");
		}
		writer.println("+");
	}
	
	/**
	 * Attraverso un {@code PrintStream} stampa la {@code Grid}. Utilizza il metodo statico
	 * {@code PrinterOnConsole.printRowDelimiter} per stampare il divisore di riga.
	 * @param writer PrintStream
	 * @param grid da stampare
	 */
	public void printGrid() {
		printRowDelimiter(Grid.getInstance().getColumnNumber());
		for(int i= (Grid.getInstance().getRowNumber()-1); i>=0; i--) {
			writer.print("|");
			for(int j=0; j< Grid.getInstance().getColumnNumber(); j++) {	
				writer.print(" "+ Grid.getInstance().getCell(i,j)+ " ");
				writer.print("|");
			}
			writer.println("");
			printRowDelimiter(Grid.getInstance().getColumnNumber());
		}
	}

	@Override
	public void print(String message) {
		this.writer.println(message);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		printGrid();
	}
	
//	/**
//	 * Overload: stampa sullo Standard Output {@code System.out}
//	 * @param grid da stampare
//	 */
//	public void printGrid() {
//		printGrid(System.out, Grid.getInstance());
//	}
}
