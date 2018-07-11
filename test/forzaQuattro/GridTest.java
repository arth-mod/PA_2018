package forzaQuattro;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GridTest {

	@Test
	void grigliaVuota() {
		Grid griglia = new Grid();
		
		for(int i=0; i<griglia.getRowNumber(); i++) {
			for(int j=0; j<griglia.getColumnNumber(); j++) {
				assertTrue(griglia.isFree(i, j));
			}
		}
	}
	
	@Test
	void inserimento() {
		Grid griglia = new Grid();
		try {
			griglia.insert(new Token(Color.RED), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(griglia.getCell(0, 0));
	}
	
	@Test
	void grigliaInizializzata() {
		Grid griglia = new Grid();
		assertTrue(griglia.getCell(3, 4).getColumn() == 4);
	}
	
	@Test
	void diagonal() {
		Grid grid = new Grid();
		Cell cell = new Cell(4,4);
		System.out.println("Diagonale asc");
		grid.getCellAscendingDiagonal(cell).forEach(c->System.out.println(c.getRow()+" "+c.getColumn()));
		System.out.println("Diagonale desc");
		grid.getCellDescendingDiagonal(cell).forEach(c->System.out.println(c.getRow()+" "+c.getColumn()));
	}

}
