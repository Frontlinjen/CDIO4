package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Player;
import slots.TerritoryController;
import slots.TerritoryData;

public class TerritoryTest {

	private TerritoryData data;
	private TerritoryController territoryController;
	private Player player1, player2;
	private int[] buildingtax;
	
	@Before
	public void initialize(){
		data = new TerritoryData(2, 2, 2500, 500, 1000, buildingtax);
		territoryController = new TerritoryController(data);
		
		player1 = new Player("Test1");
		player2 = new Player("Test2");
		
		territoryController.pushToGUI(2);
		player1.getAccount().removeGold(2500);
		data.setOwner(player1);
	}
	
	@Test
	public void testLandOnField() {
		territoryController.landOnField(player2);
		assertTrue(player1.getAccount().getGold()== 28000 && player2.getAccount().getGold()== 29500);
		}

	@Test
	public void testGetHotelAmount(){
		assertTrue("Fejl, du starter med ingen hoteller", territoryController.getHotelAmount() == 0);
	}
	
	@Test
	public void  testGetHouseAmount(){
		assertTrue("Fejl, du starter med ingen hoteller", territoryController.getHouseAmount() == 0);
	}
	
	@Test
	public void testGetWorth(){
		assertTrue("Fejl, værdien burde være 2500, da der ingen huse og hoteller er", territoryController.getWorth() == 2500);
	}
	
	@Test
	public void testGetDescription(){
		assertTrue("Fejl, beskrivelsen burde ikke være tom", !territoryController.getDescription().isEmpty());
	}
	
	@Test
	public void testGetUpgradeCost(){
		assertTrue("Fejl, opgraderingen burde være 1000", territoryController.getUpgradeCosts() == 1000);
	}
	
	@Test
	public void testBuyHouse(){
		territoryController.buyHouse(player1);
		assertTrue("Fejl, huset blev ikke købt", territoryController.getHouseAmount() == 1);
		assertTrue("Fejl, pengene blev ikke overført korrekt", player1.getAccount().getGold() == 26500);
		assertTrue("Fejl, værdien skulle være 3500", territoryController.getWorth() == 3500);
	}
}
