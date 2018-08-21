package unicam.cs.pa.forzaquattro.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.Test;

import unicam.cs.pa.forzaquattro.core.Cell;
import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.core.Token;
import unicam.cs.pa.forzaquattro.exceptions.FullColumnException;

class GridTest {
	Grid grid = Grid.getInstance();
	
	
	@Test
	void grigliaInizializzata() {
		Random r = new Random();
		int row = r.nextInt(6);
		int col = r.nextInt(7);
		
		assertTrue(grid.getCell(row, col).getColumn() == col);
		assertTrue(grid.getCell(row, col).getRow() == row);
	}
	
	
	@Test
	void inserimento() {
		
		try {
			grid.insert(new Token(Color.RED), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertFalse(grid.isFree(0, 0));
	}
	

	
	@Test
	void fullColumn(){
		assertThrows(FullColumnException.class,
				()-> {
					int i =0;
			while(true) {
				grid.insert(new Token(Color.RED), 2);
				i++;
				assertTrue(i<=grid.getRowNumber());
			}
				}
		);
	}
	
	@Test
	void diagonal() {
		Grid grid = Grid.getInstance();
		Cell cell = new Cell(4,4);
		System.out.println("Diagonale asc");
		grid.getCellAscendingDiagonal(cell).forEach(c->System.out.println(c.getRow()+" "+c.getColumn()));
		System.out.println("Diagonale desc");
		grid.getCellDescendingDiagonal(cell).forEach(c->System.out.println(c.getRow()+" "+c.getColumn()));
	}
}
