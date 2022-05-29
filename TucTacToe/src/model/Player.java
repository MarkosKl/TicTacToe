package model;

public class Player{

	private static final long serialVersionUID = 1L;
	protected int best5Games[],recent5Games[];
	protected int win,tie,lose,numOfGames,resultScore;
	protected double winPercentage,losePercentage,score;
	protected String best10Players[];
	
	public Player(int best5Games[], int recent5Games[], int numOfGames, int score) {
		
		this.best5Games=best5Games;
		this.recent5Games=recent5Games;
		this.numOfGames=numOfGames;
		this.score=score;
	}
	
	public Player(int numOfGames, int win, int tie, int lose) {
		this.win=win;
		this.tie=tie;
		this.lose=lose;
		this.numOfGames=numOfGames;
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

}
