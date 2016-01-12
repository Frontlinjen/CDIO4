package slots;

import java.awt.Color;


import desktop_resources.GUI;
import slots.OwnableController;
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
		GUI.getUserButtonPressed(Translator.getString("LABORCAMP"), Translator.getString("ROLL"));
		DiceResult res = player.getDice().rollDice();
		int price = res.getSum() * getRent();
		GUI.setDice(res.getDice(0), 3, 7, res.getDice(1), 4,8);
		GUI.showMessage(Translator.getString("BREWERYCONCLUSION", res.getSum(), price));
		player.getAccount().transferTo(breweryData.getOwner().getAccount(), price);
	}
	public desktop_fields.Field pushToGUI(int position){
		
		breweryData.setPosition(position);
		LaborCamp = new desktop_fields.Street.Builder().setRent(Translator.getString("BREWERYRENT",/*HARDCODED VARIABLES IS NEVER GOOD! TODO: REMOVE*/ 100)).setBgColor(new Color(255f/255, 165f/255, 48f/255)).build();
		LaborCamp.setDescription(getDescription());
		LaborCamp.setTitle(breweryData.getName());

		LaborCamp.setSubText(Integer.toString(breweryData.getPrice()));

		return LaborCamp;
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
}
