package core;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import exceptions.WinException;

/**
 * Effettua il controllo per decretare il vincitore.
 * Utilizzare il metodo statico {@code checkWinner} con la {@code Grid} da controllare e 
 * una {@code Cell}. Controlla se al {@code Token} contenuto nella cella di partenza, ne corrispondono 
 * altre 3 on orizzontale verticale o diagonale.
 * Utilizzare tale metodo dopo ogni inserimento.
 *
 */
public class Controller {
	
	/**
	 * Prende una {@code Grid} e una sua {@code Cell}.
	 * A seconda della {@code BiFunction line} passata, ottiene dalla griglia l'intera riga, diagonale o colonna in cui si trova al cella.
	 * Filtra le {@code Cell} vuote.
	 * Filtra le {@code Cell} di colore diverso.
	 * Restituisce un {@code ArrayList} di indici di celle dello stesso colore.
	 * @param grid
	 * @param cell
	 * @param line funzione che restituisca una linea orizzontale verticale o diagonale di celle
	 * @return ArrayList di celle dello stesso colore in riga
	 */
	private static ArrayList<Integer> showIndex(Grid grid, Cell cell, BiFunction<Grid, Cell, ArrayList<Cell>> line, Function<Cell,Integer> cIndex) {
		ArrayList<Integer> indici = 
				line.apply(grid, cell)
				.stream()
				.filter((c) -> c.getStatus()==CellStatus.FULL)
				.filter((c) -> c.getToken().getColor() == cell.getToken().getColor())
				.map(c->cIndex.apply(c))
				.collect(Collectors.toCollection(ArrayList<Integer>::new));
		
		return indici;
	}
	

	/**
	 * Dato un {@code ArrayList} di indici (risultato della funzione {@code showIndex},
	 * raggruppa gli indici consecutivi in {@code ArrayList}
	 * @param indici
	 * @return {@code ArrayList} di {@code ArrayList} di indici consecutivi
	 */
	private static ArrayList<ArrayList<Integer>> groupConsecutiveIndex(ArrayList<Integer> indici){
		ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
		Iterator<Integer> t = indici.iterator();
		ArrayList<Integer> temp = new ArrayList<>();
		assertTrue(indici.size()>0);
		temp.add(t.next());
		int i;
		while(t.hasNext()) {
			i=t.next();
			if(i == (temp.get(temp.size()-1)+1)) {
				temp.add(i);
			}
			else {
				mainList.add(temp);
				temp=new ArrayList<>();
				temp.add(i);
			}
		}
		mainList.add(temp);
		return mainList;
	}
	
	/**
	 * Controlla se la {@code Cell cell} ha decretato la vittoria.
	 * Ottiene le direzioni in cui si può vincere dalla griglia tramite il metodo {@code getMethods}.
	 * Per ognuna di queste controlla il numero di celle adiacenti con token dello stesso colore, 
	 * richiamando i metodi statici {@code groupConsecutiveIndex} e {@code showIndex} di {@code Controller}.
	 * @param grid
	 * @param cell
	 * @throws WinException se ci sono 4 token dello stesso colore in riga
	 */
	public static void checkWinner (Grid grid, Cell cell) throws WinException{
		Hashtable<BiFunction<Grid, Cell, ArrayList<Cell>>, Function<Cell,Integer>> methods = grid.getMethods();
		Iterator<BiFunction<Grid, Cell, ArrayList<Cell>>> t = methods.keySet().iterator();
		while(t.hasNext()) {
			BiFunction<Grid, Cell, ArrayList<Cell>> m = t.next();
			long g =Controller.groupConsecutiveIndex(
					Controller.showIndex(grid, cell, m, methods.get(m))
					)
					.stream()
					.map((l)->l.size())
					.filter((l)-> (l>= 4))
					.count();
						
			if(g>0) {
				throw new WinException();
			}	
		}
	}	
}
