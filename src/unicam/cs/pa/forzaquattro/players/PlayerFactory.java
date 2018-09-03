package unicam.cs.pa.forzaquattro.players;

import unicam.cs.pa.forzaquattro.core.Color;
import unicam.cs.pa.forzaquattro.core.Grid;
import unicam.cs.pa.forzaquattro.core.MatchType;
import unicam.cs.pa.forzaquattro.printer.FakePrinter;
import unicam.cs.pa.forzaquattro.printer.PrinterOnConsole;

/**
 * Factory Method. A partire da uno specifico tipo di {@code Match}, se tra giocatori fisici
 * o contro il computer, permette la creazione della coppia di giocatori necessaria.
 *
 */
public class PlayerFactory {
    
	/**
	 * Restituisce i due player per il tipo di match scelto
	 * @param type tipo di match
	 * @return coppia di Players del tipo corretto
	 */
    public Player[] getPlayer (MatchType type){
        Player players[] = new Player[2];
        switch (type){
            case ONEvsONE:
                players[0] = new InteractivePlayer("Player1", Color.RED, Grid.getInstance());
                players[1] = new InteractivePlayer("Player2", Color.YELLOW, Grid.getInstance());
                break;
            case ONEvsPC:
            	players[0] = new InteractivePlayer("Player", Color.RED, Grid.getInstance());
            	players[1] = new RandomPlayer("Computer", Color.YELLOW, Grid.getInstance(), new FakePrinter());
            	break;
            case PCvsPC:
            	players[0] = new RandomPlayer("Computer ROSSO", Color.RED, Grid.getInstance(), new FakePrinter());
            	players[1] = new RandomPlayer("Computer GIALLO", Color.YELLOW, Grid.getInstance(), new PrinterOnConsole());
            	break;
            default:
            	return null;
            	
        }
        return players;
    }
}
