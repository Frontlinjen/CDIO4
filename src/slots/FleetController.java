package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class FleetController extends OwnableController{
	desktop_fields.Street fleet;
	FleetData fleetData;
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
	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
