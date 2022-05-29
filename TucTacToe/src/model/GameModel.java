package model;

import java.awt.Dimension;

import control.GameController;
import model.Hal.Move;
import model.MrBean.MoveRandomO;
import model.MrBean.MoveRandomX;



public class GameModel {
	PlayerRoster  playerRoster;
	String[] gamePlayers;
	char[][] gameBoard;
	GameController gc;
	Boolean mover;
	int moves;
	
	
	public GameModel(GameController gc) {
		this.gc=gc;
		gamePlayers = new String[2];
		gameBoard= null;
		playerRoster = new PlayerRoster();
		mover=false;
		moves = 0;
	}
	
	public void selectPlayer(String player, int pos) {
		if (pos<0 && pos>1)return;
		gamePlayers[pos]=player;		
	}
	
	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] !=null);
	}
	
		
	public void startGame() {
		gameBoard= new char[3][3];
	}
	
	public boolean inPlay() {
		String winner = checkForWinner(gameBoard); 
		return gameBoard!=null && moves <9 && winner == " ";
	}
	
	public boolean NoPlay() {
		return !inPlay();
	}
	
	public int getMover() {
		return mover.compareTo(false);
	}
	
	public String[] getGamePlayers() {
		return gamePlayers;
	}
	
	public String getGamePlayer(int i) {
		if (i<0 || i>1) {
			return null;
		}
		return gamePlayers[i];
	}
	
	public char[][] getGameBoard() {
		return gameBoard;
	}
	
	public void checkDimValidity(int row, int col) {
		if (row <0 || col < 0 || row > 2 || col >2) {
			throw new IndexOutOfBoundsException(row + ","+col +" is not a valid board cell");
		}
	}
	
	public void checkMoveValidity(int row, int col) {
		checkDimValidity(row, col);
		if (gameBoard[row][col]!='\0') {
			throw new IllegalArgumentException("Non playable cell");
		}
	}
	
	public char getBoardMark(int row, int col) {
		checkDimValidity(row, col);
		return gameBoard[row][col];
	}
	
	public void setGameBoard(char[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public PlayerRoster getPlayerRoster() {
		return playerRoster;
	}

	public void setPlayerRoster(PlayerRoster playerRoster) {
		this.playerRoster = playerRoster;
	}
	
	
	public void makeMove(int row, int col) {
		checkMoveValidity(row, col);
		gameBoard[row][col]=getMoverMark();
		mover = !mover;
		moves++;
		
		/********************Hal********************/
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("Hal"))) {
			
			if(mover == true && moves <= 8) {
				this.makeAiMove();
			}
		}
		
		if((gc.getView().getRightPanel().getCurrentPlayer().equals("Hal"))) {
			
			if(mover == false && moves <= 8) {
				this.makeAiMove();
			}
		}
		/******************MrBean******************/
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("MrBean"))) {
			
			String winner = this.checkForWinner(this.getGameBoard());
			if(mover == true && moves <= 8 && winner == " ") {
				this.makeAiMove();
			}
		}
		if((gc.getView().getRightPanel().getCurrentPlayer().equals("MrBean"))) {
			
			String winner = this.checkForWinner(this.getGameBoard());
			if(mover == false && moves <= 8 && winner == " ") {
				this.makeAiMove();
			}
		}
		
		this.lastCheck();
		gc.enableDone();
		gc.showWinner();
	}
	
	public void makeAiMove() {
		/********************Hal********************/
		if((gc.getView().getRightPanel().getCurrentPlayer().equals("Hal"))) {
			Move bestMoveAi = new Move();
			bestMoveAi = Hal.findBestMoveO(gameBoard);
			
			gameBoard[bestMoveAi.row][bestMoveAi.column] = 'O';
		}
		
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("Hal"))) {
			Move bestMoveAi = new Move();
			bestMoveAi = Hal.findBestMoveX(gameBoard);
			
			gameBoard[bestMoveAi.row][bestMoveAi.column] = 'X';
		}
		/******************MrBean******************/
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("MrBean"))) {
			MoveRandomX randomMoveAi = new MoveRandomX();
			randomMoveAi = MrBean.generateRandomMoveX(gameBoard);
			
			gameBoard[randomMoveAi.rowsRandomX][randomMoveAi.columnsRandomX] = 'X';
		}
		
		if((gc.getView().getRightPanel().getCurrentPlayer().equals("MrBean"))) {
			MoveRandomO randomMoveAi = new MoveRandomO();
			randomMoveAi = MrBean.generateRandomMoveO(gameBoard);
			
			gameBoard[randomMoveAi.rowsRandomO][randomMoveAi.columnsRandomO] = 'O';
		}
		
		mover=!mover;
		moves++;
	}
	
	public void selectHal() {
		this.makeAiMove();
	}
	
	public void selectMrBean() {
		this.makeAiMove();
	}
	
	public void checkForAi() {
		this.setMover(true);
	}
	
	public char getMoverMark() {
		return mover? 'X' : 'O';
	}
	
	public String getPlayerStats(String pl) {
		StringBuilder sb = new StringBuilder("");
		sb.append(pl).append("\n\n\n");
		sb.append("Total:").append("\t").append("4").append("\n");
		sb.append("Won:").append("\t").append("4").append("\n");
		sb.append("Lost:").append("\t").append("4").append("\n");
		sb.append("WinPercentage:").append("\t").append("4").append("\n");
		sb.append("LosePercentage:").append("\t").append("4").append("\n");
		return sb.toString();			
	}
	

	
	public String checkForWinner(char[][] gameBoard) {
		int i=0;
		// check rows
		for (int j = 0; j <= 2; j++) {
		if (gameBoard[j][i] == 'X' && gameBoard[j][i+1] == 'X' && gameBoard[j][i+2] == 'X') {
		return "X wins";
		}
		}

		for (int j = 0; j <= 2; j++) {
		if (gameBoard[j][i] == 'O' && gameBoard[j][i+1] == 'O' && gameBoard[j][i+2] == 'O') {
		return "O wins";
		}
		}
		//check columns
		for (int j = 0; j <= 2; j++) {
		if (gameBoard[i][j] == 'X' && gameBoard[i+1][j] == 'X' && gameBoard[i+2][j] == 'X') {
		return "X wins";
		}
		}

		for (int j = 0; j <= 2; j++) {
		if (gameBoard[i][j] == 'O' && gameBoard[i+1][j] == 'O' && gameBoard[i+2][j] == 'O') {
		return "O wins";
		}
		}

		//check diagonals
		if (gameBoard[0][0] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][2] == 'X') {
		return "X wins";
		}

		else if (gameBoard[0][0] == 'O' && gameBoard[1][1]  == 'O' && gameBoard[2][2] == 'O') {
		return "O wins";
		}

		else if (gameBoard[0][2] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][0] == 'X') {
		return "X wins";
		}

		else if (gameBoard[0][2] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][0] == 'O') {
		return "O wins";
		}
		return " ";
		}
	
	public String checkForTie(char[][] gameBoard) {
		String winner = checkForWinner(gameBoard);
		
				if(winner == " " && moves == 9) {
					return "TIE";
				
				}
		return " ";
	}
	
	/*Method to print the result*/
	public void lastCheck() {
		String winner = this.checkForWinner(this.getGameBoard());
		String tie = this.checkForTie(this.getGameBoard());
		if(this.NoPlay() && winner != "") {
			System.out.println(winner);
		}
		if(tie != "") {
			System.out.println(tie);
		}
	}
	
	public void emptyBoard(char[][] gameBoard) {
		moves = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				gameBoard[i][j] = '\0';
			}
		}
	}
	
	public void reEnableSelectPlayer() {
		gc.getView().getLeftPanel().getSelectPlayerBtn().setPreferredSize(new Dimension(150,40));
		gc.getView().getRightPanel().getSelectPlayerBtn().setPreferredSize(new Dimension(150,40));
	}
	
	public void emptyPlayers() {
		for(int i = 0; i < 2; i++) {
			gamePlayers[i] = null;
		}
	}
	
	public void setMover(Boolean mover) {
		this.mover = mover;
	}
	
	
	
	/*Check which AI are playing */
	public void BothAiPlaying(){
		/********************Hal VS MrBean********************/
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("Hal") && gc.getView().getRightPanel().getCurrentPlayer().equals("MrBean"))){
			
			boolean stop = true;
				while (stop) {
				if(stop) {
					
					Move bestMoveAi = new Move();
					bestMoveAi = Hal.findBestMoveX(gameBoard);
					gameBoard[bestMoveAi.row][bestMoveAi.column] = 'X';
					moves++;
					mover=!mover;
					
					String winner = this.checkForWinner(this.getGameBoard());
					String tie = this.checkForTie(this.getGameBoard());
					this.lastCheck();
					gc.enableDone();
					gc.showWinner();
					if(winner != " " || tie != " ") {
						break;
					}
					
					MoveRandomO randomMoveAi = new MoveRandomO();
					randomMoveAi = MrBean.generateRandomMoveO(gameBoard);
					gameBoard[randomMoveAi.rowsRandomO][randomMoveAi.columnsRandomO] = 'O';
					moves++;
					mover=!mover;
					
					
					this.lastCheck();
					gc.enableDone();
					gc.showWinner();
					String winner1 = this.checkForWinner(this.getGameBoard());
					String tie1 = this.checkForTie(this.getGameBoard());
					if(winner1 != " " || tie1 != " ") {
						break;
						}
					}
				}
		}
	if((gc.getView().getLeftPanel().getCurrentPlayer().equals("MrBean") && gc.getView().getRightPanel().getCurrentPlayer().equals("Hal"))) {
		
		boolean stop = true;
		while (stop) {
			
		if(stop) {
			
			MoveRandomX randomMoveAi = new MoveRandomX();
			randomMoveAi = MrBean.generateRandomMoveX(gameBoard);
			gameBoard[randomMoveAi.rowsRandomX][randomMoveAi.columnsRandomX] = 'X';
			moves++;
			mover=!mover;
			
			String winner = this.checkForWinner(this.getGameBoard());
			String tie = this.checkForTie(this.getGameBoard());
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			if(winner != " " || tie != " ") {
				break;
			}
			
			Move perfectMoveAi = new Move();
			perfectMoveAi = Hal.findBestMoveO(gameBoard);
			gameBoard[perfectMoveAi.row][perfectMoveAi.column] = 'O';
			moves++;
			mover=!mover;
			
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			String winner1 = this.checkForWinner(this.getGameBoard());
			String tie1 = this.checkForTie(this.getGameBoard());
			if(winner1 != " " || tie1 != " ") {
				break;
			}
		}
		
		}
	}
	/********************Hal VS Hal********************/
	if((gc.getView().getLeftPanel().getCurrentPlayer().equals("Hal") && gc.getView().getRightPanel().getCurrentPlayer().equals("Hal"))) {
		
		boolean stop = true;
		while (stop) {
			
		if(stop) {
			
			Move bestMoveAi = new Move();
			bestMoveAi = Hal.findBestMoveX(gameBoard);
			gameBoard[bestMoveAi.row][bestMoveAi.column] = 'X';
			moves++;
			mover=!mover;
			
			String winner = this.checkForWinner(this.getGameBoard());
			String tie = this.checkForTie(this.getGameBoard());
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			if(winner != " " || tie != " ") {
				break;
			}
			
			Move perfectMoveAi = new Move();
			perfectMoveAi = Hal.findBestMoveO(gameBoard);
			gameBoard[perfectMoveAi.row][perfectMoveAi.column] = 'O';
			moves++;
			mover=!mover;
			
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			String winner1 = this.checkForWinner(this.getGameBoard());
			String tie1 = this.checkForTie(this.getGameBoard());
			if(winner1 != " " || tie1 != " ") {
				break;
			}
		}
		}
	}
	/********************MrBean VS MrBean********************/
	if((gc.getView().getLeftPanel().getCurrentPlayer().equals("MrBean") && gc.getView().getRightPanel().getCurrentPlayer().equals("MrBean"))) {
		
		boolean stop = true;
		while (stop) {
			
		if(stop) {
			
			MoveRandomX randomMoveAi = new MoveRandomX();
			randomMoveAi = MrBean.generateRandomMoveX(gameBoard);
			gameBoard[randomMoveAi.rowsRandomX][randomMoveAi.columnsRandomX] = 'X';
			moves++;
			mover=!mover;
			
			String winner = this.checkForWinner(this.getGameBoard());
			String tie = this.checkForTie(this.getGameBoard());
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			if(winner != " " || tie != " ") {
				break;
			}
			
			MoveRandomO randomMoveAi1 = new MoveRandomO();
			randomMoveAi1 = MrBean.generateRandomMoveO(gameBoard);
			gameBoard[randomMoveAi1.rowsRandomO][randomMoveAi1.columnsRandomO] = 'O';
			moves++;
			mover=!mover;
			
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			String winner1 = this.checkForWinner(this.getGameBoard());
			String tie1 = this.checkForTie(this.getGameBoard());
			if(winner1 != " " || tie1 != " ") {
				break;
			}
		}
		}
	}
	}
}