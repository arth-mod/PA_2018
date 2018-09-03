package unicam.cs.pa.forzaquattro.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import unicam.cs.pa.forzaquattro.core.Cell;
import unicam.cs.pa.forzaquattro.core.CellStatus;
import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.core.Token;
import unicam.cs.pa.forzaquattro.exceptions.FullColumnException;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.exceptions.WinException;
import unicam.cs.pa.forzaquattro.printer.PrinterOnConsole;

class CellTest {

	
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
	
	@Test
	void checkCounter() {
		Grid grid = Grid.getInstance();
		try {
			Cell c1= Grid.getInstance().getCell(0, 1);
			Cell c2 = Grid.getInstance().getCell(1, 1);
			Cell c3 = Grid.getInstance().getCell(0, 3);
			Cell c = Grid.getInstance().getCell(0, 2);
			c1.setToken(new Token(Color.RED));
			c2.setToken(new Token(Color.RED));
			c3.setToken(new Token(Color.RED));
			c.setToken(new Token(Color.RED));
			new PrinterOnConsole().printGrid();
			assertTrue(c.getCounterElement(0) == 0); //hor
			assertTrue(c.getCounterElement(1) == 2); //vert
			assertTrue(c.getCounterElement(2) == 0); //diag
			assertTrue(c.getCounterElement(3) == 1); //antidiag
		} catch (IllegalTokenLocation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WinException e) {
			e.printStackTrace();
		}
		
	}
	

}
