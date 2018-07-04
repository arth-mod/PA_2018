package forzaQuattro;

public class Match {
	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;
	private final Player[] players;
	private final Grid grid;
	private int currentPlayer = PLAYER1;
	
	public Match(Player p1, Player p2, int row, int column ) {
		this.players = new Player[] { p1 , p2 };
		this.grid = new Grid(row, column);
	}
	
	public Match(Player p1, Player p2) {
		this(p1, p2, Grid.DEFAULT_ROW, Grid.DEFAULT_COLUMN);
	}
	
	private static int otherPlayer(int pid) {
		return (pid+1)%2;
	}

	
	public static void main(String[] args) {
		Grid grid = new Grid();
		Utility.printGrid(System.out, grid);

	}

}
