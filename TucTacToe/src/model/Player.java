package model;

public class Player {

	private static final long serialVersionUID = 1L;
	protected int best5Games[],recent5Games[];
	protected int win,tie,lose,numOfGames,resultScore;
	protected double winPercentage,losePercentage,score;
	protected String best10Players[];
	protected String name;
	protected int totalGames[];
	
	public Player(int best5Games[], int recent5Games[], int numOfGames, int score) {
		
		this.best5Games=best5Games;
		this.recent5Games=recent5Games;
		this.numOfGames=numOfGames;
		this.score=score;
	}
	
	public Player(int numOfGames, int win, int tie, int lose, int totalGames[]) {
		this.win=win;
		this.tie=tie;
		this.lose=lose;
		this.numOfGames=numOfGames;
		this.totalGames=totalGames;
	}
	public Player(String name) {
		this.name=name;
	}
	
	public Player() {
		
	}
	/**********************GETTERS**********************/
	
	public int[] getBest5Games() {
		return best5Games;
	}

	public double getScore() {
		return score;
	}

	public double getLosePercentage() {
		return losePercentage;
	}

	public int[] getRecent5Games() {
		return recent5Games;
	}

	public String[] getBest10Players() {
		return best10Players;
	}

	public int getNumOfGames() {
		return numOfGames;
	}

	public double getWinPercentage() {
		return winPercentage;
	}
	
	public String getName() {
		return name;
	}
	
	public int getResultScore() {
		return resultScore;
	}
	
	public int getLose() {
		return lose;
	}
	
	public int getTie() {
		return tie;
	}
	
	public int getWin() {
		return win;
	}
	
	/***************************************************/

	/**********************SETTERS**********************/

	public void setWinPercentage(double winPercentage) {
		this.winPercentage = winPercentage;
	}


	public void setRecent5Games(int[] recent5Games) {
		this.recent5Games = recent5Games;
	}

	public void setLosePercentage(double losePercentage) {
		this.losePercentage = losePercentage;
	}


	public void setBest5Games(int[] best5Games) {
		this.best5Games = best5Games;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}


	public void setBest10Players(String[] best10Players) {
		this.best10Players = best10Players;
	}
	
	public void setWin(int win) {
		this.win = win;
	}

	public void setTie(int tie) {
		this.tie = tie;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public void setResultScore(int resultScore) {
		this.resultScore = resultScore;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/***************************************************/

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
