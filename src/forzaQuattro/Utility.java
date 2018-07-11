package forzaQuattro;

import java.io.PrintStream;


public class Utility {

	private static void printRowDelimiter( PrintStream writer , int size ) {
//		writer.print("    ");
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
	
	public static void printGrid(PrintStream writer, Grid grid) {
		printRowDelimiter(writer, grid.getColumnNumber());
		for(int i= (grid.getRowNumber()-1); i>=0; i--) {
			writer.print("|");
			for(int j=0; j< grid.getColumnNumber(); j++) {
//				if(!grid.isFree(i,j)) {
//				
					writer.print(" "+grid.getCell(i,j)+ " ");
//				}
//				else {
//					writer.print("   ");
//				}
				writer.print("|");
			}
			writer.println("");
			printRowDelimiter(writer, grid.getColumnNumber());
		}
	}
	
	public static void printGrid(Grid grid) {
		printGrid(System.out, grid);
	}
}
