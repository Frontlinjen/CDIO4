package test;	
import game.*;
import slots.*;
//import slots.FieldController.Types;

import static org.junit.Assert.*;

import org.junit.Test;

public class LaborCampTest {

	@Test
	public void testGetRent() {
		BreweryData data = new BreweryData(1, 2, 3);
		BreweryController laborCamp = new BreweryController(data);
		
		Player player1 = new Player("Test1");
		Player player2 = new Player("Test2");
		laborCamp.pushToGUI(1);
		
		laborCamp.setOwner(player2);
		player2.getProperty().addProperty(laborCamp);
		player2.getProperty().addProperty(laborCamp);
				
		laborCamp.landOnField(player1);
		
		for (int i=2; i<=12 ;i++){
			int baseRent = laborCamp.getRent(player2.getProperty().getBreweriesOwned(), i);
			
			assertTrue(player1.getAccount().getGold()==30000-baseRent && player2.getAccount().getGold()==30000+baseRent);
		}
	}

}
