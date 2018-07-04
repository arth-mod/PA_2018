import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import forzaQuattro.Cell;
import forzaQuattro.CellStatus;
import forzaQuattro.Token;

class CellTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void isEmpty() {
		Cell cell=new Cell();
		assertTrue(cell.getStatus()==CellStatus.EMPTY);
	}
	
	@Test
	void isFull() {
		Cell cell=new Cell();
		assertTrue(cell.getStatus()==CellStatus.FULL);
	}
	

}
