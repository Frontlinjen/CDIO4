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
	
	public TerritoryData(int i, int price, int rent, int houseCost) {
		super(i, price);
		this.rent = rent;
		this.houseCost = houseCost;
		houses = 0;
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

}