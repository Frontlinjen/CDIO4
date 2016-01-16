package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class FleetController extends OwnableController{
	private FleetData fleetData;
	private final int RENT[] = {500, 1000, 2000, 4000};
	public FleetController(FleetData data)
	{
		super((OwnableData)data);
		fleetData = data;
	}		

	@Override
	public desktop_fields.Field pushToGUI(int position) {
		fleetData.setPosition(position);
		guiField = new desktop_fields.Shipping.Builder().setRent(String.format("%d, %d, %d, %d", RENT[0], RENT[1], RENT[2], RENT[3])).setBgColor(new Color(144f/255,211f/255, 212f/255)).build();
		guiField.setTitle(fleetData.getName());
		guiField.setDescription(getDescription());
		guiField.setSubText("" + fleetData.price);
		return guiField;
	}
	
	@Override
	public int getRent()
	{
		Player owner = fleetData.getOwner();
		if(owner==null)
			return RENT[0];
		return RENT[owner.getProperty().getFleetOwned()-1];
		
	}
	@Override
	public int getWorth() {
		return fleetData.getPrice();
	}
	@Override
	public String getDescription() {
		
		return Translator.getString("FLEETDSC");
	}
	@Override
	public FIELDGROUPS getFieldGroup() {
		return FIELDGROUPS.FLEET;
	}
	@Override
	protected void chargeRent(Player player) {
		GUI.showMessage(Translator.getString("PAYTHEOWNER", getRent()));
		player.getAccount().transferTo(fleetData.getOwner().getAccount(), getRent());
		
	}
	@Override
	protected void registerOwner() {
		fleetData.getOwner().getProperty().addFleet(this);
	}

	@Override
	protected void UnRegisterOwner() {
		fleetData.getOwner().getProperty().removeFleet(this);
	}
	
	public String toString(){
		return "RENT[]=" + RENT + "getRent()=" + getRent();
	}

}
