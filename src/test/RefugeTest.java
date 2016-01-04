package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;
import slots.Tax;
import slots.Field.Types;
import slots.Refuge;

public class RefugeTest {

	@Test
	public void test() {
		Refuge refuge = new Refuge (1, Types.REFUGE, 5000);

		Player player = new Player("Test");

		refuge.pushToGUI(1);
		refuge.landOnField(player);
		
		assertTrue(player.getAccount().getGold()== 35000);
	}

}
