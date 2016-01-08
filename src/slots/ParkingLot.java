package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;
import slots.Field.Types;

public class ParkingLot extends Field{


	private int bonus;
	desktop_fields.Street parkingLot; 
	
	
	public ParkingLot(int i, Types type, int bonus) {
		super(i, type);
		this.bonus = bonus;
	}

	@Override
	public void landOnField(Player player) {
		//Player lands on the Parking lot field and is given gold.
		parkingLot.displayOnCenter();
		GUI.showMessage(Translator.getString("LANDONPARKINGLOT", bonus));
		player.getAccount().addGold(bonus);		
	}
	@Override
	public desktop_fields.Field pushToGUI(int position){
		this.position = position;
		parkingLot = new desktop_fields.Street.Builder().setBgColor(new Color(223f/255, 255f/255, 43f/255)).build();
		parkingLot.setDescription(this.getDescription());
		parkingLot.setTitle(this.getName());
		parkingLot.setSubText("Payout: " + this.bonus);
		return parkingLot;
	}
	
	
	@Override
	public String toString() {
		return "Refuge [refuge=" + parkingLot + ", bonus=" + bonus + ", position=" + position
				+ ", getName()=" + getName() + ", getPosition()=" + getPosition() + "]";
	}
}
