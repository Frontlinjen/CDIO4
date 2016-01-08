package test;

import static org.junit.Assert.*;

import org.junit.Test;
import game.Account;
import game.Player;
import slots.FieldController.Types;
import slots.TaxData;

public class TaxTest {

	@Test
	public void test() {
		TaxData tax = new TaxData(1, Types.TAX, 1000);

		Player player = new Player("Test");

		tax.pushToGUI(1);
		player.move(1);
		tax.landOnField(player);
		
		assertTrue(player.getAccount().getGold()== 27000 || player.getAccount().getGold()== 29000);
	}

}
