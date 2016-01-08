package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class ParkingLot extends Field{


	private int bonus;
	desktop_fields.Street parkingLot; 
	
	
	public ParkingLot(int i, int bonus) {
		super(i);
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
		return "ParkingLot [parkingLot=" + parkingLot + ", bonus=" + bonus + ", position=" + position
				+ ", getName()=" + getName() + ", getPosition()=" + getPosition() + "]";
	}
}
