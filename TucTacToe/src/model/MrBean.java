package model;
import java.util.Random;

public class MrBean {
	static int numberRowX, numberColumnX;
	static int numberRowO, numberColumnO;
	
	/*constructor */
	public MrBean() {
		
	}
	
	static class MoveRandomX{
		int rowsRandomX, columnsRandomX;
	}
	
	static class MoveRandomO{
		int rowsRandomO, columnsRandomO;
	}
	
	static char playerRandomX = 'X', playerRandomO = 'O';
	
	
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
		static MoveRandomO generateRandomMoveO(char board[][]) {
			
			Boolean emptyCells = checkForEmptyCells(board);
			Random randomMove = new Random();
			numberRowO = randomMove.nextInt(3);
			numberColumnO = randomMove.nextInt(3);
			
			MoveRandomO randomMoveO = new MoveRandomO();
			
			if(emptyCells == false) {
				randomMoveO.columnsRandomO = -1;
				randomMoveO.rowsRandomO = -1;
				return randomMoveO;
			}
			else {
				randomMoveO.columnsRandomO = numberColumnO;
				randomMoveO.rowsRandomO = numberRowO;
			}
			
			/*Makes moves since checking for previous full cells */
			while(board[randomMoveO.rowsRandomO][randomMoveO.columnsRandomO] != '\0') {
				numberRowO = randomMove.nextInt(3);
				numberColumnO = randomMove.nextInt(3);
				randomMoveO.rowsRandomO = numberRowO;
				randomMoveO.columnsRandomO = numberColumnO;
			}
			return randomMoveO;
		}
		 
/********************************************************FOR X ONLY********************************************************/
		static MoveRandomX generateRandomMoveX(char board[][]) {
			
			Boolean emptyCells = checkForEmptyCells(board);
			Random randomMove = new Random();
			numberRowX = randomMove.nextInt(3);
			numberColumnX = randomMove.nextInt(3);
			
			MoveRandomX randomMoveX = new MoveRandomX();
			
			if(emptyCells == false) {
				randomMoveX.rowsRandomX = -1;
				randomMoveX.columnsRandomX = -1;
				return randomMoveX;
			}
			else {
				randomMoveX.columnsRandomX = numberColumnX;
				randomMoveX.rowsRandomX = numberRowX;
			}
			
			/*Makes moves since checking for previous full cells */
			while(board[randomMoveX.rowsRandomX][randomMoveX.columnsRandomX] !='\0') {
				numberRowX = randomMove.nextInt(3);
				numberColumnX = randomMove.nextInt(3);
				randomMoveX.rowsRandomX = numberRowX;
				randomMoveX.columnsRandomX = numberColumnX;
			}
			return randomMoveX;
		}
}
