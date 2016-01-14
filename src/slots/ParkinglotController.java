package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class ParkinglotController extends FieldController{
	private desktop_fields.Street parkingLot; 
	private ParkinglotData parkinglotData;
	public ParkinglotController(ParkinglotData data)
	{
		super(data);
		parkinglotData = data;
	}

	@Override
	public void landOnField(Player player) {
		
		/*
		 * Player lands on the Parking lot field and is given the bonus. 
		 * The bonus is at minimum a 1000 but will get all the penality money,
		 * which is continously added throughout the game.
		 */
		
		parkingLot.displayOnCenter();
		GUI.showMessage(Translator.getString("LANDONPARKINGLOT", parkinglotData.getAccount().getGold()));
		parkinglotData.TransferBonus(player.getAccount());	
	}
	@Override
	public desktop_fields.Field pushToGUI(int position){
		parkinglotData.setPosition(position);
		parkingLot = new desktop_fields.Street.Builder().setBgColor(new Color(223f/255, 255f/255, 43f/255)).build();
		parkingLot.setDescription(getDescription());
		parkingLot.setTitle(parkinglotData.getName());
		parkingLot.setSubText(Translator.getString("PARKINGLOTSUB", parkinglotData.getAccount().getGold()));
		return parkingLot;
	}
	@Override
	public String getDescription() {
		
		return Translator.getString("PARKINGLOTDSC");
	}
	
	public String toString(){
		return "getDescription()=" + getDescription();
	}
}
