import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class GameTests {

	@Test
	void test1() {
		StartGUI gui = new StartGUI();
	}
	
	@Test
	void test2() {
		StartGUI gui = new StartGUI();
		Menu menu = gui.menu;
		Instructions ins = menu.getInstructions();
		PlayerSelect ps = ins.getPlayerSelect();
		ps.np = 4;
		ps.pNames = new ArrayList<>(Arrays.asList("Gogo", "Tory", "Kev", "Selon"));
		GameGUI ggui = ps.startGame();
	}
	
	@Test
	void test3() {
		StartGUI gui = new StartGUI();
		Menu menu = gui.menu;
		Instructions ins = menu.getInstructions();
		PlayerSelect ps = ins.getPlayerSelect();
		ps.np = 4;
		ps.pNames = new ArrayList<>(Arrays.asList("player name", "Tory", "Kev", "Selon"));
		GameGUI ggui = ps.startGame();
		ggui.game.roll();
		Location loc = ggui.game.currentPlayer.location;
		ggui.game.moveEast(ggui);
		assert(!loc.equals(ggui.game.currentPlayer.location));
	}

}
