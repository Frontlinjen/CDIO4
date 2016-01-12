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
	/*
	 * If a player owns a territory it will enable him to purchase a house.
	 */
	public void buyHouse(Player player){
		if(territoryData.getOwner() == player){
			if(territoryData.getHouses() < 5){
				if(player.getAccount().withdraw(territoryData.price)){
					player.getAccount().removeGold(territoryData.getHouseCost());
					territoryData.addHouse();
					GUI.showMessage(Translator.getString("HOUSECONFIRM"));
				}
				else{
					GUI.showMessage(Translator.getString("YOUCANNOTAFFORDTHAT"));	
				}
			}
			else{
				GUI.showMessage(Translator.getString("BUILDLIMIT"));
			}
		}
		else{
			GUI.showMessage(Translator.getString("YOUARENOTTHEOWNER"));
		}
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
		territory.setDescription(getDescription());
		territory.setTitle(territoryData.getName());
		territory.setSubText(Integer.toString(territoryData.getPrice()));
		return territory;
	}
	public int getHouseAmount()
	{
		if(territoryData.getHouses()<5)
			return territoryData.getHouses();
		else
			return 0;
	}
	public int getHotelAmount()
	{
		return territoryData.getHouses()==5 ? 1 : 0;
	}

	@Override
	public int getWorth() {
		int territoryWorth = 0;
		return territoryWorth + territoryData.getPrice()+(territoryData.getHouses()*territoryData.getHouseCost());
	}
	@Override
	public String getDescription() {
		if(territoryData.getGroupID()==0 || territoryData.getGroupID()==1) {
			return Translator.getString("SLOTDSC1");
		}
		else if(territoryData.getGroupID()==2 || territoryData.getGroupID()==3) {
			return Translator.getString("SLOTDSC2");
		}
		else if(territoryData.getGroupID()==4 || territoryData.getGroupID()==5) {
			return Translator.getString("SLOTDSC3");
		}
		else {
			return Translator.getString("SLOTDSC4");
		}
	}
	@Override
	public FIELDGROUPS getFieldGroup() {
		return FIELDGROUPS.values()[territoryData.getGroupID()];
	}



}
