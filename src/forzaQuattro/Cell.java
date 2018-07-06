package forzaQuattro;



public class Cell {

	private Token token;
	private CellStatus status;
	
	//costruttore
	public Cell() {
		this.token=null;
		this.status=CellStatus.EMPTY;
	}
	
	public CellStatus getStatus() {
		return this.status;
	}
	
	public boolean setToken(Token token) {
		if( this.getStatus() ==CellStatus.EMPTY) {
			this.token=token;
			this.status=CellStatus.FULL;
			
			return true;
		}else {
			return false;
		}
	}
	
	
	public Token getToken() {
		return this.token;
	}

 @Override
 public String toString() {
	return this.token.toString();
	 
 }

	
	
}
