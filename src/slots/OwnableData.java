package slots;

import game.Player;

public abstract class OwnableData extends FieldData {

	
	protected int price;
	private Player owner;
	private int pawnvalue;
	public OwnableData(int translateID, int price, int pawnvalue) {
		super(translateID);
		this.price = price;
		this.pawnvalue = pawnvalue;
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
	
	public int getPawnValue(){
		return pawnvalue;
	}
	
	public String toString(){
		return "getPrice()=" + getPrice() + ", getOwner()=" + getOwner();
	}
		
}
