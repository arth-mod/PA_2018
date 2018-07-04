package forzaQuattro;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CellTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void isEmpty() {
		Cell cell=new Cell();
		assertTrue(cell.getStatus() == CellStatus.EMPTY);
	}
	
	@Test
	void isFull() {
		Cell cell=new Cell();
		cell.setToken(new Token(Color.RED, 0));
		assertTrue(cell.getStatus() == CellStatus.FULL);
	}
	

}
