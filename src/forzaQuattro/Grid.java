package forzaQuattro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;

public class Grid {
	
	public static final int DEFAULT_ROW = 6;
	public static final int DEFAULT_COLUMN = 7;
	public final Cell[][] field;
	public int row;
	public int column;
	
	/**
	 * Costruttore - effettua il riempimento con {@code Cell} vuote
	 * @param row numero di righe
	 * @param column numero di colonne
	 */
	public Grid(int row, int column) {
		this.field = new Cell[row][column];
		this.row = row;
		this.column=column;
		fill();
	}
	/**
	 * Riempie una griglia di {@code Cell} vuote
	 */
	private void fill() {
		for( int i=0 ; i<this.row ; i++ ) {
			for( int j=0 ; j<this.column ; j++ ) {
				this.field[i][j] = new Cell(i,j);
			}
		}
	}
	
	/**
	 * Costruttore di default
	 */
	public Grid() {
		this(DEFAULT_ROW, DEFAULT_COLUMN);
	}
	
	/**
	 * Permette l'inserimento del {@code Token} nella colonna desiderata
	 * @param token
	 * @param column
	 * @return {@code Cell} cella in cui è stato inserito il token
	 * @throws IllegalTokenLocation se la {@code Cell} è già occupata
	 * @throws FullColumnException se la colonna risulta piena 
	 */
	public Cell insert(Token token, int column)throws IllegalTokenLocation, FullColumnException{
		int row;
		row = this.getRow(column);
		this.field[row][column].setToken(token);
		return this.field[row][column];
	}

	/**
	 * Durante l'inserimento, determina la prima riga libera della colonna
	 * @param column
	 * @return {@code int} row index
	 * @throws FullColumnException se la colonna risulta piena
	 */
	private int getRow(int column) throws FullColumnException{
		int i;
		for(i=0; i<this.row; i++) {
			if(isFree(i, column) ) {
				return i;
			}
		}
		throw new FullColumnException("Colonna piena, prova con un'altra");
	}
	
	/**
	 * Funzione booleana per determinare lo stato di una {@code Cell}
	 * @param i indice di riga della cella
	 * @param j indice di colonna della cella
	 * @return true se la cella è piena
	 */
	public boolean isFree(int row, int column) {
		return( this.field[row][column].getStatus()==CellStatus.EMPTY ?  true :  false);
	}

	/**
	 * Access method: numero di colonne della griglia
	 * @return {@code int} colonne
	 */
	public int getColumnNumber() {
		return this.column;
	}
	
	/**
	 * Access method: numero di righe della griglia
	 * @return {@code int} righe
	 */
	public int getRowNumber() {
		return this.row;
	}
	
	/**
	 * Restituisce la {@code Cell} in posizione (row, column)
	 * @param row
	 * @param column
	 * @return Cella della griglia il posizione (row, column)
	 */
	private Cell getCell(int row, int column) {
		return this.field[row][column];
	}

	/**
	 * Data una {@code Cell} della {@code Grid},
	 * restituisce l'intera riga in cui è contenuta
	 * @param cell
	 * @return ArrayList<Cell> 
	 */
	public ArrayList<Cell> getCellRow(Cell cell) {
		return new ArrayList<Cell>(Arrays.asList(this.field[cell.getRow()]));
	}
	
	/**
	 * Data una {@code Cell} della {@code Grid},
	 * restituisce l'intera colonna in cui è contenuta
	 * @param cell
	 * @return ArrayList<Cell> 
	 */
	public ArrayList<Cell> getCellColumn(Cell cell) {
		ArrayList<Cell> col = new ArrayList<>();
		for(int i=0; i<this.getRowNumber(); i++) {
			col.add(this.getCell(i, cell.getColumn()));
		}
		return col;
	}

	/**
	 * Data una {@code Cell} della {@code Grid},
	 * restituisce l'intera diagonale (sinistra destra, dal basso verso l'alto)
	 * @param cell
	 * @return ArrayList<Cell> 
	 */
	public ArrayList<Cell> getCellAscendingDiagonal(Cell cell) {
		Cell start = this.lowestLeftCell(cell);
		ArrayList<Cell> vector = new ArrayList<>();
		vector.add(start);
		while(!this.isLimitUpperCell(start)) {
			start = this.getCell(start.getRow()+1, start.getColumn()+1);
			vector.add(start);
		}
		return vector;
	}
	

	/**
	 * Data una {@code Cell} della {@code Grid},
	 * restituisce l'intera diagonale (sinistra destra, dall'alto verso il basso)
	 * @param cell
	 * @return ArrayList<Cell> 
	 */
	public ArrayList<Cell> getCellDescendingDiagonal(Cell cell) {
		Cell start = this.higestLeftCell(cell);
		ArrayList<Cell> vector = new ArrayList<>();
		vector.add(start);
		while(!this.isLimitBottomCell(start)) {
			start = this.getCell(start.getRow()-1, start.getColumn()+1);
			vector.add(start);
		}
		return vector;
	}
	
	/**
	 * 
	 * @param cell 
	 * @return true se la {@code Cell} è l'ultima della diagonale crescente
	 */
	private boolean isLimitUpperCell(Cell cell) {
		return cell.getRow()==this.getRowNumber()-1 || cell.getColumn() == this.getColumnNumber()-1;
	}

	/**
	 * 
	 * @param cell
	 * @return {@code true} se la {@code Cell} è l'ultima della diagonale decrescemte
	 */
	private boolean isLimitBottomCell(Cell cell) {
		return cell.getRow() == 0 || cell.getColumn() == this.getColumnNumber()-1;
	}

	/**
	 * 
	 * @param cell
	 * @return {@code Cell} in basso a sinistra sulla diagonale
	 */
	private Cell lowestLeftCell(Cell cell) {
		int currentRow=cell.getRow();
		int currentColumn=cell.getColumn();
		while (currentRow!=0 && currentColumn!=0) {
			currentColumn--;
			currentRow--;
		}
		return (this.getCell(currentRow, currentColumn));
	}
	
	/**
	 * 
	 * @param cell
	 * @return {@code Cell} in alto a sinistra sulla diagonale
	 */
	private Cell higestLeftCell(Cell cell) {
		int currentRow= cell.getRow();
		int currentColumn=cell.getColumn();
		while (currentRow!=this.getRowNumber()-1 && currentColumn!=0) {
			currentColumn--;
			currentRow++;
		}
		return (this.getCell(currentRow, currentColumn));
	}
	
	
	public static BiFunction<Grid, Cell, ArrayList<Cell>> getCellRow = (g,c) -> g.getCellRow(c);
	public static BiFunction<Grid, Cell, ArrayList<Cell>> getCellColumn = (g,c) -> g.getCellColumn(c);
	public static BiFunction<Grid, Cell, ArrayList<Cell>> getCellAscendingDiagonal = (g,c) -> g.getCellAscendingDiagonal(c);
	public static BiFunction<Grid, Cell, ArrayList<Cell>> getCellDescendingDiagonal = (g,c) -> g.getCellDescendingDiagonal(c);
	
	public ArrayList<BiFunction<Grid, Cell, ArrayList<Cell>>> getMethods() {
		ArrayList<BiFunction<Grid, Cell, ArrayList<Cell>>> t = new ArrayList<>();
		t.add(getCellRow);
		t.add(getCellColumn);
		t.add(getCellAscendingDiagonal);
		t.add(getCellDescendingDiagonal);
		return t;
	}
}
