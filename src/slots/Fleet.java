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
	desktop_fields.Street fleet;
	private final int RENT_1 = 500;
	private final int RENT_2 = 1000;
	private final int RENT_3 = 2000;
	private final int RENT_4 = 4000;
	
	public Fleet(int i, Types type, int price) {
		super(i, type, price);
	}

	@Override
	/**
	 * Considers how much the player should pay depending on how
	 * many fleets the owner has.
	 */
	public int getRent() {
	switch (getOwner().getProperty().getFleetOwned()){
	case 1: {
		return RENT_1;
	}
	case 2: {
		return RENT_2;
	}
	case 3: {
		return RENT_3;
	}
	case 4: {
		return RENT_4;
	}
	default: {
		
	}
	}
		return 0;
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
				player.getProperty().expandFleet();
				GUI.showMessage(Translator.getString("BOUGHTFIELD", getName(), price));
			}	
		}
	}

	@Override
	public desktop_fields.Field pushToGUI(int position) {
		this.position = position;
		fleet = new desktop_fields.Street.Builder().setRent(String.format("%d, %d, %d, %d", RENT_1, RENT_2, RENT_3, RENT_4)).setBgColor(new Color(144f/255,211f/255, 212f/255)).build();
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
