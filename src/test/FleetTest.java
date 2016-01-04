package test;
import game.*;
import slots.*;
import slots.Field.Types;

import static org.junit.Assert.*;

import org.junit.Test;

public class FleetTest {

	@Test
	public void testGetRent() {
		Fleet ship1 = new Fleet(1,Types.FLEET,100);
		ship1.pushToGUI(1);
		Player player1 = new Player("Sheep");
				
		ship1.setOwner(player1);
		player1.getProperty().expandFleet();
		assertTrue("Fail, the rent should be 500 with 1 fleet owned",ship1.getRent()==500);
		player1.getProperty().expandFleet();
		assertTrue("Fail, the rent should be 1000 with 2 fleet owned",ship1.getRent()==1000);
		player1.getProperty().expandFleet();
		assertTrue("Fail, the rent should be 1500 with 3 fleet owned",ship1.getRent()==2000);
		player1.getProperty().expandFleet();
		assertTrue("Fail, the rent should be 2000 with 4 fleet owned",ship1.getRent()==4000);
	}
}
