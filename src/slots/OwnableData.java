package slots;

import game.Player;

public class OwnableData extends FieldData {

	private boolean pawned = false;
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
	
	public boolean pawned()
	{
		return pawned;
	}
	
	public void setPawned(boolean pawned)
	{
		pawned = pawned();
	}
	
}
