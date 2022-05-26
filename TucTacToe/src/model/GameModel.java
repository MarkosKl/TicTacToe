package model;

import control.GameController;


public class GameModel {
	PlayerRoster  playerRoster;
	String [] gamePlayers;
	String[][] gameBoard;
	GameController gc;
	Boolean mover;
	
	public GameModel(GameController gc) {
		this.gc=gc;
		gamePlayers = new String[2];
		gameBoard= null;
		playerRoster = new PlayerRoster();
		mover=false;
	}
	
	public void checkDimValidity(int row, int col) {
		if (row <0 || col < 0 || row > 2 || col >2) {
			throw new IndexOutOfBoundsException(row + ","+col +" is not a valid board cell");
		}
	}
	
	public void checkMoveValidity(int row, int col) {
		checkDimValidity(row, col);
		if (gameBoard[row][col]!=null) {
			throw new IllegalArgumentException("Non playable cell");
		}
	}
	
	public String getBoardMark(int row, int col) {
		checkDimValidity(row, col);
		return gameBoard[row][col];
	}
	
	public void makeMove(int row, int col) {
		checkMoveValidity(row, col);
		gameBoard[row][col]=getMoverMark();
		mover=!mover;		
	}
	
	public String getMoverMark() {
		return mover? "X": "O";
	}
	
	public void selectPlayer(String player, int pos) {
		if (pos<0 && pos>1)return;
		gamePlayers[pos]=player;		
	}
	
	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] !=null);
	}
		
	public void startGame() {
		gameBoard= new String[3][3];
	}
	
	public boolean inPlay() {
		return gameBoard !=null;
	}
	
	public boolean NoPlay() {
		return !inPlay();
	}
	
	public String[] getGamePlayers() {
		return gamePlayers;
	}

	public String[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(String[][] gameBoard) {
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
	
	public String checkWinner(String[][] gameBoard) {
		int i=0;
		
		/*check for tie*/
		
		for(i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(gameBoard[j][i]!=null) {
					return "TIE";
				}
			}
		}
		
		/*check the rows*/
		for(int j=0; j<3; j++) {
			if (gameBoard[j][i] == "X" && gameBoard[j][i+1]=="X" && gameBoard[j][i+2]=="X") {
				return "X wins";
			}
		}
		
		for(int j=0; j<3; j++) {
			if (gameBoard[j][i] == "O" && gameBoard[j][i+1]=="O" && gameBoard[j][i+2]=="O") {
				return "O wins";
			}
		}
		
		/*check the columns*/
		
		for(int j=0; j<3; j++) {
			if(gameBoard[j][i]=="X" && gameBoard[j+1][i]=="X" && gameBoard[j+2][i]=="X") {
				return "X wins";
			}
		}
		
		for(int j=0; j<3; j++) {
			if(gameBoard[j][i]=="O" && gameBoard[j+1][i]=="O" && gameBoard[j+2][i]=="O") {
				return "O wins";
			}
		}
		
		/*check the diagonals*/
		
		if(gameBoard[0][0]=="X" && gameBoard[1][1]=="X" && gameBoard[2][2]=="X") {
			return "X wins";
		}
		
		else if(gameBoard[0][0]=="O" && gameBoard[1][1]=="O" && gameBoard[2][2]=="O") {
			return "O wins";
		}
		
		else if(gameBoard[0][2]=="X" && gameBoard[1][1]=="X" && gameBoard[2][0]=="X") {
			return "X wins";
		}
		
		else if(gameBoard[0][2]=="O" && gameBoard[1][1]=="O" && gameBoard[2][0]=="O") {
			return "O wins";
		}
		return "";
	}
	
	public void lastCheck() {
		String winner = this.checkWinner(this.getGameBoard());
		if(this.NoPlay() && winner != "") {
			System.out.println(winner);
		}
	}
}
