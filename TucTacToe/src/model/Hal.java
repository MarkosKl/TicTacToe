package model;


public class Hal {
	
	/*constructor */
	public Hal() {
		
	}
	
	static class Move {
		int row, column;
	}
	
	
	static char playerX = 'X', playerO = 'O';
	

	//Checks for empty cells
		 static Boolean checkForEmptyCells(char board[][])
		{
			
		    for (int i=0; i<3; i++)
		        for (int j=0; j<3; j++)
		            if (board[i][j] == '\0')
		                return true;
		    return false;
		}
	
/********************************************************FOR O ONLY********************************************************/
	static int calculateScoreO(char board[][]) {
			
		/* checks for horizontal win */
		for(int i=0; i<3; i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				if(board[i][0] == playerX) {
					return +10;
				}
				else if(board[i][0] == playerO) {
					return -10;
				}
			}
		}
		
		/* checks for vertical win */
		for(int i=0; i<3; i++) {
			if(board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				if(board[0][i] == playerX) {
					return +10;
				}
				else if(board[0][i] == playerO) {
					return -10;
				}
			}
		}
		
		/* checks for diagonal win */
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
	    {
	        if (board[0][0] == playerX)
	            return +10;
	        else if (board[0][0] == playerO)
	            return -10;
	    }
	 
	    if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
	    {
	        if (board[0][2] == playerX)
	            return +10;
	        else if (board[0][2] == playerO)
	            return -10;
	    }
		
		return 0;
	}
	
/********************************************************FOR X ONLY********************************************************/
	static int calculateScoreX(char board[][]) {

		/* checks for horizontal win */
		for(int i=0; i<3; i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				if(board[i][0] == playerO) {
					return +10;
				}
				else if(board[i][0] == playerX) {
					return -10;
				}
			}
		}
		
		/* checks for vertical win */
		for(int i=0; i<3; i++) {
			if(board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				if(board[0][i] == playerO) {
					return +10;
				}
				else if(board[0][i] == playerX) {
					return -10;
				}
			}
		}
		
		/* checks for diagonal win */
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
	    {
	        if (board[0][0] == playerO)
	            return +10;
	        else if (board[0][0] == playerX)
	            return -10;
	    }
	 
	    if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
	    {
	        if (board[0][2] == playerO)
	            return +10;
	        else if (board[0][2] == playerX)
	            return -10;
	    }
		
		return 0;
	}
	
/********************************************************FOR O ONLY********************************************************/
	static int minimaxO(char board[][], int depth, Boolean isMax) {
		
		int scoreO = calculateScoreO(board);
		
		 // If Maximizer wins return score
	    if (scoreO == 10)
	        return scoreO;
	 
	    // If Minimizer wins return score
	    if (scoreO == -10)
	        return scoreO;
	 
	    if (checkForEmptyCells(board) == false)
	        return 0;
	 
	    if (isMax)
	    {
	        int best = -100;
	 
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	            	
	                if (board[i][j]=='\0')
	                {
	                    
	                    board[i][j] = playerX;
	 
	                    //Here is the recursion and checks for every outcome of the game
	                    best = Math.max(best, minimaxO(board,depth + 1, !isMax));
	 
	                    //Empty the cells
	                    board[i][j] = '\0';
	                }
	            }
	        }
	        return best;
	    }
	    else
	    {
	        int best = 100;
	 
	        
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                
	                if (board[i][j] =='\0')
	                {
	                    
	                    board[i][j] = playerO;
	 
	                    
	                    best = Math.min(best, minimaxO(board,
	                                    depth + 1, !isMax));
	 
	                    
	                    board[i][j] = '\0';
	                }
	            }
	        }
	        return best;
	    }
	}
	
/********************************************************FOR O ONLY********************************************************/
	//Returns the best possible move
	 static Move findBestMoveO(char board[][])
	{
	    int bestVal = -1000;
	    Move bestMoveO = new Move();
	    bestMoveO.row = -1;
	    bestMoveO.column = -1;
	 
	    //Access of the cells and find the optimal move
	    for (int i = 0; i < 3; i++)
	    {
	        for (int j = 0; j < 3; j++)
	        {
	            
	            if (board[i][j] == '\0')
	            {
	                
	                board[i][j] = playerX;
	 
	               
	                int moveVal = minimaxO(board, 0, false);
	 
	                
	                board[i][j] = '\0';
	 
	                //Compare the moves and chooses the best
	                if (moveVal > bestVal)
	                {
	                    bestMoveO.row = i;
	                    bestMoveO.column = j;
	                    bestVal = moveVal;
	                }
	            }
	        }
	    }
	 
	    return bestMoveO;
	}
	 
/********************************************************FOR X ONLY********************************************************/	 
	 static int minimaxX(char board[][],int depth, Boolean isMax)
	{
		int score = calculateScoreX(board);
		
		
		if (score == 10)
		 return score;
		
		
		if (score == -10)
		 return score;
		
		
		if (checkForEmptyCells(board) == false)
		 return 0;
		
		if (isMax)
		{
		 int best = -100;
		 for (int i = 0; i < 3; i++)
		 {
		     for (int j = 0; j < 3; j++)
		     {
		         if (board[i][j]=='\0')
		         {
		             board[i][j] = playerX;
		
		             best = Math.max(best, minimaxX(board, depth + 1, !isMax));
		
		             board[i][j] ='\0';
		         }
		     }
		 }
		 return best;
		}
		
		else
		{
		 int best = 100;
		
		 for (int i = 0; i < 3; i++)
		 {
		     for (int j = 0; j < 3; j++)
		     {
		         if (board[i][j] == '\0')
		         {
		             board[i][j] = playerO;
		
		             best = Math.min(best, minimaxX(board, depth + 1, !isMax));
		
		             board[i][j] ='\0';
		         }
		     }
		 }
		 return best;
		}
	}
		
/********************************************************FOR X ONLY********************************************************/
		static Move findBestMoveX(char gameBoard[][])
		{
		int bestVal = -100;
		Move bestMoveX = new Move();
		bestMoveX.row = -1;
		bestMoveX.column = -1;
		
		for (int i=0; i<3; i++)
		{
		 for (int j=0; j<3; j++)
		 {
		     if (gameBoard[i][j] == '\0')
		     {
		         gameBoard[i][j] = playerX;
		
		         int moveVal = minimaxX(gameBoard, 0, false);
		
		         gameBoard[i][j] = '\0';
		
		         if (moveVal > bestVal)
		         {
		             bestMoveX.row = i;
		             bestMoveX.column = j;
		             bestVal = moveVal;
		         }
		     }
		 	 }
		}
		return bestMoveX;
	}
}
