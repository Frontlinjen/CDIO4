package slots;

import java.awt.Color;


import desktop_resources.GUI;
import slots.OwnableController;
import game.DiceResult;
import game.Player;
import game.Translator;

public class BreweryController extends OwnableController{

	private BreweryData breweryData;
	private desktop_fields.Street LaborCamp;

	public BreweryController(BreweryData data) {
		super((OwnableData)data);
		breweryData = data;
	}

	@Override
	public int getRent() {
		return 0;
	}

	@Override 
	public void landOnField(Player player) {
		/**
		 * Player lands on brewery.
		 * If field is owned, he pays an amount depending on a roll with
		 * two dice times the amount of labor camps owned by the owner.
		 * If field is not owned, player can choose to buy it.
		 */
		LaborCamp.displayOnCenter();
		if(hasOwner()){
			if(breweryData.getOwner()!=player)
			{
				GUI.getUserButtonPressed(Translator.getString("LABORCAMP"), Translator.getString("ROLL"));
				DiceResult res = player.getDice().rollDice();
				int price = res.getSum() */*HARDCODED VARIABLES IS NEVER GOOD! TODO: REMOVE 100:*/ 100 * breweryData.getOwner().getProperty().getBreweriesOwned();
				GUI.setDice(res.getDice(0), 3, 7, res.getDice(1), 4,8);
				GUI.showMessage(Translator.getString("LABORCAMPCONCLUSION", res.getSum(), price));
				player.getAccount().transferTo(breweryData.getOwner().getAccount(), price);
			}else{
				GUI.showMessage(Translator.getString("YOURFIELD"));
			}
		}else{
			if(BuyField(player)){
				GUI.showMessage(Translator.getString("BOUGHTFIELD", breweryData.getName(), breweryData.price));
			}	
		}
	}
	public desktop_fields.Field pushToGUI(int position){

		breweryData.setPosition(position);
		LaborCamp = new desktop_fields.Street.Builder().setRent(Translator.getString("LABORCAMPRENT",/*HARDCODED VARIABLES IS NEVER GOOD! TODO: REMOVE*/ 100)).setBgColor(new Color(255f/255, 165f/255, 48f/255)).build();
		LaborCamp.setDescription(breweryData.getDescription());
		LaborCamp.setTitle(breweryData.getName());

		LaborCamp.setSubText(Integer.toString(breweryData.getPrice()));

		return LaborCamp;
	}

	@Override
	public int getWorth() {
		return breweryData.getPrice();
	}

}
