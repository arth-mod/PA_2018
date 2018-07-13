package sperimental;

import java.util.function.Function;

import core.Cell;

public enum Direction {
	HORIZONTAL,
	VERTICAL;
	
	public Function<Cell, Integer> getDeterminantIndex(){
		switch(this) {
		case HORIZONTAL: return (c) ->c.getColumn();
		case VERTICAL: return (c)-> c.getRow();
		}
		return null;
	}
}
