package slots;

import game.Player;

public abstract class OwnableData extends FieldData {

	
	protected int price;
	private Player owner;
	public OwnableData(int i, int price) {
		super(i);
		this.price = price;
	}
	public int getPrice()
	{
		return price;
	}
	public void setOwner(Player newOwner)
	{
		owner = newOwner;
	}
	public void removeOwner()
	{
		owner = null;
	}
	public Player getOwner()
	{
		return owner;
	}
	public boolean hasOwner()
	{
		return (owner!=null);
	}
	

		
}
