package test;	
import game.*;
import slots.*;
import slots.Field.Types;

import static org.junit.Assert.*;

import org.junit.Test;

public class LaborCampTest {

	@Test
	public void testGetRent() {
		LaborCamp laborCamp = new LaborCamp(1, Types.LABORCAMP, 2500, 100);
		
		Player player1 = new Player("Test1");
		Player player2 = new Player("Test2");
		laborCamp.pushToGUI(1);
		
		laborCamp.setOwner(player2);
		player2.getProperty().expandLaborCamp();
		player2.getProperty().expandLaborCamp();
				
		laborCamp.landOnField(player1);
		boolean paid = false;
		for (int i=2; i<=12 ;i++){
			int baseRent = i * 100 * player2.getProperty().getLaborCampOwned();
			
			if (player1.getAccount().getGold()==30000-baseRent && player2.getAccount().getGold()==30000+baseRent){
				paid=true;
				break;
			}
			
		}
		assertTrue(paid == true);
//		System.out.println(player1.getAccount().getGold());
//		System.out.println(player2.getAccount().getGold());
	}

}
