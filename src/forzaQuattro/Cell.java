package forzaQuattro;



public class Cell {

	private Token token;
	private CellStatus status;
	private int row;
	private int column;
	
	//costruttore
	public Cell(int row, int column) {
		this.token=null;
		this.status=CellStatus.EMPTY;
		this.row = row;
		this.column = column;
	}
	
	public CellStatus getStatus() {
		return this.status;
	}
	
	public void setToken(Token token) throws IllegalTokenLocation{
		if( this.getStatus() ==CellStatus.EMPTY) {
			this.token=token;
			this.status=CellStatus.FULL;
		}else {
			throw new IllegalTokenLocation("Cella già occupata");
		}
	}
	
	
	public Token getToken() {
		return this.token;
	}

	 @Override
	 public String toString() {
		return this.token.toString()/*+ " "+this.getRow()+" "+this.getColumn()*/;
		 
	 }
	 
	 public int getRow() {
		 return this.row;
	 }
	 
	 public int getColumn() {
		 return this.column;
	 }
	 
//	 public int[] getPosition() {
//		 int position[] = {this.getRow() , this.getColumn()};
//		 return position;
//	 }
//	 
	
	
}
