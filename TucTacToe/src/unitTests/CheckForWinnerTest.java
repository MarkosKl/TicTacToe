package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import control.GameController;
import model.GameModel;


import org.junit.jupiter.api.Test;

class CheckForWinnerTest {

	@Test
	void test1() {
		var gc = new GameController();
		char[][] gameBoard = {{'X', 'X', 'X'}, {'O', 'X',' ' }, {' ', 'O',' '}};		
		var model = new GameModel(gc);
		String output = model.checkForWinner(gameBoard);
		assertEquals("X wins", output);
	}

	@Test
	void test2() {
		var gc = new GameController();
		char[][] gameBoard = {{'X', 'O', 'O'}, {'X', ' ', ' '}, {'X', ' ', ' '}};		
		var model = new GameModel(gc);
		String output = model.checkForWinner(gameBoard);
		assertEquals("X wins", output);
	}

	@Test
	void test3() {
		var gc = new GameController();
		char[][] gameBoard = {{'X', 'O', 'X'}, {'O', 'X', ' '}, {'O', 'O', 'X'}};		
		var model = new GameModel(gc);
		String output = model.checkForWinner(gameBoard);
		assertEquals("X wins", output);
	}

}
