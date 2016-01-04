package slots;

import game.*;
import desktop_resources.GUI;
import slots.Field;

public abstract class Ownable extends Field{

	public Ownable(int i, Types type, int price) {
		super(i, type);
		this.price = price;
	}

	protected int price;
	private Player owner;
	
	public abstract int getRent();

	public Player getOwner() {
		return owner;
	}
	public void removeOwner()
	{
		this.owner = null;
		GUI.removeOwner(getPosition());
		
	}
	public void setOwner(Player owner) {
		/**
		 * General way to make the buyer of a field the owner.
		 */
		System.out.println(getName() + " now has " + owner.getName() + " as their owner" + " at slot " + getPosition());
		this.owner = owner;
		GUI.setOwner(getPosition(), owner.getName());
	}
	public boolean hasOwner()
	{
		return(getOwner()!=null);
	}
	
	public boolean BuyField (Player visitor){
		/**
		 * General purchase procedure, with a withdrawal of money
		 * and a call to setOwner if the withdraw was completed.
		 */
		if(GUI.getUserLeftButtonPressed(Translator.getString("BUYFIELD", price), Translator.getString("YES"), Translator.getString("NO"))){
			if(visitor.getAccount().withdraw(price)){
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

	@Override
	public String toString() {
		return "Ownable [price=" + price + ", owner=" + owner + "]";
	}
}