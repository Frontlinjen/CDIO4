package slots;

import java.awt.Color;

import desktop_resources.GUI;
import slots.OwnableController;
import game.Player;
import game.Translator;

public class TerritoryData extends OwnableData{

	private int rent;
	private int houses;
	private int houseCost;
	private int groupID;
	
	public TerritoryData(int i, int id, int price, int rent, int houseCost) {
		super(i, price);
		this.rent = rent;
		this.houseCost = houseCost;
		houses = 0;
		groupID = id;
	}


	public int getRent() {
		return rent;
	}
	
	public int getHouses(){
		return houses;
	}
	
	public void addHouse(){
		houses++;
	}
	
	public int getHouseCost(){
		return houseCost;
	}
	
	public int getGroupID(){
		return groupID;
	}

}