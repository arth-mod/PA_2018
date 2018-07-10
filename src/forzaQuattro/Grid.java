package forzaQuattro;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
	
	public static final int DEFAULT_ROW = 6;
	public static final int DEFAULT_COLUMN = 7;
	public final Cell[][] field;
	public int row;
	public int column;
	
	
	public Grid(int row, int column) {
		this.field = new Cell[row][column];
		this.row = row;
		this.column=column;
		fill();
	}
	
	private void fill() {
		for( int i=0 ; i<this.row ; i++ ) {
			for( int j=0 ; j<this.column ; j++ ) {
				this.field[i][j] = new Cell(i,j);
			}
		}
	}
	
	public Grid() {
		this(DEFAULT_ROW, DEFAULT_COLUMN);
	}
	
	public Cell insert(Token token, int column)throws IllegalTokenLocation, FullColumnException{
		int row;
		row = this.getRow(column);
		this.field[row][column].setToken(token);
		return this.field[row][column];
	}

	private int getRow(int column) throws FullColumnException{
		int i;
		for(i=0; i<this.row; i++) {
			if(isFree(i, column) ) {
				return i;
			}
		}
		throw new FullColumnException("Colonna piena, prova con un'altra");
	}
	

	public boolean isFree(int i, int j) {
		return( this.field[i][j].getStatus()==CellStatus.EMPTY ?  true :  false);
	}

	public int getColumnNumber() {
		return this.column;
	}
	
	public int getRowNumber() {
		return this.row;
	}
	
	/*
	 * restituisce la cella in posizione row col
	 */
	private Cell getCell(int row, int column) {
		return this.field[row][column];
	}

	/*
	 * data una cella, restituisce l'intera riga in cui è contenuta
	 */
	public ArrayList<Cell> getCellRow(Cell cell) {
		return new ArrayList<Cell>(Arrays.asList(this.field[cell.getRow()]));
	}

	
	/*
	 * data una cella, restituisce l'intera colonna in cui è contenuta
	 */
	public ArrayList<Cell> getCellColumn(Cell cell) {
		ArrayList<Cell> col = new ArrayList<>();
		for(int i=0; i<this.getRowNumber(); i++) {
			col.add(this.getCell(i, cell.getColumn()));
		}
		return col;
	}
	
	/*
	 * data una cella, restituisce l'intera diagonale (sinistra destra, dal basso verso l'alto)
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
	
	/*
	 * data una cella, restituisce l'intera diagonale (sinistra destra, dall'alto verso il basso)
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
	
	
	private boolean isLimitUpperCell(Cell cell) {
		return cell.getRow()==this.getRowNumber()-1 || cell.getColumn() == this.getColumnNumber()-1;
	}

	
	private boolean isLimitBottomCell(Cell cell) {
		return cell.getRow() == 0 || cell.getColumn() == this.getColumnNumber()-1;
	}

	/*
	 * restituisce la cella in basso a sinistra sulla diagonale
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
	
	/*
	 * restuisce la cella in alto a sinistra sulla diagonale
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
}
