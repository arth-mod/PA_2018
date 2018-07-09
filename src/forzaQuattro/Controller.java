package forzaQuattro;


import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

//	public static boolean horizontalCheck(Grid grid) {		
//		for (int i = 0; i< grid.row ; i++ ){
//			int count=0;
//			Token currentToken=new Token(Color.RED);
//	        for (int j = 0; j<grid.column; j++){
//	        	if(!grid.isFree(i, j)) {
//	        		if(currentToken.equals(grid.field[i][j].getToken())) {
//	        			
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
	
	public static ArrayList<Integer> showIndex(Grid grid, Cell cell, Function<Cell,Integer> f) {
		
		Stream<Cell> cellRow = Stream.of(grid.getCellRow(cell));
//		così otteniamo un ArrayList contenente gli indici delle Celle con il colore appena aggiunto
		ArrayList<Integer> indici = cellRow
		.filter((c) -> c.getStatus()==CellStatus.FULL)
		.filter((c) -> c.getToken().getColor() == cell.getToken().getColor())
		.map(c->f.apply(c))
		.collect(Collectors.toCollection(ArrayList<Integer>::new));
		
		return indici;
	}
	

	public static ArrayList<ArrayList<Integer>> groupConsecutiveIndex(ArrayList<Integer> indici) throws Exception{
		ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
		Iterator<Integer> t = indici.iterator();
		ArrayList<Integer> temp = new ArrayList<>();
		if(indici.size()==0) {
			throw new Exception("too small");
		}
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
	
	
	
	
	public static boolean verticalCheck(Grid grid) {		
		for (int j = 0; j< grid.column ; j++ ){
			int count=0;
			Token currentToken=new Token(Color.RED);
	        for (int i = 0; i<grid.row; i++){
	        	if(!grid.isFree(i, j)) {
	        		if(currentToken.equals(grid.field[i][j].getToken())) {
		        		count++;
		        		System.out.println(count);
		        		if(count==4)
		        		    return true;
		        	}
		        	else {
						currentToken=grid.field[i][j].getToken();
						count=1;
					}
	        	}else {
	        		count=0;
				}
	            }           
	        }
		return false;
	}

	private static int[] lowestCell(Grid grid,int row,int column) {
		int currentRow=row;
		int currentColumn=column;
		while (currentRow!=0 && currentColumn!=0) {
			currentColumn--;
			currentRow--;
		}
		int position[]= {currentRow,currentColumn};
		return (position);
	}
	
	public static boolean ascendingDiagonalCheck(Grid grid,int row,int column) {	
		int position[]=lowestCell(grid, row, column);
		System.out.println("ciaooo");
		int currentRow = position[0];
		int currentColumn = position[1];
		int count=0;
		Token currentToken=new Token(Color.RED);
		while(currentRow< grid.row && currentColumn<grid.column) {
			if(!grid.isFree(currentRow, currentColumn)) {
        		if(currentToken.equals(grid.field[currentRow][currentColumn].getToken())) {
	        		count++;
	        		System.out.println(count);
	        		if(count==4)
	        		    return true;
	        	}
	        	else {
					currentToken=grid.field[currentRow][currentColumn].getToken();
					count=1;
				}
        	}else {
        		count=0;
			}
			currentColumn++;
			currentRow++;
		}
		return false;
	}
	
	
	private static int[] lowestRightCell(Grid grid,int row,int column) {
		int currentRow=row;
		int currentColumn=column;
		while (currentRow!=0 && currentColumn!=grid.column) {
			currentColumn++;
			currentRow--;
		}
		int position[]= {currentRow,currentColumn};
		return (position);
	}
	
	
	public static boolean descendingDiagonalCheck(Grid grid,int row,int column) {	
		int position[]=lowestRightCell(grid, row, column);
		int currentRow = position[0];
		int currentColumn = position[1];
		int count=0;
		Token currentToken=new Token(Color.RED);
		while(currentRow< grid.row && currentColumn>=0) {
			if(!grid.isFree(currentRow, currentColumn)) {
        		if(currentToken.equals(grid.field[currentRow][currentColumn].getToken())) {
	        		count++;
	        		System.out.println(count);
	        		if(count==4)
	        		    return true;
	        	}
	        	else {
					currentToken=grid.field[currentRow][currentColumn].getToken();
					count=1;
				}
        	}else {
        		count=0;
			}
			currentColumn--;
			currentRow++;
		}
		return false;
	}
	
	
//	public boolean control(Grid grid,int row,int column) {
//		return (horizontalCheck(grid) || verticalCheck(grid) || ascendingDiagonalCheck(grid, row, column) || descendingDiagonalCheck(grid, row, column));
//	}
	
	
}
