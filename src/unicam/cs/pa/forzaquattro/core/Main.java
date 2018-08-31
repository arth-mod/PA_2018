package unicam.cs.pa.forzaquattro.core;

import unicam.cs.pa.forzaquattro.players.PlayerFactory;

/**
 * 
 * Main da eseguire per iniziare a giocare
 */
public class Main {

	public static void main(String[] args) {
//		Match.arrangeGrid();
		
		GridFactory gridFactory = new GridFactory();
		GridType gridType = Match.arrangeGridType();
		Grid grid = gridFactory.getGrid(gridType);
		
		MatchType type = Match.arrangeMatchType();
		Match match = new Match(type, new PlayerFactory());
		match.play();
	}

}
