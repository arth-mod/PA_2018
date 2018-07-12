package forzaQuattro;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import core.Cell;
import core.Color;
import core.Grid;
import core.Token;

class GridTest {
	
	@Test
	void inserimento() {
		Grid griglia = Grid.getInstance();
		try {
			griglia.insert(new Token(Color.RED), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertFalse(griglia.isFree(0, 0));
	}
	
	@Test
	void grigliaInizializzata() {
		Grid griglia = Grid.getInstance();
		assertTrue(griglia.getCell(3, 4).getColumn() == 4);
	}
	
	@Test
	void diagonal() {
		Grid grid = Grid.getInstance();
		Cell cell = new Cell(4,4);
		System.out.println("Diagonale asc");
		grid.getCellAscendingDiagonal(cell).forEach(c->System.out.println(c.getRow()+" "+c.getColumn()));
		System.out.println("Diagonale desc");
		grid.getCellDescendingDiagonal(cell).forEach(c->System.out.println(c.getRow()+" "+c.getColumn()));
	}

	@Test
	void getLinesOfEmptyGrid() {
		Grid grid = Grid.getInstance();
//		long c = 
			grid.getMethods()
			.keySet()
			.stream()
			.map((getLineFunction) -> getLineFunction.apply(grid, new Cell(0,0)))
			.collect(Collectors.toSet())
			.forEach((line)->line//.forEach((cell) -> grid.isFree(cell.getRow(), cell.getColumn())));
			
					.stream()
					.filter(cell -> grid.isFree(cell.getRow(), cell.getColumn()))
			)
					;
//			.filter((line)-> line.size()>0)
//			.count();
//		assertTrue(c==0);
	}
}
