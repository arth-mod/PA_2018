package unicam.cs.pa.forzaquattro.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unicam.cs.pa.forzaquattro.core.Cell;
import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.core.Token;
import unicam.cs.pa.forzaquattro.exceptions.WinException;
import unicam.cs.pa.forzaquattro.printer.PrinterOnConsole;

class ControllerTest {
	
	Grid grid = Grid.getInstance();

	@Test 
	void checkTest() {
		assertThrows(WinException.class,
			()->{
				grid.getCell(0, 0).setToken(new Token(Color.RED));
				grid.getCell(0, 3).setToken(new Token(Color.RED));
				grid.getCell(0, 1).setToken(new Token(Color.RED));
				grid.getCell(0, 2).setToken(new Token(Color.RED));
//				grid.insert(new Token(Color.RED), 0);
//				grid.insert(new Token(Color.RED), 3);
//				grid.insert(new Token(Color.RED), 1);
//				grid.insert(new Token(Color.RED), 2);
//				Controller.checkWinner(grid, c);
				}
		);
	}
	
			
	
}
