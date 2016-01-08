package slots;

import java.awt.Color;


import desktop_resources.GUI;
import slots.OwnableController;
import game.DiceResult;
import game.Player;
import game.Translator;

public class BreweryController extends OwnableController{

	private BreweryData breweryData;
	desktop_fields.Street LaborCamp;

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
		 * Player lands on laborcamp.
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
				int price = res.getSum() * 100 * getOwner().getProperty().getBreweriesOwned();
				GUI.setDice(res.getDice(0), 3, 7, res.getDice(1), 4,8);
				GUI.showMessage(Translator.getString("LABORCAMPCONCLUSION", res.getSum(), price));
				player.getAccount().transferTo(getOwner().getAccount(), price);
			}else{
				GUI.showMessage(Translator.getString("YOURFIELD"));
			}
		}else{
			if(BuyField(player)){
				GUI.showMessage(Translator.getString("BOUGHTFIELD", getName(), price));
			}	
		}
	}
	public desktop_fields.Field pushToGUI(int position){

		this.position = position;
		LaborCamp = new desktop_fields.Street.Builder().setRent(Translator.getString("LABORCAMPRENT", baseRent)).setBgColor(new Color(255f/255, 165f/255, 48f/255)).build();
		LaborCamp.setDescription(this.getDescription());
		LaborCamp.setTitle(this.getName());

		LaborCamp.setSubText(price+"");

		return LaborCamp;
	}

	@Override
	public String toString() {
		return "LaborCamp [LaborCamp=" + LaborCamp + ", price=" + price + ", position=" + position
				+ ", getRent()=" + getRent() + ", getOwner()=" + getOwner() + ", hasOwner()=" + hasOwner()
				+ ", getName()=" + getName() + ", getPosition()=" + getPosition() + "]";
	}
}
