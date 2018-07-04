package forzaQuattro;



public class Cell {

	private Token token;
	
	
	//costruttore
	public Cell() {
		
	}
	
	public CellStatus getStatus() {
		if(this.token==null) {
			return CellStatus.EMPTY;
		}else {
			return CellStatus.FULL;
		}
	}
	
	public boolean setToken(Token token) {
		if( this.token !=null) {
			return false;
		}else {
			this.token=token;
			return true;
		}
	}
	
	
	public Color getToken() {
		return this.token.getColor();
	}
	
	
	
	
}
