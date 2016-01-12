package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Account;
import game.Player;
import slots.FieldController;
import slots.TaxData;
import slots.TaxController;
import slots.ParkinglotData;
import slots.FieldData;

public class TaxTest {
	
	FieldData fieldData;
	Account acc;
	TaxData taxD;
	TaxController tax;
	Player player;
	
	@Before
	public void preTest(){
		fieldData = new FieldData(39);
		Account acc = new Account(0, "Test");
		taxD = new TaxData(1, 2000, 10);
		tax = new TaxController(fieldData, taxD, acc);
		player = new Player("Test");
	}

	@Test
	public void testFlatTax() {

		tax.pushToGUI(1);
		player.move(1, false);
		tax.landOnField(player);
		
		assertTrue(player.getAccount().getGold()== 27000 || player.getAccount().getGold()== 28000);
		assertTrue(acc.getGold()==2000 || acc.getGold()==3000);

	}
//	
//	@Test
//	public void testPercentageTax

}
