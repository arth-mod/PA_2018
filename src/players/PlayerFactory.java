package players;

import core.Color;
import core.Grid;

public class PlayerFactory {
    
    public Player[] getPlayer ( MatchType type){
        Player players[] = new Player[2];
        switch (type){
            case ONEvsONE:
                players[0] = new InteractivePlayer("Player1", Color.RED, Grid.getInstance());
                players[1] = new InteractivePlayer("Player2", Color.YELLOW, Grid.getInstance());
                return players;
            case ONEvsPC:
            	players[0] = new InteractivePlayer("Player1", Color.RED, Grid.getInstance());
            	players[1] = new RandomPlayer("Computer", Color.YELLOW, Grid.getInstance());
            	return players;
            case PCvsPC:
            	players[0] = new RandomPlayer("Computer", Color.RED, Grid.getInstance());
            	players[1] = new RandomPlayer("Computer", Color.YELLOW, Grid.getInstance());
            	return players;
            default:
            	return null;
            	
        }
    }
}
