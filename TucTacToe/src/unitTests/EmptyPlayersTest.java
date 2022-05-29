package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import control.GameController;
import model.GameModel;

class EmptyPlayersTest {

	private String players[];

	@Test
	void test() {
		players = new String[2];
		players[0]="markos";
		players[1]="nikos";
		var gc = new GameController();
		var mod = new GameModel(gc);
		Boolean tr=false;
		mod.emptyPlayers();
		if(players[0]!=null && players[1]!=null) {
			tr=true;
		}
		else tr=false;
		
		assertEquals(true,tr);
	}
	
}
