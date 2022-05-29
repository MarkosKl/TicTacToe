package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import control.GameController;
import model.GameModel;

class EmptyBoardTest {

	@Test
	void test() {
		var gc = new GameController();
		var mod = new GameModel(gc);
		Boolean tr=false;
		
		char board[][]={{'X', 'O', 'X'}, 
				  {'X', 'O', 'O'}, 
				  {'O', 'X', 'X'}};
		mod.emptyBoard(board);
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(board[i][j]=='\0') {
					tr=true;
				}
				else tr=false;
			}
		}
		assertEquals(true,tr);
	}
}
