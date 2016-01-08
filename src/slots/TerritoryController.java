package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class TerritoryController extends OwnableController {
	desktop_fields.Street territory;
	TerritoryData territoryData;

	TerritoryController(TerritoryData data)
	{
		super((OwnableData)data);
		territoryData = data;

	}
	@Override
	public void landOnField(Player player) {
		/**
		 * player lands on territory. If the field is owned, the player 
		 * will have to pay the rent, depending on which field it is.
		 * If the field is not owned, the chance to buy it, is given.
		 */
		territory.displayOnCenter();
		if(hasOwner())
		{
			if(getOwner()!=player)
			{
				GUI.showMessage(Translator.getString("PAYTHEOWNER", rent));
				player.getAccount().transferTo(getOwner().getAccount(), rent);
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
		territory = new desktop_fields.Street.Builder().setRent(rent+"").setBgColor(new Color(68f/255, 255f/255, 43f/255)).build();
		territory.setDescription(this.getDescription());
		territory.setTitle(this.getName());
		territory.setSubText(this.price+"");
		return territory;
	}


}
