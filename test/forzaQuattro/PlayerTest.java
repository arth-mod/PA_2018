package forzaQuattro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Cell;
import core.Color;
import core.Grid;
import core.Token;
import exceptions.FullColumnException;
import exceptions.IllegalTokenLocation;
import players.AdvancedRandomPlayer;
import players.Player;

class PlayerTest {

	@Test
	void testThreeCheck() {
		try {
			Cell c = Grid.getInstance().insert(new Token(Color.RED), 0);
			Grid.getInstance().insert(new Token(Color.RED), 1);
			c = Grid.getInstance().insert(new Token(Color.RED), 0);
			AdvancedRandomPlayer p = new AdvancedRandomPlayer("ap", Color.RED, Grid.getInstance());
			
			p.checkThree(Grid.getInstance(), c);
		} catch (IllegalTokenLocation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FullColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
