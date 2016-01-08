package slots;

import java.awt.Color;


import desktop_resources.GUI;
import slots.OwnableController;
import game.DiceResult;
import game.Player;
import game.Translator;
import slots.FieldController.Types;

public class BreweryData extends OwnableData{

	private int baseRent;
	
	
	public BreweryData(int baseRent, int translateID, int price) {
		super(translateID, price);
		this.baseRent = baseRent;
	}

}
