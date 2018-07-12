package forzaQuattro;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import core.Cell;
import core.Color;
import core.Grid;
import core.Token;

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
		assertFalse(griglia.isFree(0, 0));
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

	@Test
	void getLinesOfEmptyGrif() {
		Grid grid = new Grid();
//		long c = 
			grid.getMethods()
			.keySet()
			.stream()
			.map((getLineFunction) -> getLineFunction.apply(grid, new Cell(0,0)))
			.collect(Collectors.toSet())
			.forEach(System.out::print);
			;
//			.filter((line)-> line.size()>0)
//			.count();
//		assertTrue(c==0);
	}
}
