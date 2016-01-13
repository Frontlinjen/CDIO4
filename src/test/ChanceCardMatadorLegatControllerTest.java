package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chancecards.ChanceCardCashData;
import Chancecards.ChanceCardMatadorLegatController;
import game.Player;
import slots.TerritoryController;
import slots.BreweryController;
import slots.TerritoryData;
import slots.BreweryData;

public class ChanceCardMatadorLegatControllerTest {

	private Player player;
	private TerritoryController tc, tc1;
	private TerritoryData td, td1;
	private BreweryController bc;
	private BreweryData bd;
	private ChanceCardMatadorLegatController ccMat;
	private ChanceCardCashData data;
	private int[] buildingtax;
	
	@Test
	public void test() {
		player = new Player("Test");
		buildingtax = new int[6];
		td = new TerritoryData(1,6200,1200,3000, buildingtax);
		td1 = new TerritoryData(2,4200,800,2000, buildingtax);
		tc = new TerritoryController(td);
		tc1 = new TerritoryController(td1);
		bd = new BreweryData(500,3,4000);
		bc = new BreweryController(bd);
		bc.pushToGUI(3);
		bc.buyField(player);
		tc.pushToGUI(1);
		tc.buyField(player);
		tc1.pushToGUI(2);
		tc1.buyField(player);
		tc1.buyHouse(player);
		tc1.buyHouse(player);
		tc1.buyHouse(player);
		player.getAccount().setGold(7300);
		
		data = new ChanceCardCashData(0, 15000);
		ccMat = new ChanceCardMatadorLegatController(data);
		
		ccMat.onDrawn(player);
		
		assertTrue(player.getAccount().getGold() == 7300);
		
		}

	@Test
	public void test2()	{
		player = new Player("Test");
		buildingtax = new int[6];
		td1 = new TerritoryData(2,4200,800,2000, buildingtax);
		tc1 = new TerritoryController(td1);
		tc1.pushToGUI(2);
		tc1.buyField(player);
		player.getAccount().setGold(7300);
		
		data = new ChanceCardCashData(0, 15000);
		ccMat = new ChanceCardMatadorLegatController(data);
		
		ccMat.onDrawn(player);
		
		assertTrue(player.getAccount().getGold() == 22300);
	}
}
