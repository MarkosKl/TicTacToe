package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.PlayerRoster;


class AddPlayerTest {

	@Test
	void test() {
		var playerRoster=new PlayerRoster();
		int output=playerRoster.addPlayer();
		assertEquals(2,output);
	}

}
