package unicam.cs.pa.forzaquattro.core;

import java.util.Scanner;

/**
 * Factory method. A partire da un {@code GridType} permette l'istanziazione della {@code Grid}.
 * Essendo {@code Grid} ideata come Singleton, la sua creazione ha effetto solo la prima volta
 * @author Joyod
 *
 */
public class GridFactory {

	/**
	 * Permette la creazione della {@code Grid}. In caso di Custom Grid le dimensioni verranno richieste all'utente
	 * @param type tipo di griglia desiderato
	 * @return istanza della griglia
	 */
	public Grid getGrid(GridType type) {
		switch(type) {
			case CUSTOM: arrangeGrid();
			case DEFAULT:
			default: return Grid.getInstance();
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
