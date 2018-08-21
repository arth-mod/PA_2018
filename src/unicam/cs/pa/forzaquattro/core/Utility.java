package unicam.cs.pa.forzaquattro.core;

import java.io.PrintStream;

/**
 * Metodi statici per la stampa della griglia di gioco
 *
 */
public class Utility {

	/**
	 * Stampa i divisori di riga di una griglia
	 * @param writer PrintStream
	 * @param size dimensione
	 */
	private static void printRowDelimiter( PrintStream writer , int size ) {
		for( int i=0 ; i<size ; i++ ) {
			writer.print("+---");
		}
		writer.println("+");
	}
	
	/**
	 * Attraverso un {@code PrintStream} stampa la {@code Grid}. Utilizza il metodo statico
	 * {@code Utility.printRowDelimiter} per stampare il divisore di riga.
	 * @param writer PrintStream
	 * @param grid da stampare
	 */
	public static void printGrid(PrintStream writer, Grid grid) {
		printRowDelimiter(writer, grid.getColumnNumber());
		for(int i= (grid.getRowNumber()-1); i>=0; i--) {
			writer.print("|");
			for(int j=0; j< grid.getColumnNumber(); j++) {	
				writer.print(" "+grid.getCell(i,j)+ " ");
				writer.print("|");
			}
			writer.println("");
			printRowDelimiter(writer, grid.getColumnNumber());
		}
	}
	
	/**
	 * Overload: stampa sullo Standard Output {@code System.out}
	 * @param grid da stampare
	 */
	public static void printGrid(Grid grid) {
		printGrid(System.out, grid);
	}
}
