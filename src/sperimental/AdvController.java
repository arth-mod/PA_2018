package sperimental;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import core.Cell;
import core.CellStatus;
import core.Color;
import core.Grid;
import core.Token;
import exceptions.FullColumnException;
import exceptions.IllegalTokenLocation;
import exceptions.WinException;

public class AdvController {
	
	
	private static ArrayList<Cell> showCellInLine(Grid grid, Cell cell, BiFunction<Grid, Cell, ArrayList<Cell>> line) {
		ArrayList<Cell> cellInLine = 
				line.apply(grid, cell)
				.stream()
				.filter((c) -> c.getStatus()==CellStatus.FULL)
				.filter((c) -> c.getToken().getColor() == cell.getToken().getColor())
//				.map(c->cIndex.apply(c))
				.collect(Collectors.toCollection(ArrayList<Cell>::new));
		
		return cellInLine;
	}
	
	
	private static ArrayList<ArrayList<Cell>> groupConsecutiveCell(ArrayList<Cell> indici, Direction dir){
		ArrayList<ArrayList<Cell>> mainList = new ArrayList<>();
		Iterator<Cell> t = indici.iterator();
		ArrayList<Cell> temp = new ArrayList<>();
//		assertTrue(indici.size()>0);
		temp.add(t.next());
		Cell i;
		while(t.hasNext()) {
			i=t.next();
			if(dir.getDeterminantIndex().apply(i) == dir.getDeterminantIndex().apply(temp.get(temp.size()-1))+1) {
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
	
	public static void find(Grid grid, Cell cell) {
		Hashtable<BiFunction<Grid, Cell, ArrayList<Cell>>, Direction> methods = Grid.getInstance().getMethodsAndDirections();
		Iterator<BiFunction<Grid, Cell, ArrayList<Cell>>> t = methods.keySet().iterator();
		BiFunction<Grid, Cell, ArrayList<Cell>> m;
		while(t.hasNext()) {
			m = t.next();
			groupConsecutiveCell(
					showCellInLine(grid, cell, m), methods.get(m)
			)
			.stream()
			.filter(l -> l.size()>3)
			.forEach(System.out::println);
			;
		}
	}
	
	
	public static void main(String[] args) {
		Grid grid = Grid.getInstance();
		try {
			
			grid.insert(new Token(Color.RED), 0);
			grid.insert(new Token(Color.YELLOW), 0);
			Cell cell = grid.insert(new Token(Color.RED), 1);
			
			find(grid, cell);
		} catch (IllegalTokenLocation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FullColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
//	public static void checkWinner (Grid grid, Cell cell) throws WinException{
//		Hashtable<BiFunction<Grid, Cell, ArrayList<Cell>>, Function<Cell,Integer>> methods = grid.getMethods();
//		Iterator<BiFunction<Grid, Cell, ArrayList<Cell>>> t = methods.keySet().iterator();
//		while(t.hasNext()) {
//			BiFunction<Grid, Cell, ArrayList<Cell>> m = t.next();
//			long g =AdvController.groupConsecutiveIndex(
//					AdvController.showIndex(grid, cell, m, methods.get(m))
//					)
//					.stream()
//					.map((l)->l.size())
//					.filter((l)-> (l>= 4))
//					.count();
//						
//			if(g>0) {
//				throw new WinException();
//			}	
//		}
//	}
}
