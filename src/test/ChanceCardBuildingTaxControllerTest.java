package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chancecards.ChanceCardBuildingTaxController;
import Chancecards.ChanceCardBuildingTaxData;
import game.Player;
import game.Account;
import slots.TerritoryController;
import slots.TerritoryData;


public class ChanceCardBuildingTaxControllerTest {
	
	ChanceCardBuildingTaxData data;
	ChanceCardBuildingTaxController ccTax;
	Player player;
	TerritoryController felt, felt1;
	TerritoryData territoryData, territoryData1;
	Account acc;
	int[] buildingtax;
	
	
	
	@Test
	public void testBuildingTax() {
		
		data = new ChanceCardBuildingTaxData(1, 1000, 2000);
		
		acc = new Account(0, "ParkingLot");
		player = new Player("Test");
		buildingtax = new int[6];
		
		territoryData = new TerritoryData(1, 2500, 500, 1000, buildingtax);
		territoryData1 = new TerritoryData(2, 5000, 1500, 2000, buildingtax);
		felt = new TerritoryController(territoryData);
		felt1 = new TerritoryController(territoryData1);
		felt.pushToGUI(1);
		felt.buyField(player);
		felt.buyHouse(player);
		felt.buyHouse(player);
		felt.buyHouse(player);
		felt.buyHouse(player);
		felt.buyHouse(player);
		
		felt1.pushToGUI(2);
		felt1.buyField(player);
		felt1.buyHouse(player);
		felt1.buyHouse(player);
		felt1.buyHouse(player);
		felt1.buyHouse(player);
		
		System.out.println(felt.getHotelAmount());
		System.out.println(felt.getHouseAmount());
		
		player.getAccount().setGold(30000);
		
		ccTax = new ChanceCardBuildingTaxController(data, acc);
		ccTax.onDrawn(player);
		
		System.out.println(player.getAccount().getGold());
		System.out.println(acc.getGold());
		
		assertTrue(player.getAccount().getGold() == 24000);
		assertTrue(acc.getGold() == 6000);
	}
	
}
