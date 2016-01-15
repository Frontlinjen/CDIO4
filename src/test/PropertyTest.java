package test;

import static org.junit.Assert.*;

import org.junit.Before;

import game.*;
import slots.OwnableController.FIELDGROUPS;
import slots.TerritoryController;
import slots.TerritoryData;

import org.junit.Test;

import desktop_fields.Field;
import desktop_resources.GUI;

public class PropertyTest {
	
	Player player = new Player("player1");
	
	@Test
	public void ownsEntireGroupTest() {
		
		int[] a = {1,1,1,1,1};
		TerritoryData data1 = new TerritoryData(2, 0, 1, 1, 1, a);
		TerritoryController ctrl1 = new TerritoryController(data1);
		TerritoryData data2 = new TerritoryData(4, 0, 1, 1, 1, a);
		TerritoryController ctrl2 = new TerritoryController(data2);
		Field fctrl1 = ctrl1.pushToGUI(1);
		Field fctrl2 = ctrl2.pushToGUI(2);
		GUI.create(new Field[]{fctrl1, fctrl2});
		Property prop = player.getProperty();
		
		ctrl1.buyField(player);
		assertFalse(prop.ownsEntireGroup(FIELDGROUPS.BLUE));
		ctrl2.buyField(player);
		assertTrue(prop.ownsEntireGroup(FIELDGROUPS.BLUE));
	}
	
	@Test
	public void ownsEntireGroupTestWithThreeFieldGroupAndOnePlayerTest(){
		int[] a = {1,1,1,1,1};
		TerritoryData data3 = new TerritoryData(7, 1, 1, 1, 1, a);
		TerritoryController ctrl3 = new TerritoryController(data3);
		TerritoryData data4 = new TerritoryData(9, 1, 1, 1, 1, a);
		TerritoryController ctrl4 = new TerritoryController(data4);
		TerritoryData data5 = new TerritoryData(10, 1, 1, 1, 1, a);
		TerritoryController ctrl5 = new TerritoryController(data5);
		Field fctrl3 = ctrl3.pushToGUI(1);
		Field fctrl4 = ctrl4.pushToGUI(2);
		Field fctrl5 = ctrl5.pushToGUI(3);
		GUI.create(new Field[]{fctrl3, fctrl4, fctrl5});
		Property prop = player.getProperty();
		
		ctrl3.buyField(player);
		assertFalse(prop.ownsEntireGroup(FIELDGROUPS.PINK));
		ctrl4.buyField(player);
		assertFalse(prop.ownsEntireGroup(FIELDGROUPS.PINK));
		ctrl5.buyField(player);
		assertTrue(prop.ownsEntireGroup(FIELDGROUPS.PINK));
	}
}
