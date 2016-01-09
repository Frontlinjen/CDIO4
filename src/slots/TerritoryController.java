package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class TerritoryController extends OwnableController {
	private desktop_fields.Street territory;
	private TerritoryData territoryData;

	public TerritoryController(TerritoryData data)
	{
		super(data);
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
			if(territoryData.getOwner()!=player)
			{
				GUI.showMessage(Translator.getString("PAYTHEOWNER", territoryData.getRent()));
				player.getAccount().transferTo(territoryData.getOwner().getAccount(), territoryData.getRent());
			}else{
				GUI.showMessage(Translator.getString("YOURFIELD"));
			}
		}else{
			if(BuyField(player)){
				GUI.showMessage(Translator.getString("BOUGHTFIELD", territoryData.getName(), territoryData.getPrice()));
			}
		}
	}

	@Override
	public desktop_fields.Field pushToGUI(int position) {
		territoryData.setPosition(position);
		territory = new desktop_fields.Street.Builder().setRent(Integer.toString(territoryData.getRent())).setBgColor(new Color(68f/255, 255f/255, 43f/255)).build();
		territory.setDescription(territoryData.getDescription());
		territory.setTitle(territoryData.getName());
		territory.setSubText(Integer.toString(territoryData.getPrice()));
		return territory;
	}



}
