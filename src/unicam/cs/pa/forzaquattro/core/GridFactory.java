package unicam.cs.pa.forzaquattro.core;

import java.util.Scanner;

public class GridFactory {

	private boolean done = false;
	private static GridFactory INSTANCE = new GridFactory();
	
	private GridFactory() {
		
	}
	
	public GridFactory getInstance() {
		return INSTANCE;
	}
	
	public Grid getGrid(GridType type) {
		if(done) {
			return Grid.getInstance();
		}
		switch(type) {
			case CUSTOM: arrangeGrid();
			case DEFAULT:
			default: {
				done = true;
				return Grid.getInstance();
			}
		}
	}
	
	
	/**
	 * Inserimento di valori personalizzati per la griglia di gioco. 
	 * In caso di valori errati, la griglia viene istanziata con valori di default
	 */
	private void arrangeGrid() {
		Scanner input = new Scanner(System.in);
		System.out .print("Numero di righe desiderato (enter per valori di default): ");
		try {
			String row = input.nextLine();
			int r = Integer.parseInt(row);
			System.out .print("Numero di colonne desiderato: ");
			String column = input.nextLine();
			int c = Integer.parseInt(column);
			if(r<4 || c<4) {
				throw new NumberFormatException();
			}
			System.out.println("Griglia inizializzata con valori "+r+"x"+c);
			Grid.init(r, c);
		} catch (NumberFormatException e) {
			System.out .println("Griglia istanziata con valori di default 6x7");
			Grid.getInstance();
		}
	}
	
}
