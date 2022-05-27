package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import control.GameController;
import model.GameModel;

class CheckForTie {

	@Test
	void test1() {
		var gc = new GameController();
		char[][] gameBoard = {{'X', 'O', 'X'}, {'X', 'O', 'O'}, {'O', 'X', 'X'}};		
		var model = new GameModel(gc);
		String output = model.checkForTie(gameBoard);
		assertEquals("TIE", output);
	}


}
