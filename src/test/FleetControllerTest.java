package test;
import game.*;
import slots.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FleetControllerTest {

	@Test
	public void testGetRent() {
		FleetData fleetData1 = new FleetData(1,100);
		FleetController ship1 = new FleetController(fleetData1);
		ship1.pushToGUI(1);
		Player player1 = new Player("Sheep");
				
		ship1.setOwner(player1);
		player1.getProperty().addFleet(ship1);
		assertTrue("Fail, the rent should be 500 with 1 fleet owned",ship1.getRent()==500);
		player1.getProperty().addFleet(ship1);
		assertTrue("Fail, the rent should be 1000 with 2 fleet owned",ship1.getRent()==1000);
		player1.getProperty().addFleet(ship1);
		assertTrue("Fail, the rent should be 1500 with 3 fleet owned",ship1.getRent()==2000);
		player1.getProperty().addFleet(ship1);
		assertTrue("Fail, the rent should be 2000 with 4 fleet owned",ship1.getRent()==4000);
	}
}
