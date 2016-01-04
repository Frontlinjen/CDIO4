package game;


public class Player {
	/**
	 * Holds all information about the player, including a reference to th account.
	 */
	private String name;
	private int position = 0;
	/**
	 * Each player has their own set of dice which keeps track of their rolls. 
	 */
	private Account account;
	private Property propertyOwned = new Property();
	
	DiceCup dice = new DiceCup(2);
	
	public DiceCup getDice()
	{
		return dice;
	}
	
	public Player(String s)
	{
		name = s;
		account = new Account(30000, name);
	}
	public String getName()
	{
		return name;	
	}
	public Account getAccount()
	{
		return account;
	}
	public Property getProperty()
	{
		return propertyOwned;
	}
	
	public void move (int afstand){
		final int ANTALSLOTS = 21;
		position += afstand; 
		//add the moved distance to the old position.
		
		if(position > ANTALSLOTS){ 
			position -= ANTALSLOTS;
	/**
	 * Decide wether or not the new position exeeds the board.
	 * If it does, it take the amount of fields from the position
	 * to find the new position.
	 */
		}
	}
	
	public int getPosition(){
		return position;
	}

	@Override
	public String toString() {
		return "Player [, getName()=" + getName() + ", getAccount()=" + getAccount() + ", getProperty().getPropertyCount()="
				+ getProperty().getPropertyCount() + ", getPosition()=" + getPosition() + "]";
	}
}
 