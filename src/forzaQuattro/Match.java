package forzaQuattro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
	
	public void play() {
		int turns = 0;
		while(this.doAction( )) {
			turns ++;
			if(turns == this.grid.getRowNumber() * this.grid.getColumnNumber()) {
				this.sendMessage(this.players[0], "Pareggio");
				this.sendMessage(this.players[1], "Pareggio");
				break;
			}
		}
	}

	private void sendMessage(Player player, String message) {
		player.getOutput().println(player+": "+message);
		
	}

	private boolean doAction() {
		try {
			this.players[this.currentPlayer].step();
			Utility.printGrid(this.players[this.currentPlayer].getOutput(), this.grid);
		} catch (IllegalTokenLocation  | FullColumnException e) {
//			System.out.println(e.getMessage());
			this.sendMessage(this.players[this.currentPlayer], e.getMessage());
			return true;
		} catch(WinException e) {
			Utility.printGrid(this.grid);
//			System.out.println("Vittoria per "+this.players[this.currentPlayer]);
			this.sendMessage(this.players[this.currentPlayer], "Hai vinto!");
			this.sendMessage(this.players[otherPlayer(this.currentPlayer)], "Hai perso!");
			return false;
		}
		this.currentPlayer=otherPlayer(this.currentPlayer);
		return true;
	}

	public static void main(String[] args) throws IllegalTokenLocation, Exception {
		Grid grid = new Grid();
		InteractivePlayer p1=new InteractivePlayer("p1", Color.RED,grid);
		InteractivePlayer p2=new InteractivePlayer("p2", Color.YELLOW,grid);
		
		RandomPlayer rp1 = new RandomPlayer("rp", Color.YELLOW, grid);
		RandomPlayer rp2 = new RandomPlayer("rp", Color.RED, grid);
		
		Match match=new Match(rp1, rp2 );
		//TO_DO scegliere tipologia di math (contro pc o contro altro utente)
		//enum tipologia match
		//inserimento nomi utenti
		match.play();		
			
		}
}
