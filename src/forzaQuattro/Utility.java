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
		printRowDelimiter(writer, grid.column);
		for(int i= (grid.row-1); i>=0; i--) {
			writer.print("|");
			for(int j=0; j< grid.column; j++) {
				if(!grid.isFree(i,j)) {
				
					writer.print(" "+grid.field[i][j]+ " ");
				}
				else {
					writer.print("   ");
				}
				writer.print("|");
			}
			writer.println("");
			printRowDelimiter(writer, grid.column);
		}
	}
	
	public static void printGrid(Grid grid) {
		printGrid(System.out, grid);
	}
}
