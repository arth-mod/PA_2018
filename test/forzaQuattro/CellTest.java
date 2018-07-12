package forzaQuattro;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import core.Cell;
import core.CellStatus;
import core.Color;
import core.Token;

class CellTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void isEmpty() {
		Cell cell=new Cell(0,0);
		assertTrue(cell.getStatus() == CellStatus.EMPTY);
	}
	
	@Test
	void isFull() {
		Cell cell=new Cell(0,0);
		try {
			cell.setToken(new Token(Color.RED));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(cell.getStatus() == CellStatus.FULL);
	}
	
	@Test
	void posizione() {
		Cell cell = new Cell(3,4);
		assertTrue(cell.getRow() == 3);
		assertTrue(cell.getColumn() == 4);
	}
	

}
