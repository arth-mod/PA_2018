package unicam.cs.pa.forzaquattro.core;

import java.util.ArrayList;

import java.util.Observable;

import unicam.cs.pa.forzaquattro.exceptions.FullColumnException;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.exceptions.WinException;

/**
 * Rappresenta la griglia di gioco. Contiene un array di {@code Cell}.
 * {@code Grid} è un {@code Singleton} pertanto ne esiste una sola istanza,
 * accessibile attraverso il metodo {@code getIstance()}.
 * Possibile modificare le dimensioni della {@code Grid} con il metodo {@code init}
 * prima di accedervi la prima volta
 *
 */
@SuppressWarnings("deprecation")
public class Grid extends Observable{
	
	private static final int DEFAULT_ROW = 6;
	private static final int DEFAULT_COLUMN = 7;
	private final Cell[][] field;
	private int row;
	private int column;
	//*************************************************************************************************************************************************
	private int[] counter; 
	//***********************************************************************************************************************************************************************
	private static Grid INSTANCE = null;
	private static boolean flag = false;
	
	
	/**
	 * {@code Grid} è progettato come {@code Singleton}.
	 * Pertanto può esisterne solo un'istanza, accessibile attraverso tale metodo
	 * @return la {@code Grid} già istanziata o una appena creata (per la prima volta)
	 */
	public static Grid getInstance() {
		if(!flag) {
			init(DEFAULT_ROW, DEFAULT_COLUMN);
		}
		return INSTANCE;
	}
	
	/**
	 * Metodo per inizializzare una Griglia con dimesioni personalizzate.
	 * {@code Grid} è un {@code Singleton} quindi tale metodo ha effetto solo la prima volta 
	 * @param row 
	 * @param column
	 */
	public static void init(int row, int column) {
		if(flag) {
			return;
		}
		INSTANCE = new Grid(row,column);
		flag = true;
	}
	
	
	/**
	 * Costruttore - effettua il riempimento con {@code Cell} vuote.
	 * {@code Grid} è progettato come {@code Singleton} quindi il costruttore è privato.
	 * Usare il metodo {@code getInstance} per accedere all'istanza.
	 * @param row numero di righe
	 * @param column numero di colonne
	 */
	private Grid(int row, int column) {
		this.field = new Cell[row][column];
		this.row = row;
		this.column=column;
		this.counter = new int[column];
		fill();
	}
	
	/**
	 * Riempie una griglia di {@code Cell} vuote.
	 */
	private void fill() {
		for ( int j=0 ; j<this.column ; j++ ){
			this.counter[j] = 0; //***************contatore colonne gliglia**********************************************************
			for ( int i=0 ; i<this.row ; i++ ){
				this.field[i][j] = new Cell(i,j);
			}
		}
	}
	
	/**
	 * Costruttore di default.
	 * {@code Grid} è progettato come {@code Singleton} quindi il costruttore è privato.
	 * Usare il metodo {@code getInstance} per accedere all'istanza.
	 */
	private Grid() {
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
	protected Cell insert(Token token, int column)throws IllegalTokenLocation, FullColumnException, WinException{
		int row;
		try {
			row = this.getRow(column);
			this.field[row][column].setToken(token);
			this.counter[column] ++;
			this.setChanged();
			this.notifyObservers();
		} catch (WinException e) {
			this.setChanged();
			this.notifyObservers();
			throw new WinException();
		}
		
		//***************************************************************************
		return this.field[row][column];
	}

	/**
	 * Durante l'inserimento, determina la prima riga libera della colonna.
	 * @param column
	 * @return {@code int} row index
	 * @throws FullColumnException se la colonna risulta piena
	 */
	private int getRow(int column) throws FullColumnException{
//		int i;
//		for(i=0; i<this.row; i++) {
//			if(isFree(i, column) ) {
//				return i;
//			}
//		} ************************************************************************************************************************
		int i = this.counter[column];
		if(i < this.getRowNumber()) {
			return i;
		}
		
		throw new FullColumnException("Colonna piena, prova con un'altra");
	}
	
	/**
	 * Funzione booleana per determinare lo stato di una {@code Cell}
	 * @param row indice di riga della cella
	 * @param column indice di colonna della cella
	 * @return true se la cella è piena
	 */
	public boolean isFree(int row, int column) {
		return( this.field[row][column].isEmpty());
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
	public Cell getCell(int row, int column){
		if(row>=0 && row<this.getRowNumber() && column>=0 && column<this.getColumnNumber()) {
//			throw new IllegalTokenLocation();
			return this.field[row][column];
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param row riga della cella di partenza
	 * @param col colonna della cella di partenza
	 * @param direction {@code VERT} - {@code HOR} - {@code DIAG} - {@code ANTIDIAG}
	 * @return le celle adiancenti nella direzione specificata. Le celle vuote sono escluse
	 */
	public ArrayList<Cell> getNeighbours(int row, int col, Direction direction){
		ArrayList<Cell> neighbours = new ArrayList<>();
		Cell cell1 = null;
		Cell cell2 = null;
		switch(direction) {
			case VERT	 :	cell1 = getCell(row+1, col); cell2 = getCell(row-1, col); break;
			case HOR	 :	cell1 = getCell(row, col+1); cell2 = getCell(row, col-1); break;
			case DIAG	 : 	cell1 = getCell(row+1, col+1); cell2 = getCell(row-1, col-1); break;
			case ANTIDIAG:	cell1 = getCell(row-1, col+1); cell2 = getCell(row+1, col-1); break;
		}
		if(cell1 != null && !cell1.isEmpty()) {
			neighbours.add(cell1);
		}
		if(cell2 != null && !cell2.isEmpty()) {
			neighbours.add(cell2);
		}
		return neighbours;
	}
	
	
	
	
//	
//	
//
//	/**
//	 * Data una {@code Cell} della {@code Grid},
//	 * restituisce l'intera riga in cui è contenuta
//	 * @param cell
//	 * @return ArrayList<Cell> 
//	 */
//	public ArrayList<Cell> getCellRow(Cell cell) {
//		return new ArrayList<Cell>(Arrays.asList(this.field[cell.getRow()]));
//	}
//	
//	/**
//	 * Data una {@code Cell} della {@code Grid},
//	 * restituisce l'intera colonna in cui è contenuta
//	 * @param cell
//	 * @return ArrayList<Cell> 
//	 */
//	public ArrayList<Cell> getCellColumn(Cell cell) {
//		ArrayList<Cell> col = new ArrayList<>();
//		for(int i=0; i<this.getRowNumber(); i++) {
//			col.add(this.getCell(i, cell.getColumn()));
//		}
//		return col;
//	}
//
//	/**
//	 * Data una {@code Cell} della {@code Grid},
//	 * restituisce l'intera diagonale (sinistra destra, dal basso verso l'alto)
//	 * @param cell
//	 * @return ArrayList<Cell> 
//	 */
//	public ArrayList<Cell> getCellAscendingDiagonal(Cell cell) {
//		Cell start = this.lowestLeftCell(cell);
//		ArrayList<Cell> vector = new ArrayList<>();
//		vector.add(start);
//		while(!this.isLimitUpperCell(start)) {
//			start = this.getCell(start.getRow()+1, start.getColumn()+1);
//			vector.add(start);
//		}
//		return vector;
//	}
//	
//
//	/**
//	 * Data una {@code Cell} della {@code Grid},
//	 * restituisce l'intera diagonale (sinistra destra, dall'alto verso il basso)
//	 * @param cell
//	 * @return ArrayList<Cell> 
//	 */
//	public ArrayList<Cell> getCellDescendingDiagonal(Cell cell) {
//		Cell start = this.higestLeftCell(cell);
//		ArrayList<Cell> vector = new ArrayList<>();
//		vector.add(start);
//		while(!this.isLimitBottomCell(start)) {
//			start = this.getCell(start.getRow()-1, start.getColumn()+1);
//			vector.add(start);
//		}
//		return vector;
//	}
//	
//	/**
//	 * 
//	 * @param cell 
//	 * @return true se la {@code Cell} è l'ultima della diagonale crescente
//	 */
//	private boolean isLimitUpperCell(Cell cell) {
//		return cell.getRow()==this.getRowNumber()-1 || cell.getColumn() == this.getColumnNumber()-1;
//	}
//
//	/**
//	 * 
//	 * @param cell
//	 * @return {@code true} se la {@code Cell} è l'ultima della diagonale decrescemte
//	 */
//	private boolean isLimitBottomCell(Cell cell) {
//		return cell.getRow() == 0 || cell.getColumn() == this.getColumnNumber()-1;
//	}
//
//	/**
//	 * 
//	 * @param cell
//	 * @return {@code Cell} in basso a sinistra sulla diagonale
//	 */
//	private Cell lowestLeftCell(Cell cell) {
//		int currentRow=cell.getRow();
//		int currentColumn=cell.getColumn();
//		while (currentRow!=0 && currentColumn!=0) {
//			currentColumn--;
//			currentRow--;
//		}
//		return (this.getCell(currentRow, currentColumn));
//	}
//	
//	/**
//	 * 
//	 * @param cell
//	 * @return {@code Cell} in alto a sinistra sulla diagonale
//	 */
//	private Cell higestLeftCell(Cell cell) {
//		int currentRow= cell.getRow();
//		int currentColumn=cell.getColumn();
//		while (currentRow!=this.getRowNumber()-1 && currentColumn!=0) {
//			currentColumn--;
//			currentRow++;
//		}
//		return (this.getCell(currentRow, currentColumn));
//	}
//	
//	
//	public static BiFunction<Grid, Cell, ArrayList<Cell>> getCellRow = (g,c) -> g.getCellRow(c);
//	public static BiFunction<Grid, Cell, ArrayList<Cell>> getCellColumn = (g,c) -> g.getCellColumn(c);
//	public static BiFunction<Grid, Cell, ArrayList<Cell>> getCellAscendingDiagonal = (g,c) -> g.getCellAscendingDiagonal(c);
//	public static BiFunction<Grid, Cell, ArrayList<Cell>> getCellDescendingDiagonal = (g,c) -> g.getCellDescendingDiagonal(c);
//	
//	
//	/**
//	 * Restituisce una {@code Hashtable} contenente la funzione per ottenere una riga della griglia
//	 * e la rispettiva funzione per calcolarne gli indici delle celle.
//	 * Se la {@code BiFunction} restituisce una diagonale, 
//	 * la funzione che calcola gli indici delle celle sarà quella che restituisce l'indice di colonna.
//	 * Se la {@code BiFunction} restituisce una colonna, 
//	 * la funzione che calcola gli indici delle celle sarà quella che restituisce l'indice di riga
//	 * @return {@code Hashtable}
//	 */
//	public Hashtable<BiFunction<Grid, Cell, ArrayList<Cell>>, Function<Cell,Integer>> getMethods(){
//		Hashtable<BiFunction<Grid, Cell, ArrayList<Cell>>, Function<Cell,Integer>> t = new Hashtable<>();
//		t.put(getCellRow, c->c.getColumn());
//		t.put(getCellColumn, c->c.getRow());
//		t.put(getCellAscendingDiagonal, c->c.getColumn());
//		t.put(getCellDescendingDiagonal, c->c.getColumn());
//		return t;
//	}
//
//	
}
