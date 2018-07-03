package forzaQuattro;

import java.io.PrintStream;


public class Utility {

	private static void printRowDelimiter( PrintStream writer , int size ) {
		writer.print("    ");
		for( int i=0 ; i<size ; i++ ) {
			writer.print("+---");
		}
		writer.println("+");
	}
	
	private static void printRow(PrintStream writer, int size) {
		writer.print("    ");
		for( int i=0 ; i<size ; i++ ) {			
			writer.print("| "+"  ");
		}
		writer.println("|");
	}
	
	public static void printGrid(PrintStream writer, Grid griglia) {
		printRowDelimiter(writer, griglia.column);
		for(int i=0; i<griglia.row; i++) {
			printRow(writer, griglia.column);
			printRowDelimiter(writer, griglia.column);
		}
	}
}
