package model;

import java.io.Serializable;

import javax.swing.JOptionPane;

import control.GameController;

public class PlayersCatalogue implements Serializable {
	private String[] players;
	private GameController gc = new GameController();
	private int numOfPlayers;
	
	public PlayersCatalogue() {
		players = new String[15];
		players[0]="Vasilis";
		players[1]="Nektarios";
		players[2]="Yannis";
		players[3]="Eleni";
		this.numOfPlayers=4;
	}
	
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}



	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
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
	
	public void setPlayerString(String s) {
		int counter = this.countArrayElements(getPlayers());
		this.players[counter] = players[counter];
	}


	/*Checks if the array is empty and returns the first empty slot or returns -1 if the array is full */
	private int countArrayElements(String[] players) {
		for(int i=0; i<=players.length; i++) {
			if(players[i]==null) {
				return i;
			}
		}
		return -1;
	}
	
	public int addPlayer() {
		String playerName;
		int counter = 0;
		if(this.countArrayElements(getPlayers()) <= 14) {
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
		if(playerName.length()> 20) {
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
			else if(players[0] != null || players[1] != null || players[2] != null || players[3] != null || players[4] != null || players[5] != null || players[6] != null || players[7] != null || players[8] != null || players[9] != null){//Ama exoume kenh thesh sto array, bazei to onoma sthn swsth thesh kathe fora
				this.getPlayers()[counter] = playerName;
				return 2;
			}
		}
		numOfPlayers++;
		return 3;
	}
}
