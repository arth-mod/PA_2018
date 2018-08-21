package unicam.cs.pa.forzaquattro.core;

import java.util.*;

import unicam.cs.pa.forzaquattro.exceptions.FullColumnException;
import unicam.cs.pa.forzaquattro.exceptions.IllegalTokenLocation;
import unicam.cs.pa.forzaquattro.exceptions.WinException;
import unicam.cs.pa.forzaquattro.players.Player;
import unicam.cs.pa.forzaquattro.players.PlayerFactory;

/**
 * Gestore della partita. Memorizza la griglia di gioco e i due giocatori, 
 * tiene traccia del giocatore corrente e cambia giocatore quando l'attuale ha eseguito la sua mossa. 
 * Utilizzare il metodo {@core match.play()} per avviare la partita.
 */
public class Match {
	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;
	private final Player[] players;
	private final Grid grid;
	private int currentPlayer = PLAYER1;
	
	
	/**
	 * Costruttore di {@core Match}.
	 * @param type tipo della partita
	 * @param factory costruttore di {@core Player}
	 */
	public Match(MatchType type, PlayerFactory factory) {
		this.players = factory.getPlayer(type);
		this.grid = Grid.getInstance();
	}
	
	/**
	 * Dato un intero rappresentate un {@core Player}, restituisce l'altro
	 * @param pid
	 * @return
	 */
	private static int otherPlayer(int pid) {
		return (pid+1)%2;
	}
	
	/**
	 * Metodo core del Match. Fa eseguire operazioni ai giocatori. 
	 * Tiene traccia di quante giocate sono state fatte. 
	 * Termina in caso di vittoria di uno dei due o pareggio.
	 */
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

	/**
	 * Invia un messaggio al Player utilizzando il suo PrintStream.
	 * @param player giocatore a cui inviare il messaggio
	 * @param message Stringa da inviare
	 */
	private void sendMessage(Player player, String message) {
		player.getOutput().println(player+": "+message);
		
	}

	/**
	 * Fa eseguire operazioni al Player corrente.
	 * Esegue lo switch del Player in caso quello corrente abbia correttamente eseguito la sua mossa
	 * @return true se la mossa è andata a buon fine o va ripetuta, false in caso di vittoria
	 */
	private boolean doAction() {
		try {
			this.players[this.currentPlayer].step();
			Utility.printGrid(this.players[this.currentPlayer].getOutput(), this.grid);
		} catch (IllegalTokenLocation  | FullColumnException e) {
			this.sendMessage(this.players[this.currentPlayer], e.getMessage());
			return true;
		} catch(WinException e) {
			Utility.printGrid(this.grid);
			this.sendMessage(this.players[this.currentPlayer], "Hai vinto!");
			this.sendMessage(this.players[otherPlayer(this.currentPlayer)], "Hai perso!");
			return false;
		}
		this.currentPlayer=otherPlayer(this.currentPlayer);
		return true;
	}
	
	/**
	 * Inserimento di valori personalizzati per la griglia di gioco. 
	 * In caso di valori errati, la griglia viene istanziata con valori di default
	 */
	public static void arrangeGrid() {
		Scanner input = new Scanner(System.in);
		System.out .print("Numero di righe desiderato (enter per valori di default): ");
		try {
			String row = input.nextLine();
			int r = Integer.parseInt(row);
			System.out .print("Numero di colonne desiderato: ");
			String column = input.nextLine();
			int c = Integer.parseInt(column);
			if(r<4 || c<4) {
				throw new NumberFormatException();
			}
			System.out.println("Griglia inizializzata con valori "+r+"x"+c);
			Grid.init(r, c);
		} catch (NumberFormatException e) {
			System.out .println("Griglia istanziata con valori di default 6x7");
			Grid.getInstance();
		}
	}


	/**
	 * Da richiamare per chiedere all'utente il tipo di partita desiderato.
	 * Input vuoto o errato genera partita di default 1 contro 1.
	 * @return MatchType della partita scelta
	 */
	public static MatchType arrangeMatchType() {
		System.out.println("TIPOLOGIA DI PARTITA:\n0 per 1 contro 1\n1 per 1 contro PC\n2 per battaglia tra PC");
		Scanner in = new Scanner(System.in);
		try {
			return MatchType.fromInt(Integer.parseInt(in.nextLine()));
		} catch (NumberFormatException e) {
			return MatchType.fromInt(0);
		}
	}
}
