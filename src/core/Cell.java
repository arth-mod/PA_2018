package core;

import exceptions.IllegalTokenLocation;

/**
 * La {@code Cell} è l'ogetto utilizzato dalla {@code Grid} per memorizzare {@code Token}.
 * Oltre al {@code Token} memorizza il suo stato e la sua posizione all'interno della {@code Grid}
 *
 */
public class Cell {

	private Token token;
	private CellStatus status;
	private int row;
	private int column;
	
	/**
	 * Costruttore di {@code Cell}
	 * @param row riga in cui è posizionata all'interno della {@code Grid}
	 * @param column colonna in cui è posizionata all'interno della {@code Grid}
	 */
	public Cell(int row, int column) {
		this.token=null;
		this.status=CellStatus.EMPTY;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Ritorna lo stato della cella
	 * @return {@code CellStatus} EMPTY se la cella non contiene un {@code Token}, FULL altrimenti
	 */
	public CellStatus getStatus() {
		return this.status;
	}
	
	/**
	 * Inserisce un {@code Token} nella cella e ne cambia lo stato in FULL
	 * @param token da inserire
	 * @throws IllegalTokenLocation se la cella contiene già un {@code Token}
	 */
	public void setToken(Token token) throws IllegalTokenLocation{
		if( this.isEmpty()) {
			this.token=token;
			this.status=CellStatus.FULL;
		}else {
			throw new IllegalTokenLocation("Cella già occupata");
		}
	}
	
	/**
	 * Accede al {@code Token} memorizzato nella cella.
	 * @return {@code Token} o null se la cella è vuota
	 */
	public Token getToken() {
		return this.token;
	}

	/**
	 * Override del metodo {@code toString}
	 * Stampa il {@code Token} se ne contiene uno, altrimenti una stringa vuota
	 */
	@Override
	public String toString() {
		if(this.isEmpty()) {
			return " ";
		}
		return this.token.toString();
		 
	 }
	
	/**
	 * Boolean per determinare se la cella contiene un {@code Token}
	 * @return {@code true} se la vuota, {@code false} altrimenti
	 */
	public boolean isEmpty() {
		return this.getStatus() == CellStatus.EMPTY;
	}

	/**
	 * Accede al campo riga della cella
	 * @return riga
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Accede al campo colonna della cella
	 * @return colonna
	 */
	public int getColumn() {
		return this.column;
	}
	 
}
