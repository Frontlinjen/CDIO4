package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;
import game.Property;

public class TerritoryController extends OwnableController {
	private TerritoryData territoryData;

	public TerritoryController(TerritoryData data)
	{
		super(data);
		territoryData = data;
	}
	public void removeHouses()
	{
		territoryData.resetHouses();
		GUI.setHouses(territoryData.getPosition(), 0);
		GUI.setHotel(territoryData.getPosition(), false);
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
					int houseCount = territoryData.getHouses();
					
					if(houseCount < 5)
					{
						GUI.setHouses(territoryData.getPosition(), territoryData.getHouses());
					}
					else
					{
						GUI.setHotel(territoryData.getPosition(), true);
					}
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

		Color[] colors = {Color.blue, Color.orange, Color.green, Color.lightGray, Color.red, Color.white, Color.yellow, /*dark purple*/ new Color(155, 67, 196)};
		Color thisColor = colors[getFieldGroup().ordinal()];
		territoryData.setPosition(position);
		
		guiField = new desktop_fields.Street.Builder().setRent(Integer.toString(territoryData.getRent())).setBgColor(thisColor).build();
		guiField.setDescription(getDescription());
		guiField.setTitle(territoryData.getName());
		guiField.setSubText(Integer.toString(territoryData.getPrice()));
		return guiField;
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
		return territoryData.getRent();
	}
	
	public Property property = new Property();
	
	@Override
	protected void chargeRent(Player player) {
			GUI.showMessage(Translator.getString("PAYTHEOWNER", this.getRent()));
			player.getAccount().transferTo(territoryData.getOwner().getAccount(), this.getRent());
			
	}
	@Override
	protected void registerOwner() {
		territoryData.getOwner().getProperty().addTerritory(this);
		
	}
	@Override
	protected void UnRegisterOwner() {
		territoryData.getOwner().getProperty().removeTerritory(this);
		
	}

	public String toString(){
		return "getUpgradeCosts()=" + getUpgradeCosts() + ", getHouseAmount()=" + getHouseAmount() + ", getHotelAmount()=" + getHotelAmount() + ", getWorth()=" + getWorth() + ", getDescription()=" + getDescription() + ", getFieldGroup()=" + getFieldGroup() + "getRent()=" + getRent();
	}

}
