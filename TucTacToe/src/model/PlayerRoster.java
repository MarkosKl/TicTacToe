package model;

import java.io.Serializable;

import javax.swing.JOptionPane;

import control.GameController;

public class PlayerRoster implements Serializable{

	private static final long serialVersionUID = 1L;
	private String[] players;
	private GameController gc = new GameController();
	private int numOfPlayers;
	
	public PlayerRoster() {
		players = new String[15];
		players[0]="Vasilis";
		players[1]="Nektarios";
		players[2]="Yannis";
		players[3]="Eleni";
		players[4] = "Hal";
		players[5] = "MrBean";
		this.numOfPlayers=6;
	}
	
	public String getPlayer(int i) {
		if (i<0 || i>players.length) {
			return null;
		}
		return players[i];
	}
	
	public String[] getPlayers() {
		return players;
	}
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}



	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}


	public void setPlayerString(String s) {
		int counter = this.countArrayElements(getPlayers());
		this.players[counter] = players[counter];
	}



	
	public int addPlayer() {
		String playerName;
		int counter = 0;
		if(this.countArrayElements(getPlayers()) < 15) {
			counter = this.countArrayElements(getPlayers());
		}
		if (counter==-1) {
			JOptionPane.showMessageDialog(gc.getView(),
					"15 Players already exist",
					"Ooops...",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		
		playerName = JOptionPane.showInputDialog("Enter Player's Name");
		if(playerName.length() > 20) {
			JOptionPane.showMessageDialog(gc.getView(),
					"PlayerName is too long",
					"Ooops...",
					JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		for(int i=0; i<=players.length; i++) {
			if(playerName.equals(this.getPlayer(i))) {
				JOptionPane.showMessageDialog(gc.getView(), 						
						"Player already exists",
						"Ooops...",
						JOptionPane.ERROR_MESSAGE);
				return 1;
			}
			else if(players[0] != null || players[1] != null || players[2] != null || players[3] != null || players[4] != null || players[5] != null || players[6] != null || players[7] != null || players[8] != null || players[9] != null || players[10] != null || players[11] != null || players[12] != null || players[13] != null || players[14] != null ){
				this.getPlayers()[counter] = playerName;
				return 2;
			}
		}
		numOfPlayers++;
		return 3;
	}
	
	/*Checks if the array is empty and returns the first empty slot or returns -1 if the array is full */
	public int countArrayElements(String[] players) {
		for(int i=0; i<=players.length; i++) {
			if(players[i]==null) {
				return i;
			}
		}
		return -1;
	}
}