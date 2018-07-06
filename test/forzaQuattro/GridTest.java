package forzaQuattro;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GridTest {

	@Test
	void grigliaVuota() {
		Grid griglia = new Grid();
		
		for(int i=0; i<griglia.row; i++) {
			for(int j=0; j<griglia.column; j++) {
				assertTrue(griglia.isFree(i, j));
			}
		}
	}
	
	@Test
	void inserimento() {
		Grid griglia = new Grid();
		try {
			assertTrue(griglia.insert(new Token(Color.RED), 0));
		} catch (IllegalTokenLocation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(griglia.field[0][0]);
	}

}
