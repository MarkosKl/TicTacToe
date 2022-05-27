package model;

import java.io.Serializable;

import javax.swing.JOptionPane;

import control.GameController;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int best5Games[],recent5Games[];
	protected int win,tie,lose,numOfGames,resultScore;
	protected double winPercentage,losePercentage,score;
	protected String best10Players[];
	private String[] playersInfo;
	private int numOfPlayers;
	private GameController gc = new GameController();
	
	public Player() {
		this.best10Players=best10Players;
		this.best5Games=best5Games;
		this.recent5Games=recent5Games;
		this.numOfGames=numOfGames;
		this.winPercentage=winPercentage;
		this.losePercentage=losePercentage;
		this.score=score;
		
		playersInfo = new String[15];
		playersInfo[0]="Vasilis";
		playersInfo[1]="Nektarios";
		playersInfo[2]="Yannis";
		playersInfo[3]="Eleni";
		playersInfo[4] = "Hal";
		playersInfo[5] = "Mr. Bean";
		this.numOfPlayers=6;
	}
	
	public Player(int numOfGames) {
		this.win=win;
		this.tie=tie;
		this.lose=lose;
		this.numOfGames=numOfGames;
	}
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}



	public String[] getPlayersInfo() {
		return playersInfo;
	}

	public void setPlayers(String[] players) {
		this.playersInfo = players;
	}

	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}



	public String getPlayer(int i) {
		if (i<0 || i>playersInfo.length) {
			return null;
		}
		return playersInfo[i];
	}
	
	public int[] getBest5Games() {
		return best5Games;
	}



	public void setBest5Games(int[] best5Games) {
		this.best5Games = best5Games;
	}



	public int[] getRecent5Games() {
		return recent5Games;
	}



	public void setRecent5Games(int[] recent5Games) {
		this.recent5Games = recent5Games;
	}



	public int getNumOfGames() {
		return numOfGames;
	}



	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}



	public double getWinPercentage() {
		return winPercentage;
	}



	public void setWinPercentage(double winPercentage) {
		this.winPercentage = winPercentage;
	}



	public double getLosePercentage() {
		return losePercentage;
	}



	public void setLosePercentage(double losePercentage) {
		this.losePercentage = losePercentage;
	}



	public double getScore() {
		return score;
	}



	public void setScore(double score) {
		this.score = score;
	}



	public String[] getBest10Players() {
		return best10Players;
	}



	public void setBest10Players(String[] best10Players) {
		this.best10Players = best10Players;
	}


	//Method that calculates score
	public double calculateScore(int w,int t,int numOfGames) {
		
		score=50*((2*w+t)/numOfGames);
		
		return score;
	}
	
	//Method that calculates Win Percentage
	public double calculateWinPercentage(int win, int numOfGames) {
		
		winPercentage=(win*100)/numOfGames;
		
		return winPercentage;
	}
	
	//Method that calculates Lose Percentage
	public double calculateLosePercentage(int lose, int numOfGames) {
		
		losePercentage=(lose*100)/numOfGames;
		
		return losePercentage;
	}
	
	public void setPlayerString(String s) {
		int counter = this.countArrayElements(getPlayersInfo());
		this.playersInfo[counter] = playersInfo[counter];
	}


	/*Checks if the array is empty and returns the first empty slot or returns -1 if the array is full */
	public int countArrayElements(String[] playersInfo) {
		for(int i=0; i<=playersInfo.length; i++) {
			if(playersInfo[i]==null) {
				return i;
			}
		}
		return -1;
	}
	
	
	public int addPlayer() {
		String playerName;
		int counter = 0;
		if(this.countArrayElements(getPlayersInfo()) < 15) {
			counter = this.countArrayElements(getPlayersInfo());
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
		for(int i=0; i<=playersInfo.length; i++) {
			if(playerName.equals(this.getPlayer(i))) {
				JOptionPane.showMessageDialog(gc.getView(), 						
						"Player already exists",
						"Ooops...",
						JOptionPane.ERROR_MESSAGE);
				return 1;
			}
			else if(playersInfo[0] != null || playersInfo[1] != null || playersInfo[2] != null || playersInfo[3] != null || playersInfo[4] != null || playersInfo[5] != null || playersInfo[6] != null || playersInfo[7] != null || playersInfo[8] != null || playersInfo[9] != null){//Ama exoume kenh thesh sto array, bazei to onoma sthn swsth thesh kathe fora
				this.getPlayersInfo()[counter] = playerName;
				return 2;
			}
		}
		numOfPlayers++;
		return 3;
	}

}
