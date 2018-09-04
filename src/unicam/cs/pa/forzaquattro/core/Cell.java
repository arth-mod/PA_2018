package unicam.cs.pa.forzaquattro.core;

import java.util.ArrayList;
import java.util.Iterator;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.exceptions.WinException;

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
	private int[] counter; 
	
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
		this.counter = new int[4];
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
	public void setToken(Token token) throws IllegalTokenLocation, WinException{
		if( this.isEmpty()) {
			this.token=token;
			this.status=CellStatus.FULL;
			
			this.checkNeighbours();
		}else {
			throw new IllegalTokenLocation("Cella già occupata");
		}
	}
	
	/**
	 * Controlla le celle adiancenti nelle 4 direzioni. Aumenta il contatore della direzione corrispondente se
	 * trova celle contenenti lo stesso token. Infine procede all'aggiornamento delle celle individuate
	 * @throws WinException se si riscontrano 4 celle in fila
	 */
	private void checkNeighbours() throws WinException{ 
		for(Direction direction : Direction.values()) {
			ArrayList<Cell> neighbours = Grid.getInstance().getNeighbours(this.row, this.column, direction);
			Iterator<Cell> t = neighbours.iterator();
			//controlla le celle vicine e aumenta il proprio contatore
			while(t.hasNext()) {
				Cell c = t.next();
				if(c.getToken().equals(this.token)) {
					this.counter[direction.value]++;
//					c.advise(i);
				}
			}
			//aumenta contatori delle celle vicine
			adviseNeighbours(direction);
		}
	}
	
	/**
	 * Permette di aggiornare i contatori delle celle vicine a quella considerata. Viene chiamato dal metodo
	 * {@code checkNeighbours()} invocato dopo ogni inserimento
	 * @param direction direzione in cui considerare le celle 
	 * @throws WinException se si riscontrano 4 celle in fila
	 */
	private void adviseNeighbours(Direction direction) throws WinException{ 
			ArrayList<Cell> neighbours = Grid.getInstance().getNeighbours(this.row, this.column, direction);
			Iterator<Cell> t = neighbours.iterator();
			while(t.hasNext()) {
				Cell c = t.next();
				if(c.getToken().equals(this.token)) {
					c.advise(direction);
				}
			}
	}

	/**
	 * Metodo privato della cella. Aumenta il contatore della cella corrispondente alla direzione passata.
	 * @param direction indica quale contatore aumentare
	 * @throws WinException se si riscontrano 4 celle in fila 
	 */
	private void advise(Direction direction) throws WinException { 
		this.counter[direction.value]++;
		if(this.counter[direction.value] > 1) {
			ArrayList<Cell> neighbours = Grid.getInstance().getNeighbours(this.row, this.column, direction);
			Iterator<Cell> t = neighbours.iterator();
			while(t.hasNext()) {
				Cell c = t.next();
				if(c.getCounterElement(direction.value) > 1) {
					throw new WinException();
				}
			}
		}
	}
	
	/**
	 * Accessore al contatore della cella
	 * @param i indice del contatore
	 * @return valore in posizione i
	 */
	public int getCounterElement(int i) {
		return this.counter[i];
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
