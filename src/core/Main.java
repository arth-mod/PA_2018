package core;

import java.util.Scanner;
import players.PlayerFactory;

/**
 * 
 * Main da eseguire per iniziare a giocare
 */
public class Main {

	public static void main(String[] args) {
		Match.arrangeGrid();
		MatchType type = Match.arrangeMatchType();
		Match match = new Match(type, new PlayerFactory());
		match.play();
	}

}
