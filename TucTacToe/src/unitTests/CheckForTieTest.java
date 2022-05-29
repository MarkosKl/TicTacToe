package unitTests;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import control.GameController;
import model.GameModel;

class CheckForTieTest {
	@Test
	void test1() {
		int moves=0;
		var gc = new GameController();
		assumeTrue(moves==9);
		char gameBoard[][] = {{'X', 'O', 'X'}, 
							  {'X', 'O', 'O'}, 
							  {'O', 'X', 'X'}};		
		var model = new GameModel(gc);
		String output = model.checkForTie(gameBoard);
		
		assertEquals("TIE", output);
	}
}
