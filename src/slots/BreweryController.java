package slots;

import java.awt.Color;


import desktop_resources.GUI;
import slots.OwnableController;
import game.DiceCup;
import game.DiceResult;
import game.Player;
import game.Translator;

public class BreweryController extends OwnableController{
	private BreweryData breweryData;

	public BreweryController(BreweryData data) {
		super((OwnableData)data);
		breweryData = data;
	}
	
	public void chargeRent(Player player)
	{
		
		GUI.getUserButtonPressed(Translator.getString("BREWERY"), Translator.getString("ROLL"));
		DiceCup dice = new DiceCup(2);
		DiceResult res = dice.rollDice();
		int price = res.getSum() * getRent();
		GUI.setDice(res.getDice(0), 3, 7, res.getDice(1), 4,8);
		GUI.showMessage(Translator.getString("BREWERYCONCLUSION", res.getSum(), price));
		player.getAccount().transferTo(breweryData.getOwner().getAccount(), price);
	}
	public desktop_fields.Field pushToGUI(int position){
		
		breweryData.setPosition(position);
		guiField = new desktop_fields.Brewery.Builder().setRent(Translator.getString("BREWERYRENT",/*HARDCODED VARIABLES IS NEVER GOOD! TODO: REMOVE*/ 100)).setBgColor(new Color(255f/255, 165f/255, 48f/255)).build();
		guiField.setDescription(getDescription());
		guiField.setTitle(breweryData.getName());

		guiField.setSubText(Integer.toString(breweryData.getPrice()));

		return guiField;
	}

	@Override
	public int getWorth() {
		return breweryData.getPrice();
	}
	@Override
	public String getDescription() {
		
		return Translator.getString("BREWERYDSC");
	}

	@Override
	public FIELDGROUPS getFieldGroup() {
		return FIELDGROUPS.BREWERY;
	}

	@Override
	public int getRent() {
		return 100*breweryData.getOwner().getProperty().getBreweriesOwned();
	}

	@Override
	protected void registerOwner() {
		breweryData.getOwner().getProperty().addBreweries(this);
	}

	@Override
	protected void UnRegisterOwner() {
		breweryData.getOwner().getProperty().removeBreweries(this);
		
	}
	
	public String toString(){
		return "getWorth()=" + getWorth() + ", getDescription()=" + getDescription() + ", getFieldGroup()=" + getFieldGroup() + ", getRent()=" + getRent() + ", BreweryData()" + breweryData.toString();
	}
}
