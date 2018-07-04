package forzaQuattro;

public class Grid {
	
	public static final int DEFAULT_ROW = 6;
	public static final int DEFAULT_COLUMN = 7;
	public final Token[][] field;
	public int row;
	public int column;
	
	public Grid(int row, int column) {
		this.field = new Token[row][column];
		this.row = row;
		this.column=column;
	}
	
	public Grid() {
		this(DEFAULT_ROW, DEFAULT_COLUMN);
	}
	
	public boolean insert(Token token, int column) {
		int row = this.getRow(column);
		if(this.field[row][column] == null) {
			this.field[row][column] = token;
			return true;
		}
		return false;
		
	}

	private int getRow(int column) {
		for(int i=0; i<this.row; i++) {
			if(this.field[i][column] == null) {
				return i;
			}
		}
		System.out.println("colonna piena!!");
		//DOVREBBE SOLLEVARE ECCEZIONE
		return(10000000);
	}
	
	public void autoPrint() {
		
	}

	public boolean isFree(int i, int j) {
		return this.field[i][j] == null;
	}
}
