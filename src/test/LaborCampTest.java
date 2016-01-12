package test;	
import game.*;
import slots.*;
//import slots.FieldController.Types;

import static org.junit.Assert.*;

import org.junit.Test;

public class LaborCampTest {

	@Test
	public void testGetRent() {
		BreweryData data = new BreweryData(1, 13, 3);
		BreweryController laborCamp = new BreweryController(data);
		for(int i = 2; i <= 12; i++){
			assertTrue(laborCamp.getRent(i, 2) == 2*i*100);
		}
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
		assertTrue(laborCamp.getDescription().equals("Leje af Bryggeri koster 100 gange dit terning slag<br>Hvis begge Bryggerier ejes af samme, så stiger lege til 200 gange slaget"));
	}
	
	@Test
	public void testLandOnField(){
		BreweryData data = new BreweryData(1, 13, 3);
		BreweryController laborCamp = new BreweryController(data);
		Player player1 = new Player("Test1");
		Player player2 = new Player("Test2");
		
		laborCamp.setOwner(player2);
		player2.getProperty().addBreweries(laborCamp);
		laborCamp.landOnField(player1);
		
		for(int i = 2; i <= 12; i++){
			assertTrue(player1.getAccount().getGold() == 30000-i*100);
			assertTrue(player2.getAccount().getGold() == 30000+i*100);
		}
		
	}
	
}
