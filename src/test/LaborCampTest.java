package test;	
import game.*;
import slots.*;
//import slots.FieldController.Types;

import static org.junit.Assert.*;

import org.junit.Test;

public class LaborCampTest {

	@Test
	public void testGetRent() {
		BreweryData data = new BreweryData(100, 1, 4000);
		BreweryController laborCamp = new BreweryController(data);
		Player player = new Player("Test");
		laborCamp.pushToGUI(1);
		laborCamp.buyField(player);
		assertTrue("Fejl, renten er forkert", laborCamp.hasOwner() == true && laborCamp.getRent() == 100 || laborCamp.hasOwner() == false);
	}
	
	@Test
	public void testGetWorth() {
		BreweryData data = new BreweryData(1, 13, 3);
		BreweryController laborCamp = new BreweryController(data);
		assertTrue(laborCamp.getWorth() == 3);
	}
	
	@Test
	public void testGetDescription() {
		BreweryData data = new BreweryData(1, 13, 3);
		BreweryController laborCamp = new BreweryController(data);
		assertTrue(!laborCamp.getDescription().isEmpty());
	}
	
	@Test
	public void testLandOnField(){
		BreweryData data = new BreweryData(100, 2, 4000);
		BreweryController laborCamp = new BreweryController(data);
		Player player1 = new Player("Test1");
		Player player2 = new Player("Test2");
		laborCamp.pushToGUI(2);
		laborCamp.buyField(player2);
		laborCamp.landOnField(player1);
		
		
		boolean wasTrue = false;
		for (int i = 2; i <= 12; i++)
			if(player1.getAccount().getGold() == 30000 - i * 100 && player2.getAccount().getGold() == 26000 + i * 100)
				wasTrue = true;
		
			assertTrue(wasTrue);
		}
	
}
