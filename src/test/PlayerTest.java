package test;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.Player;

public class PlayerTest {
	
	private Player player;
	
	@Before
	public void initialize(){
	player = new Player("Sheep");
	}
	
	@Test
	public void testGetAccount() {
		assertTrue("Fail, the player should have 30000 gold to start with.", player.getAccount().getGold()==30000);
	}

	@Test
	public void testGetName(){
		assertTrue("Fail, the player should have the name \"Sheep\"", player.getName().equals("Sheep"));
	}
	
	@Test
	public void testGetProperty() {
		assertTrue("Fail, A player should not own anything at the start of the game.", player.getProperty().getPropertyCount() == 0);
	}

	@Test
	public void testMove() {
		player.move(22, true);
		assertTrue("Fail, the player should be on the first",player.getPosition() == 0 && player.getNextPosition() == 22);
	}

	@Test
	public void testMoveToNextPosition(){
		player.move(30, true);
		player.move(15, true);
		player.moveToNextPosition();
		assertTrue("Fail, the player should be on position 22", player.getPosition() == 5 && player.getAccount().getGold() == 34000);
	}
	
	@Test
	public void testSetNextPosition(){
		player.setNextPosition(15, true);
		player.moveToNextPosition();
		assertTrue("Fail, the player should be on position 15", player.getPosition() == 15);
	}
	
	@Test
	public void testHasGetOutOfPrisonCard(){
		assertTrue("Fail, the player should not start with the getOutOfPrisonCard", !player.hasGetOutOfPrisonCard());
	}
	
	@Test
	public void testSetGetOutOfPrisonCard(){
		player.setHasGetOutOfPrisonCard(true);
		assertTrue("Fail, the player should have the getOutOfPrisonCard", player.hasGetOutOfPrisonCard());
	}

}