package unicam.cs.pa.forzaquattro.core;

import unicam.cs.pa.forzaquattro.players.PlayerFactory;

/**
 * 
 * Main da eseguire per iniziare a giocare
 */
public class Main {

	public static void main(String[] args) {
//		Match.arrangeGrid();
		
		GridFactory gf = new GridFactory();
		GridType gt = Match.arrangeGridType();
		Grid grid = gf.getGrid(gt);
		
		MatchType type = Match.arrangeMatchType();
		Match match = new Match(type, new PlayerFactory());
		match.play();
	}

}
