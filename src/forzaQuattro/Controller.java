package forzaQuattro;


import java.util.*;
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
	
	public static boolean horizontalCheck(Grid grid, Cell cell) {
		int c =0;
		Stream<Cell> cellRow = Stream.of(grid.getCellRow(cell));
		List <List<Cell>> g = cellRow
		.filter((c) -> c.getStatus()==CellStatus.FULL)
		.filter((c) -> c.getToken().getColor() == cell.getToken().getColor())
		.collect(
				(Supplier<List<List<Cell>>>) ArrayList::new,
				(sequences, currentCol) ->{
					if(sequences.size()==0 || !areHorizontalAdj(getLast(getLast(sequences)), currentCell)) {
						sequences.add(new ArrayList<>());
					}
					getLast(sequences).add(currentCell);
				},
				List::addAll
				);
//		int row = cell.getRow();
//		int count=0;
//		Token currentToken=new Token(Color.RED);
//        for (int j = 0; j<grid.column; j++){
//        	if(!grid.isFree(row, j)) {
//        		if(currentToken.equals(grid.field[row][j].getToken())) {
//	        		count++;
//	        		System.out.println(count);
//	        		if(count==4)
//	        		    return true;
//	        	}
//	        	else {
//					currentToken=grid.field[row][j].getToken();
//					count=1;
//				}
//        	}else {
//        		count=0;
//			}
//          }           
//		return false;
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
	
	
	public boolean control(Grid grid,int row,int column) {
		return (horizontalCheck(grid) || verticalCheck(grid) || ascendingDiagonalCheck(grid, row, column) || descendingDiagonalCheck(grid, row, column));
	}
	
	
}
