package slots;

import game.*;

import java.awt.Color;

import desktop_fields.Field;
import desktop_fields.Shipping;
import desktop_resources.GUI;
import slots.Ownable;
import game.Player;
import game.Translator;
import slots.Field.Types;

public class Fleet extends Ownable{
	private final int[] RENT = {500, 1000, 2000, 4000};
	
	public Fleet(int i, Types type, int price) {
		super(i, type, price);
	}

	@Override
	/**
	 * Considers how much the player should pay depending on how
	 * many fleets the owner has.
	 */
	public int getRent() {
	return RENT[getOwner().getProperty().getFleetOwned()-1];
	}

	@Override
	public void landOnField(Player player) {
		
		fleet.displayOnCenter();
		/**
		 * Player lands on a fleet.
		 * If the field is owned, the player pays the rent, 
		 * which is determined by calling getRent.
		 * If the field is not owned, he has the choice to buy it.
		 */
		if(hasOwner()){
			if(getOwner()!=player)
			{
				GUI.showMessage(Translator.getString("PAYTHEOWNER", getRent()));
				player.getAccount().transferTo(getOwner().getAccount(), getRent());
			}else{
				GUI.showMessage(Translator.getString("YOURFIELD"));
			}
		}else{
			if(BuyField(player)){
				GUI.showMessage(Translator.getString("BOUGHTFIELD", getName(), price));
			}	
		}
	}

	@Override
	public desktop_fields.Field pushToGUI(int position) {
		this.position = position;
		fleet = new desktop_fields.Street.Builder().setRent(String.format("%d, %d, %d, %d", RENT[0], RENT[1], RENT[2], RENT[3])).setBgColor(new Color(144f/255,211f/255, 212f/255)).build();
		fleet.setTitle(getName());
		fleet.setDescription(getDescription());
		fleet.setSubText("" + price);
		return fleet;
	}
	
	@Override
	public String toString() {
		return "Fleet [Fleet=" + fleet + ", price=" + price + ", position=" + position
				+ ", getRent()=" + getRent() + ", getOwner()=" + getOwner() + ", hasOwner()=" + hasOwner()
				+ ", getName()=" + getName() + ", getPosition()=" + getPosition() + "]";
	}
}
