package model;

public class HallX {
	public HallX() {
		
	}
	static class MoveX{
		int rowX, colX;
	}
	static String playerX = "X", opponentX ="O";
	
	static Boolean isMovesLeftX(String board[][]) {
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(board[i][j]=="\0") {
					return true;
				}
			}
		}
		return false;
	}
	
	static int evaluateX(String b[][]) {
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
	}
}
