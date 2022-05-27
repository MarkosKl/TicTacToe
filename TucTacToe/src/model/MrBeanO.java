package model;

import java.util.Random;

public class MrBeanO {
	static int numberRowRO;
	static int numberColRO;
	
	public MrBeanO() {
	
	}
	
	 static class MoveRO
	{
	    int rowRO, colRO;
	};
	 
	static String playerRO = "X", opponentRO = "O";
	
	//Checks if the GameBoard is full
	static Boolean isMovesLeftRO(char[][] board)
	{
	    for (int i = 0; i < 3; i++)
	        for (int j = 0; j < 3; j++)
	            if (board[i][j] == '\0')
	                return true;
	    return false;
	}
	
	//Generates random moves
	static MoveRO generateRandomMoveRO(char board[][]) {
		
		Boolean movesLeft = isMovesLeftRO(board);
		Random rand = new Random();
		numberRowRO = rand.nextInt(3);
		numberColRO = rand.nextInt(3);
		
		MoveRO randomMoveRO = new MoveRO();
        
        if(movesLeft == false) {
			randomMoveRO.rowRO = -1;
			randomMoveRO.colRO = -1;
			return randomMoveRO;
		} else {
			randomMoveRO.rowRO = numberRowRO;
	        randomMoveRO.colRO = numberColRO;
		}
        
      //Makes moves since checking for previous full cells
        while(board[randomMoveRO.rowRO][randomMoveRO.colRO] != '\0') {
        	numberRowRO = rand.nextInt(3);
    		numberColRO = rand.nextInt(3);
        	randomMoveRO.rowRO = numberRowRO;
	        randomMoveRO.colRO = numberColRO;
        }
		
        return randomMoveRO;
	}
}
