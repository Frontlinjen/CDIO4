package slots;

import java.awt.Color;

import desktop_resources.GUI;
import slots.OwnableController;
import game.Player;
import game.Translator;

public class TerritoryData extends OwnableData{

	private int rent;

	public TerritoryData(int i, int price, int rent) {
		super(i, price);
		this.rent = rent;
	}


	public int getRent() {
		return rent;
	}

}