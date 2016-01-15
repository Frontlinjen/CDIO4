package slots;

import game.*;
import desktop_resources.GUI;
import slots.FieldController;



public abstract class OwnableController extends FieldController{
	public enum FIELDGROUPS
	{
		BLUE,
		PINK,
		GREEN,
		GRAY,
		RED,
		WHITE,
		YELLOW,
		PURPLE,
		FLEET,
		BREWERY
	}
	private OwnableData ownableData;
	private boolean pawned = false;
	protected desktop_fields.Ownable guiField;
	
	public OwnableController(OwnableData dat)
	{
		super(dat);
		ownableData = dat;
	}
	
	public abstract int getRent();
	protected abstract void chargeRent(Player player);
	protected abstract void registerOwner();
	protected abstract void UnRegisterOwner();
	
	public void transferOwnershipTo(Player player)
	{
		
	}
	@Override
	final public void landOnField(Player player)
	{
			/**
			 * Player lands on brewery.
			 * If field is owned, he pays an amount depending on a roll with
			 * two dice times the amount of labor camps owned by the owner.
			 * If field is not owned, player can choose to buy it.
			 */
			guiField.displayOnCenter();
			if(hasOwner()){
				if(ownableData.getOwner()!=player)
				{
					if(!pawned)
					{
						chargeRent(player);
					}
					else
					{
						GUI.showMessage(Translator.getString("PAWNEDFIELD"));
					}
					
				}else{
					GUI.showMessage(Translator.getString("YOURFIELD"));
				}
			}else{
				if(buyField(player))
				{
					GUI.showMessage(Translator.getString("BOUGHTFIELD",ownableData.getName(), ownableData.getPrice()));
				}
			}
	}
	
	public Player getOwner()
	{
		return ownableData.getOwner();
	}
	//Should be used when a player goes to 0 cash
	public void reset()
	{
		ownableData.removeOwner();
		GUI.removeOwner(ownableData.getPosition());
		setPawned(false);
		
	}
	//Should never be used when looping over a player's properties
	public void removeOwner()
	{
		UnRegisterOwner();
		ownableData.removeOwner();
		GUI.removeOwner(ownableData.getPosition());

	}
	public void setOwner(Player owner) {
		/**
		 * General way to make the buyer of a field the owner.
		 */
		
		System.out.println(ownableData.getName() + " now has " + owner.getName() + " as their owner" + " at slot " + ownableData.getPosition());
		ownableData.setOwner(owner);
		registerOwner();
		GUI.setOwner(ownableData.getPosition(), owner.getName());
	}
	public boolean hasOwner()
	{
		return(ownableData.getOwner()!=null);
	}

	public boolean buyField (Player visitor){
		/**
		 * General purchase procedure, with a withdrawal of money
		 * and a call to setOwner if the withdraw was completed.
		 */
		if(GUI.getUserLeftButtonPressed(Translator.getString("BUYFIELD", ownableData.getPrice()), Translator.getString("YES"), Translator.getString("NO"))){
			if(visitor.getAccount().withdraw(ownableData.getPrice())){
				setOwner(visitor);
				return true;
			}else{
				GUI.showMessage(Translator.getString("NOTENOUGHGOLD"));
			}
		}
		else{
			GUI.showMessage(Translator.getString("CONTINUETURN"));
		}
		return false;

		
	}
	public boolean pawned()
	{
		return pawned;
	}
	
	public void setPawned(boolean pawned)
	{
		this.pawned = pawned;
	}
	
	public int getPawnValue(){
		return ownableData.getPawnValue();
	}
	
	public abstract FIELDGROUPS getFieldGroup();
	public abstract int getWorth();

	@Override
	public String toString() {
		return "Ownable [price=" + ownableData.getPrice() + ", owner=" + ownableData.getOwner() + "]";
	}
}