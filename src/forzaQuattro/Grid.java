package forzaQuattro;





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
	
	public boolean insert(Token token, int column) throws IllegalTokenLocation{
		int row = this.getRow(column);
		return(this.field[row][column].setToken(token));
	}

	private int getRow(int column) throws IllegalTokenLocation{
		int i;
		for(i=0; i<this.row; i++) {
			if(isFree(i, column) ) {
				return i;
			}
		}
//		System.out.println("colonna piena!!");
		throw new IllegalTokenLocation();
		//DOVREBBE SOLLEVARE ECCEZIONE
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

	public Cell[] getCellRow(Cell cell) {
		return this.field[cell.getRow()];
	}
}
