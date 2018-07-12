package forzaQuattro;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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
//		Function<Cell,Integer> cIndex;
//		if(line.equals(Grid.getCellColumn)) {
//			cIndex = (c)->c.getRow();
//		}
//		else {
//			cIndex = (c)->c.getColumn();
//		}
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
//	public static void check (Grid grid, Cell cell) throws WinException{
//		ArrayList<BiFunction<Grid, Cell, ArrayList<Cell>>> methods = grid.getMethods();
//		Iterator<BiFunction<Grid, Cell, ArrayList<Cell>>> t = methods.iterator();
//		while(t.hasNext()) {
//			long g =Controller.groupConsecutiveIndex(
//					Controller.showIndex(grid, cell, t.next())
//					)
//					.stream()
//					.map((l)->l.size())
//					.filter((l)-> (l>=4))
//					.count();
//						
//			if(g>0) {
//				throw new WinException();
//			}	
//		}
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
		
		
//		ArrayList<ArrayList<Integer>> horizontal = Controller.groupConsecutiveIndex(Controller.showIndex(grid, cell, Grid.getCellRow, (c)->c.getColumn()));
//		Long hResult = horizontal.stream().map((l)->l.size()).filter((l)-> (l>=4)).count();
//		if(hResult>0) {
//			throw new WinException();
//		}
//		
//		ArrayList<ArrayList<Integer>> vertical = Controller.groupConsecutiveIndex(Controller.showIndex(grid, cell, (g,c)->g.getCellColumn(c), (c)->c.getRow()));
//		Long vResult = vertical.stream().map((l)->l.size()).filter((l)-> (l>=4)).count();
//		if(vResult>0) {
//			throw new WinException();
//		}
//		
//		ArrayList<ArrayList<Integer>> ascDiagonal = Controller.groupConsecutiveIndex(Controller.showIndex(grid, cell, (g,c)->g.getCellAscendingDiagonal(c), (c)->c.getColumn()));
//		Long ascResult = ascDiagonal.stream().map((l)->l.size()).filter((l)-> (l>=4)).count();
//		if(ascResult>0) {
//			throw new WinException();
//		}
//		
//		ArrayList<ArrayList<Integer>> descDiagonal = Controller.groupConsecutiveIndex(Controller.showIndex(grid, cell, (g,c)->g.getCellDescendingDiagonal(c), (c)->c.getColumn()));
//		Long descResult = descDiagonal.stream().map((l)->l.size()).filter((l)-> (l>=4)).count();
//		if(descResult>0) {
//			throw new WinException();
//		}
		
	}
	
//	public static void check (Grid grid, Cell cell) throws WinException{
//		check(grid, cell, 4);
//	}
	
//	public static boolean horizontalCheck(Grid grid) {		
//	for (int i = 0; i< grid.row ; i++ ){
//		int count=0;
//		Token currentToken=new Token(Color.RED);
//        for (int j = 0; j<grid.column; j++){
//        	if(!grid.isFree(i, j)) {
//        		if(currentToken.equals(grid.field[i][j].getToken())) {
//        			
//	        		count++;
//	        		System.out.println(count);
//	        		if(count==4)
//	        		    return true;
//	        	}
//	        	else {
//					currentToken=grid.field[i][j].getToken();
//					count=1;
//				}
//        	}else {
//        		count=0;
//			}
//            }           
//        }
//	return false;
//}

	
//	public static boolean verticalCheck(Grid grid) {		
//		for (int j = 0; j< grid.column ; j++ ){
//			int count=0;
//			Token currentToken=new Token(Color.RED);
//	        for (int i = 0; i<grid.row; i++){
//	        	if(!grid.isFree(i, j)) {
//	        		if(currentToken.equals(grid.field[i][j].getToken())) {
//		        		count++;
//		        		System.out.println(count);
//		        		if(count==4)
//		        		    return true;
//		        	}
//		        	else {
//						currentToken=grid.field[i][j].getToken();
//						count=1;
//					}
//	        	}else {
//	        		count=0;
//				}
//	            }           
//	        }
//		return false;
//	}
//
//	private static int[] lowestCell(Grid grid,int row,int column) {
//		int currentRow=row;
//		int currentColumn=column;
//		while (currentRow!=0 && currentColumn!=0) {
//			currentColumn--;
//			currentRow--;
//		}
//		int position[]= {currentRow,currentColumn};
//		return (position);
//	}
//	
//	public static boolean ascendingDiagonalCheck(Grid grid,int row,int column) {	
//		int position[]=lowestCell(grid, row, column);
//		System.out.println("ciaooo");
//		int currentRow = position[0];
//		int currentColumn = position[1];
//		int count=0;
//		Token currentToken=new Token(Color.RED);
//		while(currentRow< grid.row && currentColumn<grid.column) {
//			if(!grid.isFree(currentRow, currentColumn)) {
//        		if(currentToken.equals(grid.field[currentRow][currentColumn].getToken())) {
//	        		count++;
//	        		System.out.println(count);
//	        		if(count==4)
//	        		    return true;
//	        	}
//	        	else {
//					currentToken=grid.field[currentRow][currentColumn].getToken();
//					count=1;
//				}
//        	}else {
//        		count=0;
//			}
//			currentColumn++;
//			currentRow++;
//		}
//		return false;
//	}
//	
//	
//	private static int[] lowestRightCell(Grid grid,int row,int column) {
//		int currentRow=row;
//		int currentColumn=column;
//		while (currentRow!=0 && currentColumn!=grid.column) {
//			currentColumn++;
//			currentRow--;
//		}
//		int position[]= {currentRow,currentColumn};
//		return (position);
//	}
//	
//	
//	public static boolean descendingDiagonalCheck(Grid grid,int row,int column) {	
//		int position[]=lowestRightCell(grid, row, column);
//		int currentRow = position[0];
//		int currentColumn = position[1];
//		int count=0;
//		Token currentToken=new Token(Color.RED);
//		while(currentRow< grid.row && currentColumn>=0) {
//			if(!grid.isFree(currentRow, currentColumn)) {
//        		if(currentToken.equals(grid.field[currentRow][currentColumn].getToken())) {
//	        		count++;
//	        		System.out.println(count);
//	        		if(count==4)
//	        		    return true;
//	        	}
//	        	else {
//					currentToken=grid.field[currentRow][currentColumn].getToken();
//					count=1;
//				}
//        	}else {
//        		count=0;
//			}
//			currentColumn--;
//			currentRow++;
//		}
//		return false;
//	}
//	
	
//	public boolean control(Grid grid,int row,int column) {
//		return (horizontalCheck(grid) || verticalCheck(grid) || ascendingDiagonalCheck(grid, row, column) || descendingDiagonalCheck(grid, row, column));
//	}
	
	
}
