package unicam.cs.pa.forzaquattro.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unicam.cs.pa.forzaquattro.core.Cell;
import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.core.Token;
import unicam.cs.pa.forzaquattro.exceptions.WinException;

class ControllerTest {

	@Test 
	void checkTest() {
		assertThrows(WinException.class,
			()->{
				Grid grid = Grid.getInstance();
				grid.insert(new Token(Color.RED), 0);
				grid.insert(new Token(Color.RED), 3);
				grid.insert(new Token(Color.RED), 1);
				grid.insert(new Token(Color.RED), 2);
//				Controller.checkWinner(grid, c);
				}
		);
	}
	
	@Test 
	void checkTest1() {
		assertThrows(WinException.class,
			()->{
				Grid grid = Grid.getInstance();
				grid.insert(new Token(Color.RED), 0);
				grid.insert(new Token(Color.RED), 2);
				grid.insert(new Token(Color.RED), 3);
				grid.insert(new Token(Color.RED), 1);
				}
		);	
	}
}
