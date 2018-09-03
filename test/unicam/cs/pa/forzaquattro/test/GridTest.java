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
		
//		for(int i = 0; i<col; i++) {
//			assertTrue(grid.counter[i] == 0);
//		}
		
	}
	
	
	@Test
	void inserimento() {
		
		try {
			grid.getCell(0, 1).setToken(new Token(Color.RED));
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertFalse(grid.isFree(0, 1));
	}
	
	
}
