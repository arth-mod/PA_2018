package unicam.cs.pa.forzaquattro.printer;

import java.io.PrintStream;
import java.util.Observable;

import unicam.cs.pa.forzaquattro.core.Grid;

/**
 * Oggetto Printer che permette la stampa su console. Contiene anche i metodi per la stampa della griglia di gioco.
 * Essendo derivato da {@code Printer} implementa l'interfaccia {@code Observer}. Viene notificato in caso di modifiche alla griglia
 * di gioco e procede alla sua stampa
 *
 */
@SuppressWarnings("deprecation")
public class PrinterOnConsole extends Printer{ //mdf nuovo
	
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
	 * Attraverso un {@code PrintStream} stampa la {@code Grid}. Utilizza il metodo
	 * {@code printRowDelimiter per stampare il divisore di riga.
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

	/**
	 * Stampa dei messaggi
	 */
	@Override
	public void print(String message) {
		this.writer.println(message);
		
	}

	/**
	 * Metodo dell'interfaccia {@code Observer} per la ricezione delle notifiche
	 */
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
