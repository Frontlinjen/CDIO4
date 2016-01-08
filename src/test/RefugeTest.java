package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;
import slots.TaxData;
import slots.FieldController.Types;
import slots.ParkinglotData;

public class RefugeTest {

	@Test
	public void test() {
		ParkinglotData refuge = new ParkinglotData (1, Types.REFUGE, 5000);

		Player player = new Player("Test");

		refuge.pushToGUI(1);
		refuge.landOnField(player);
		
		assertTrue(player.getAccount().getGold()== 35000);
	}

}
