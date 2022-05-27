package model;

public class HalX{
	public HalX() {
		
	}
	static class MoveX{
		int rowX, colX;
	}
	
	static char opponentX ='O',  playerX = 'X';
	
	static Boolean isMovesLeftX(char[][] board) {
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(board[i][j]=='\0') {
					return true;
				}
			}
		}
		return false;
	}
	
	static int evaluateX(char b[][]) {
		
		for(int rowX=0; rowX<3; rowX++) {
			if (b[rowX][0] == b[rowX][1] &&
				b[rowX][1] == b[rowX][2]) {
				if (b[rowX][0] == playerX) {
					return +10;
				}
				else if (b[rowX][0] == opponentX) {
					return -10;
				}	
			}
		}
		
		  for (int colX = 0; colX < 3; colX++)
		    {
		        if (b[0][colX] == b[1][colX] &&
		            b[1][colX] == b[2][colX])
		        {
		            if (b[0][colX] == playerX)
		                return +10;
		 
		            else if (b[0][colX] == opponentX)
		                return -10;
		        }
		    }
		 
		   
		    if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
		    {
		        if (b[0][0] == playerX)
		            return +10;
		        else if (b[0][0] == opponentX)
		            return -10;
		    }
		 
		    if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
		    {
		        if (b[0][2] == playerX)
		            return +10;
		        else if (b[0][2] == opponentX)
		            return -10;
		    }
		    
		 
		    return 0;
		}
		 
		
		 static int minimaxX(char board[][],
		                    int depth, Boolean isMax)
		{
		    int score = evaluateX(board);
		 
		    
		    if (score == 10)
		        return score;
		 
		   
		    if (score == -10)
		        return score;
		 
		    
		    if (isMovesLeftX(board) == false)
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
		 
		                    best = Math.max(best, minimaxX(board,
		                                    depth + 1, !isMax));
		 
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
		                    board[i][j] = opponentX;
		 
		                    best = Math.min(best, minimaxX(board,
		                                    depth + 1, !isMax));
		 
		                    board[i][j] ='\0';
		                }
		            }
		        }
		        return best;
		    }
		}
		 
		 static MoveX findBestMoveX(char[][] gameBoard)
		{
		    int bestVal = -100;
		    MoveX bestMoveX = new MoveX();
		    bestMoveX.rowX = -1;
		    bestMoveX.colX = -1;
		 
		    for (int i = 0; i < 3; i++)
		    {
		        for (int j = 0; j < 3; j++)
		        {
		           
		            if (gameBoard[i][j] == '\0')
		            {
		                gameBoard[i][j] = playerX;
		 
		                int moveVal = minimaxX(gameBoard, 0, false);
		 
		                gameBoard[i][j] = '\0';
		 
		                if (moveVal > bestVal)
		                {
		                    bestMoveX.rowX = i;
		                    bestMoveX.colX = j;
		                    bestVal = moveVal;
		                }
		            }
		        }
		    }
		    return bestMoveX;
		}
}
