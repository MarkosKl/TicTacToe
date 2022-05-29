package unitTests;

import static org.junit.jupiter.api.Assertions.*;
import control.GameController;
import model.GameModel;

import org.junit.jupiter.api.Test;

class GetMoverMarkTest {

	@Test
	void test() {
		var gc = new GameController();
		Boolean mover=true;
		var mod = new GameModel(gc);
		char output = mod.getMoverMark();
		assertEquals(mover,output);
	}

}
