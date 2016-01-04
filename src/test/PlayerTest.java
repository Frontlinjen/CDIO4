package test;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Player;

public class PlayerTest {

	@Test
	public void testGetAccount() {
		Player player = new Player("Sheep");
		assertTrue("Fail, the player should have 30000 gold to start with.", player.getAccount().getGold()==30000);
	}

	@Test
	public void testGetProperty() {
		Player player = new Player("Sheep");
		assertTrue("Fail, A player should not own anything at the start of the game.", player.getProperty().getFleetOwned() == 0 && player.getProperty().getLaborCampOwned() == 0);
	}

	@Test
	public void testMove() {
		Player player = new Player("Sheep");
		player.move(22);
		assertTrue("Fail, the player should be on the first",player.getPosition()==1);
	}

	@Test
	public void testGetPosition() {
		Player player = new Player("Sheep");
		assertTrue("Fail, the player should not have a position without having had a turn", player.getPosition()==0);
	}

}