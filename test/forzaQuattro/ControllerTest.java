package forzaQuattro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Cell;
import core.Color;
import core.Controller;
import core.Grid;
import core.Token;
import exceptions.WinException;

class ControllerTest {

	@Test 
	void checkTest() {
		assertThrows(WinException.class,
			()->{
				Grid grid = new Grid();
				grid.insert(new Token(Color.RED), 0);
				grid.insert(new Token(Color.RED), 3);
				grid.insert(new Token(Color.RED), 1);
				Cell c = grid.insert(new Token(Color.RED), 2);
				Controller.check(grid, c);
				}
		);	
	}
}
