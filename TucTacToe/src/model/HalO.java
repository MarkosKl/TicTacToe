package model;

public class HalO {
	
public HalO() {
		
	}
	
	 static class MoveO
	{
	    int rowO, colO;
	};
	 
	static char playerO = 'X', opponentO = 'O';
	
	//Checks for empty cells
	 static Boolean isMovesLeftO(char[][] board)
	{
		
	    for (int i = 0; i < 3; i++)
	        for (int j = 0; j < 3; j++)
	            if (board[i][j] == '\0')
	                return true;
	    return false;
	}
	 
	 static int evaluateO(char b[][])
	{
	    // Checks for horizontal win
	    for (int rowO = 0; rowO < 3; rowO++)
	    {
	        if (b[rowO][0] == b[rowO][1] &&
	            b[rowO][1] == b[rowO][2])
	        {
	            if (b[rowO][0] == opponentO)
	                return +10;
	            else if (b[rowO][0] ==playerO)
	                return -10;
	        }
	    }
	 
	    //Checks for vertical win
	    for (int colO = 0; colO < 3; colO++)
	    {
	        if (b[0][colO] == b[1][colO] &&
	            b[1][colO] == b[2][colO])
	        {
	            if (b[0][colO] == opponentO)
	                return +10;
	 
	            else if (b[0][colO] == playerO)
	                return -10;
	        }
	    }
	 
	    //Checks for diagonal win
	    if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
	    {
	        if (b[0][0] == opponentO)
	            return +10;
	        else if (b[0][0] == playerO)
	            return -10;
	    }
	 
	    if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
	    {
	        if (b[0][2] == opponentO)
	            return +10;
	        else if (b[0][2] == playerO)
	            return -10;
	    }
	    
	 
	   
	    return 0;
	}
	 
	//Checks for every possible outcome of the game and returns the value of GameBoard
	 static int minimaxO(char[][] board,
	                    int depth, Boolean isMax)
	{
	    int score = evaluateO(board);
	 
	    // If Maximizer wins return score
	    if (score == 10)
	        return score;
	 
	    // If Minimizer wins return score
	    if (score == -10)
	        return score;
	 
	    if (isMovesLeftO(board) == false)
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
	                    
	                    board[i][j] = opponentO;
	 
	                    //Here is the recursion and checks for every outcome of the game
	                    best = Math.max(best, minimaxO(board,
	                                    depth + 1, !isMax));
	 
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
	 
	
	//Returns the best possible move
	 static MoveO findBestMoveO(char board[][])
	{
	    int bestVal = -1000;
	    MoveO bestMoveO = new MoveO();
	    bestMoveO.rowO = -1;
	    bestMoveO.colO = -1;
	 
	    //Access of the cells and find the optimal move
	    for (int i = 0; i < 3; i++)
	    {
	        for (int j = 0; j < 3; j++)
	        {
	            
	            if (board[i][j] == '\0')
	            {
	                
	                board[i][j] = opponentO;
	 
	               
	                int moveVal = minimaxO(board, 0, false);
	 
	                
	                board[i][j] = '\0';
	 
	                //Compare the moves and chooses the best
	                if (moveVal > bestVal)
	                {
	                    bestMoveO.rowO = i;
	                    bestMoveO.colO = j;
	                    bestVal = moveVal;
	                }
	            }
	        }
	    }
	 
	    return bestMoveO;
	}
}
