package forzaQuattro;

import java.util.Scanner;



public class Match {
	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;
	private final Player[] players;
	private final Grid grid;
	private int currentPlayer = PLAYER1;
	
	public Match(Player p1, Player p2, Grid grid ) {
		this.players = new Player[] { p1 , p2 };
		this.grid = grid;
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
			
		} catch (Exception e) {
			
		}
		this.currentPlayer=otherPlayer(this.currentPlayer);
		return true;
	}

	public static void main(String[] args) {
		Grid grid = new Grid();
//		Utility.printGrid(System.out, grid);
		InteractivePlayer p1=new InteractivePlayer("p1", Color.RED,grid);
		InteractivePlayer p2=new InteractivePlayer("p2", Color.YELLOW,grid);
		Match match=new Match(p1, p2,grid );
		
		match.play();
		
			
		}
		

		
		
		
//		while(true) {
//			
//			if (input.hasNextInt()) {
//			    int numero = input.nextInt();
//			    System.out.println("Hai scritto " + numero);
//			    p1.insertToken(numero);
//			} 
//		
//			else {
//			    System.out.println("Non hai inserito un numero");
//			}
//			
//			
//		}

	

}
