package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.GameModel;
import control.GameController;

class MrBeanTest {

	@Test
	void test() {
		var gc = new GameController();
		var mod = new GameModel(gc);
		char[][] board = null;
		board[3][3]='\0';
		
	}

}
