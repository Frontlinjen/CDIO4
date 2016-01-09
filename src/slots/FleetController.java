package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class FleetController extends OwnableController{
	private desktop_fields.Street fleet;
	private FleetData fleetData;
	private final int RENT[] = {500, 1000, 2000, 4000};
	FleetController(FleetData data)
	{
		super((OwnableData)data);
		fleetData = data;
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
			if(fleetData.getOwner()!=player)
			{
				GUI.showMessage(Translator.getString("PAYTHEOWNER", getRent()));
				player.getAccount().transferTo(fleetData.getOwner().getAccount(), getRent());
			}else{
				GUI.showMessage(Translator.getString("YOURFIELD"));
			}
		}else{
			if(BuyField(player)){
				GUI.showMessage(Translator.getString("BOUGHTFIELD",fleetData.getName(), fleetData.getPrice()));
			}	
		}
	}

	@Override
	public desktop_fields.Field pushToGUI(int position) {
		fleetData.setPosition(position);
		fleet = new desktop_fields.Street.Builder().setRent(String.format("%d, %d, %d, %d", RENT[0], RENT[1], RENT[2], RENT[3])).setBgColor(new Color(144f/255,211f/255, 212f/255)).build();
		fleet.setTitle(fleetData.getName());
		fleet.setDescription(fleetData.getDescription());
		fleet.setSubText("" + fleetData.price);
		return fleet;
	}
	@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}


}
