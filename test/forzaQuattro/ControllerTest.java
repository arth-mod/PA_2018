package forzaQuattro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControllerTest {

	@Test
	void checkTest() {
		Grid grid = new Grid();

			grid.insert(new Token(Color.RED), 0);
			grid.insert(new Token(Color.RED), 4);
			Cell c = grid.insert(new Token(Color.RED), 2);
			
	}

}
