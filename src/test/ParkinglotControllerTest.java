package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.*;
import slots.*;

public class ParkinglotControllerTest {

	@Test
	public void test() {
		
		Account parkinglotAcc = new Account(10000, "PAA");
		ParkinglotData parkinglotData = new ParkinglotData (1, parkinglotAcc);
		ParkinglotController parkinglot = new ParkinglotController(parkinglotData);
		
		Player player = new Player("Sheep");
		
		parkinglot.pushToGUI(1);
		parkinglot.landOnField(player);
		
		assertTrue(player.getAccount().getGold()== 40000);
	}

}
