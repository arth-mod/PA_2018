package unicam.cs.pa.forzaquattro.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.printer.PrinterOnConsole;

/**
 * La {@code Cell} � l'ogetto utilizzato dalla {@code Grid} per memorizzare {@code Token}.
 * Oltre al {@code Token} memorizza il suo stato e la sua posizione all'interno della {@code Grid}
 *
 */
public class Cell {
//	private Grid grid = Grid.getInstance();
	private Token token;
	private CellStatus status;
	private int row;
	private int column;
	private int[] counter;
	
	/**
	 * Costruttore di {@code Cell}
	 * @param row riga in cui � posizionata all'interno della {@code Grid}
	 * @param column colonna in cui � posizionata all'interno della {@code Grid}
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
	 * @throws IllegalTokenLocation se la cella contiene gi� un {@code Token}
	 */
	public void setToken(Token token) throws IllegalTokenLocation{
		if( this.isEmpty()) {
			this.token=token;
			this.status=CellStatus.FULL;
			for(int i=0; i<4; i++) {
				ArrayList<Cell> neighbours = Grid.getInstance().getNeighbours(this.row, this.column, Direction.fromInt(i));
				Iterator<Cell> t = neighbours.iterator();
//				neighbours.stream().filter(c -> c.getToken() == this.token).forEach(
//						c -> c.advise(i);
//						this.counter[i]++;
//						);
				System.out.println(i);
				neighbours.forEach(System.out::println);
				while(t.hasNext()) {
					Cell c = t.next();
					if(c.getToken().equals(this.token)) {
						System.out.println("uio");
						c.advise(i);
						this.counter[i]++;
					}
				}
			}
	
		}else {
			throw new IllegalTokenLocation("Cella gi� occupata");
		}
	}
	
	private void advise(int i) {
		this.counter[i]++;
		//DA FARE se contatore a 2 avvio contolli su contatori dei vicini
	}
	
	public int getCounterElement(int i) {
		return this.counter[i];
	}

	/**
	 * Accede al {@code Token} memorizzato nella cella.
	 * @return {@code Token} o null se la cella � vuota
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
