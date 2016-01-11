package slots;

import game.*;
import desktop_resources.GUI;
import slots.FieldController;

public abstract class OwnableController extends FieldController{
	private OwnableData ownableData;
	private desktop_fields.Street LaborCamp;
	public OwnableController(OwnableData dat)
	{
		ownableData = dat;
	}
	
	public int getRent()
	{
		return 0;
	}
	
	public void removeOwner()
	{
		ownableData.removeOwner();
		GUI.removeOwner(ownableData.getPosition());

	}
	public void setOwner(Player owner) {
		/**
		 * General way to make the buyer of a field the owner.
		 */
		System.out.println(ownableData.getName() + " now has " + owner.getName() + " as their owner" + " at slot " + ownableData.getPosition());
		ownableData.setOwner(owner);
		GUI.setOwner(ownableData.getPosition(), owner.getName());
	}
	public boolean hasOwner()
	{
		return(ownableData.getOwner()!=null);
	}

	public boolean BuyField (Player visitor){
		/**
		 * General purchase procedure, with a withdrawal of money
		 * and a call to setOwner if the withdraw was completed.
		 */
		if(GUI.getUserLeftButtonPressed(Translator.getString("BUYFIELD", ownableData.getPrice()), Translator.getString("YES"), Translator.getString("NO"))){
			if(visitor.getAccount().withdraw(ownableData.getPosition())){
				setOwner(visitor);
				visitor.getProperty().addProperty(this);
				return true;
			}else{
				GUI.showMessage(Translator.getString("NOTENOUGHGOLD"));
			}
		}
		else{
			GUI.showMessage(Translator.getString("ENDTURN"));
		}
		return false;

		
	}
	
	public abstract int getWorth();

	@Override
	public String toString() {
		return "Ownable [price=" + ownableData.getPrice() + ", owner=" + ownableData.getOwner() + "]";
	}
}