package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class ParkinglotController extends FieldController{
	desktop_fields.Street parkingLot; 
	ParkinglotData parkinglotData;
	ParkinglotController(ParkinglotData data)
	{
		parkinglotData = data;
	}

	@Override
	public void landOnField(Player player) {
		//Player lands on the Parking lot field and is given gold.
		parkingLot.displayOnCenter();
		GUI.showMessage(Translator.getString("LANDONPARKINGLOT", parkinglotData.getBonus()));
		player.getAccount().addGold(parkinglotData.getBonus());		
	}
	@Override
	public desktop_fields.Field pushToGUI(int position){
		parkinglotData.setPosition(position);
		parkingLot = new desktop_fields.Street.Builder().setBgColor(new Color(223f/255, 255f/255, 43f/255)).build();
		parkingLot.setDescription(parkinglotData.getDescription());
		parkingLot.setTitle(parkinglotData.getName());
		parkingLot.setSubText("Payout: " + parkinglotData.getBonus());
		return parkingLot;
	}
}
