package model;

import java.awt.Dimension;

import control.GameController;
import model.HalO.MoveO;
import model.HalX.MoveX;
import model.MrBeanO.MoveRO;
import model.MrBeanX.MoveRX;


public class GameModel {
	PlayerRoster  playerRoster;
	String [] gamePlayers;
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
	
	public void makeMove(int row, int col) {
		checkMoveValidity(row, col);
		gameBoard[row][col]=getMoverMark();
		mover=!mover;
		moves++;
		
		//////////////Hal/////////////////////
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("Hal"))) {
			
			if(mover == true && moves <= 8) {//Thetei to sumbolo tou PerfectAI ws X giati einai sta aristera
				this.makeAiMove();
			}
		}
		
		if((gc.getView().getRightPanel().getCurrentPlayer().equals("Hal"))) {
			
			if(mover == false && moves <= 8) {//Thetei to sumbolo tou PerfectAI ws O giati einai sta deksia
				this.makeAiMove();
			}
		}
		/////////////////MrBean//////////////////
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("MrBean"))) {
			
			String winner = this.checkForWinner(this.getGameBoard());
			if(mover == true && moves <= 8 && winner == "") {//Thetei to sumbolo tou BadAI ws X giati einai sta aristera kai bazoume kai to winner giati epaize mia extra kinhsh enw eixe teleiwsei to paixnidi
				this.makeAiMove();
			}
		}
		if((gc.getView().getRightPanel().getCurrentPlayer().equals("MrBean"))) {
			
			String winner = this.checkForWinner(this.getGameBoard());
			if(mover == false && moves <= 8 && winner == "") {//Thetei to sumbolo tou BadAI ws O giati einai sta aristera kai bazoume kai to winner giati epaize mia extra kinhsh enw eixe teleiwsei to paixnidi
				this.makeAiMove();
			}
		}
		
		this.lastCheck();
		gc.enableDone();
		gc.showWinner();
	}
	
	public void makeAiMove() {
		///////////////////Hal////////////////
		if((gc.getView().getRightPanel().getCurrentPlayer().equals("Hal"))) {//Briskei thn kaluterh pithanh kinhsh kai thn topothetei sto gameBoard me to swsto sumbolo
			MoveO bestMoveAi = new MoveO();
			bestMoveAi = HalO.findBestMoveO(gameBoard);
			
			gameBoard[bestMoveAi.rowO][bestMoveAi.colO] = 'O';
		}
		
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("Hal"))) {//Briskei thn kaluterh pithanh kinhsh kai thn topothetei sto gameBoard me to swsto sumbolo
			MoveX bestMoveAi = new MoveX();
			bestMoveAi = HalX.findBestMoveX(gameBoard);
			
			gameBoard[bestMoveAi.rowX][bestMoveAi.colX] = 'X';
		}
		/////////////////MrBean//////////////////
		if((gc.getView().getLeftPanel().getCurrentPlayer().equals("MrBean"))) {//Briskei tuxaia kinhsh kai thn topothetei sto gameBoard me to swsto sumbolo
			MoveRX randomMoveAi = new MoveRX();
			randomMoveAi = MrBeanX.generateRandomMoveRX(gameBoard);
			
			gameBoard[randomMoveAi.rowRX][randomMoveAi.colRX] = 'X';
		}
		
		if((gc.getView().getRightPanel().getCurrentPlayer().equals("MrBean"))) {//Briskei tuxaia kinhsh kai thn topothetei sto gameBoard me to swsto sumbolo
			MoveRO randomMoveAi = new MoveRO();
			randomMoveAi = MrBeanO.generateRandomMoveRO(gameBoard);
			
			gameBoard[randomMoveAi.rowRO][randomMoveAi.colRO] = 'O';
		}
		
		mover=!mover;
		moves++;
	}
	
	public void selectPerfectAi() {
		this.makeAiMove();
	}
	
	public void selectRandomAi() {
		this.makeAiMove();
	}
	
	public void checkForAi() {
		this.setMover(true);
	}
	
	public char getMoverMark() {
		return mover? 'X' : 'O';
	}
	
	public void selectPlayer(String player, int pos) {
		if (pos<0 && pos>1)return;
		gamePlayers[pos]=player;		
	}
	
	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] !=null);
	}
	
	public void setMover(Boolean mover) {
		this.mover = mover;
	}
		
	public void startGame() {
		gameBoard= new char[3][3];
	}
	
	public boolean inPlay() {
		String winner = checkForWinner(gameBoard); 
		return gameBoard!=null && moves <9 && winner == "";
	}
	
	public boolean NoPlay() {
		return !inPlay();
	}
	
	public String[] getGamePlayers() {
		return gamePlayers;
	}

	public char[][] getGameBoard() {
		return gameBoard;
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
	
	public String getPlayerStats(String player) {
		StringBuilder sb = new StringBuilder("");
		sb.append(player).append("\n\n\n");
		sb.append("Total:").append("\t").append(4).append("\n");
		sb.append("Won:").append("\t").append("75%").append("\n");
		sb.append("Lost:").append("\t").append("25%").append("\n");
		return sb.toString();			
	}
	
	public void makeMoves(int row, int column) {
		checkMoveValidity(row, column);
		gameBoard[row][column] = getMoverMark();
		mover = !mover;
		moves++;
	}
	
	public String checkForWinner(char[][] gameBoard) {
		int i=0;
	
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
		return "";
		}
	
	public String checkForTie(char[][] gameBoard) {
		String winner = checkForWinner(gameBoard);
		
				if(winner == "" && moves == 9) {
					return "TIE";
				
				}
		return "";
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
	
	
	
	
	
	
	
	
	/*---------------------------------------------THELOUN STROSIMO--------------------------------------------------------------------*/
	
	
	
	
	public void BothAiPlaying(){//Elegxoume poia AI paizoun metaju tous kai ta bazoume na kanoune kinhseis mexri na breuei nikhths h isopalia
		//////////////////////////////Hal VS MrBean//////////////////////////
		if((gc.getView().getLeftPanel().getSelectPlayerBtn().equals("Hal") && gc.getView().getRightPanel().getSelectPlayerBtn().equals("MrBean"))){
			
			boolean stop = true;
				while (stop) {
				if(stop) {
					
					MoveX bestMoveAi = new MoveX();
					bestMoveAi = HalX.findBestMoveX(gameBoard);
					gameBoard[bestMoveAi.rowX][bestMoveAi.colX] = 'X';
					moves++;
					mover=!mover;
					
					String winner = this.checkForWinner(this.getGameBoard());
					String tie = this.checkForTie(this.getGameBoard());
					this.lastCheck();
					gc.enableDone();
					gc.showWinner();
					if(winner != "" || tie != "") {
						break;
					}
					
					MoveRO randomMoveAi = new MoveRO();
					randomMoveAi = MrBeanO.generateRandomMoveRO(gameBoard);
					gameBoard[randomMoveAi.rowRO][randomMoveAi.colRO] = 'O';
					moves++;
					mover=!mover;
					
					
					this.lastCheck();
					gc.enableDone();
					gc.showWinner();
					String winner1 = this.checkForWinner(this.getGameBoard());
					String tie1 = this.checkForTie(this.getGameBoard());
					if(winner1 != "" || tie1 != "") {
						break;
						}
					}
				}
		}
	if((gc.getView().getLeftPanel().getSelectPlayerBtn().equals("MrBean") && gc.getView().getRightPanel().getSelectPlayerBtn().equals("Hal"))) {
		
		boolean stop = true;
		while (stop) {
			
		if(stop) {
			
			MoveRX randomMoveAi = new MoveRX();
			randomMoveAi = MrBeanX.generateRandomMoveRX(gameBoard);
			gameBoard[randomMoveAi.rowRX][randomMoveAi.colRX] = 'X';
			moves++;
			mover=!mover;
			
			String winner = this.checkForWinner(this.getGameBoard());
			String tie = this.checkForTie(this.getGameBoard());
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			if(winner != "" || tie != "") {
				break;
			}
			
			MoveO perfectMoveAi = new MoveO();
			perfectMoveAi = HalO.findBestMoveO(gameBoard);
			gameBoard[perfectMoveAi.rowO][perfectMoveAi.colO] = 'O';
			moves++;
			mover=!mover;
			
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			String winner1 = this.checkForWinner(this.getGameBoard());
			String tie1 = this.checkForTie(this.getGameBoard());
			if(winner1 != "" || tie1 != "") {
				break;
			}
		}
		
		}
	}
	/////////////////////////////////////////Hal VS Hal///////////////////////////
	if((gc.getView().getLeftPanel().getSelectPlayerBtn().equals("Hal") && gc.getView().getRightPanel().getSelectPlayerBtn().equals("Hal"))) {
		
		boolean stop = true;
		while (stop) {
			
		if(stop) {
			
			MoveX bestMoveAi = new MoveX();
			bestMoveAi = HalX.findBestMoveX(gameBoard);
			gameBoard[bestMoveAi.rowX][bestMoveAi.colX] = 'X';
			moves++;
			mover=!mover;
			
			String winner = this.checkForWinner(this.getGameBoard());
			String tie = this.checkForTie(this.getGameBoard());
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			if(winner != "" || tie != "") {
				break;
			}
			
			MoveO perfectMoveAi = new MoveO();
			perfectMoveAi = HalO.findBestMoveO(gameBoard);
			gameBoard[perfectMoveAi.rowO][perfectMoveAi.colO] = 'O';
			moves++;
			mover=!mover;
			
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			String winner1 = this.checkForWinner(this.getGameBoard());
			String tie1 = this.checkForTie(this.getGameBoard());
			if(winner1 != "" || tie1 != "") {
				break;
			}
		}
		}
	}
	/////////////////////////////MrBean VS MrBean///////////////////////////////////
	if((gc.getView().getLeftPanel().getSelectPlayerBtn().equals("MrBean") && gc.getView().getRightPanel().getSelectPlayerBtn().equals("MrBean"))) {
		
		boolean stop = true;
		while (stop) {
			
		if(stop) {
			
			MoveRX randomMoveAi = new MoveRX();
			randomMoveAi = MrBeanX.generateRandomMoveRX(gameBoard);
			gameBoard[randomMoveAi.rowRX][randomMoveAi.colRX] = 'X';
			moves++;
			mover=!mover;
			
			String winner = this.checkForWinner(this.getGameBoard());
			String tie = this.checkForTie(this.getGameBoard());
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			if(winner != "" || tie != "") {
				break;
			}
			
			MoveRO randomMoveAi1 = new MoveRO();
			randomMoveAi1 = MrBeanO.generateRandomMoveRO(gameBoard);
			gameBoard[randomMoveAi1.rowRO][randomMoveAi1.colRO] = 'O';
			moves++;
			mover=!mover;
			
			this.lastCheck();
			gc.enableDone();
			gc.showWinner();
			String winner1 = this.checkForWinner(this.getGameBoard());
			String tie1 = this.checkForTie(this.getGameBoard());
			if(winner1 != "" || tie1 != "") {
				break;
			}
		}
		}
	}
	}
}