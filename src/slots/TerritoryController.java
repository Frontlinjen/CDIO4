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
	public int getUpgradeCosts()
	{
		return territoryData.getHouseCost();
	}
	/*
	 * If a player owns a territory it will enable him to purchase a house.
	 */
	public void buyHouse(Player player){
		if(territoryData.getOwner() == player){
			if(territoryData.getHouses() < 5){
				if(player.getAccount().withdraw(getUpgradeCosts())){
		
					territoryData.addHouse();
					GUI.showMessage(Translator.getString("HOUSECONFIRM"));
				}
				else{
					GUI.showMessage(Translator.getString("YOUCANNOTAFFORDTHAT"));	
				}
			}
			else{
				GUI.showMessage(Translator.getString("FULLYUPGRADED"));
			}
		}
		else{
			GUI.showMessage(Translator.getString("YOUARENOTTHEOWNER"));
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
		return territoryData.getHouses()>4 ? 1 : 0;
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
	@Override
	public int getRent() {
		return territoryData.getRent()+territoryData.getRent()*(int)(Math.pow(territoryData.getHouses(), 2));
	}
	@Override
	protected void chargeRent(Player player) {
		GUI.showMessage(Translator.getString("PAYTHEOWNER", territoryData.getRent()));
		player.getAccount().transferTo(territoryData.getOwner().getAccount(), territoryData.getRent());
		
	}
	@Override
	protected void registerOwner() {
		territoryData.getOwner().getProperty().addTerritory(this);
		
	}



}
