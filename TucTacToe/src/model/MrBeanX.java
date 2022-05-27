package model;

import java.util.Random;

public class MrBeanX {
	
	static int numberRowRX;
	static int numberColRX;
	
	public MrBeanX() {
	
	}
	
	 static class MoveRX
	{
	    int rowRX, colRX;
	};
	 
	static char playerRX = 'X', opponentRX = 'O';
	
	//Checks if the GameBoard is full
	static Boolean isMovesLeftRX(char board[][])
	{
	    for (int i = 0; i < 3; i++)
	        for (int j = 0; j < 3; j++)
	            if (board[i][j] == '\0')
	                return true;
	    return false;
	}
	
	//Generates random moves
	static MoveRX generateRandomMoveRX(char board[][]) {
		
		Boolean movesLeft = isMovesLeftRX(board);
		Random rand = new Random();
		numberRowRX = rand.nextInt(3);
		numberColRX = rand.nextInt(3);
		
		MoveRX randomMoveRX = new MoveRX();
        
        if(movesLeft == false) {
			randomMoveRX.rowRX = -1;
			randomMoveRX.colRX = -1;
			return randomMoveRX;
		} else {
			randomMoveRX.rowRX = numberRowRX;
	        randomMoveRX.colRX = numberColRX;
		}
        
      //Makes moves since checking for previous full cells
        while(board[randomMoveRX.rowRX][randomMoveRX.colRX] != '\0') {
        	numberRowRX = rand.nextInt(3);
    		numberColRX = rand.nextInt(3);
        	randomMoveRX.rowRX = numberRowRX;
	        randomMoveRX.colRX = numberColRX;
        }
		
        return randomMoveRX;
	}
	
}
