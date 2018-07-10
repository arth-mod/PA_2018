package forzaQuattro;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Match {
	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;
	private final Player[] players;
	private final Grid grid;
	private int currentPlayer = PLAYER1;
	
	public Match(Player p1, Player p2) throws Exception{
		this.players = new Player[] { p1 , p2 };
		if(p1.getGrid().equals(p2.getGrid())) {
			this.grid = p1.getGrid();
		}
		else {
			throw new Exception();
		}
	}
	
	private static int otherPlayer(int pid) {
		return (pid+1)%2;
	}
	
	private void play() {
		while(this.doAction( ));
	}

	private boolean doAction() {
		try {
			this.players[this.currentPlayer].step();
			Utility.printGrid(this.grid);
		} catch (IllegalTokenLocation  | FullColumnException e) {
			System.out.println(e.getMessage());
			return true;
		} catch(WinException e) {
			Utility.printGrid(this.grid);
			System.out.println("Vittoria per "+this.players[this.currentPlayer]);
			return false;
		}
		
		this.currentPlayer=otherPlayer(this.currentPlayer);
		return true;
	}

	public static void main(String[] args) throws IllegalTokenLocation, Exception {
		Grid grid = new Grid();
		InteractivePlayer p1=new InteractivePlayer("p1", Color.RED,grid);
		InteractivePlayer p2=new InteractivePlayer("p2", Color.YELLOW,grid);
		Match match=new Match(p1, p2 );
		match.play();		
			
		}
		

	

}
