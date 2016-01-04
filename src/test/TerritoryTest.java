package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;
import slots.Tax;
import slots.Territory;
import slots.Field.Types;

public class TerritoryTest {

	@Test
	public void test() {
		Territory territory = new Territory(1, Types.TERRITORY, 2500, 1000);

		Player player = new Player("Test");
		Player player2 = new Player("Test2");

		territory.pushToGUI(1);
		player.getAccount().removeGold(2500);
		territory.setOwner(player);
		territory.landOnField(player2);
		
		assertTrue(
				player.getAccount().getGold()== 28500 && player2.getAccount().getGold()== 29000);
		}

}
