package game;


public class Player {
	/**
	 * Holds all information about the player, including a reference to the account.
	 */
	private String name;
	private int position = 0;
	private int nextPosition = 0;
	private boolean cashAtStart = true;
	private boolean getOutOfPrisonCard = false;
	private Account account;
	private Property propertyOwned = new Property();
	
	public int getNextPosition() {
		return nextPosition;
	}

	public void setNextPosition(int nextPosition, boolean passStart) {
		this.nextPosition = nextPosition;
		cashAtStart = passStart;
	}
	public void moveToNextPosition()
	{
		int distance = nextPosition-position;
		if(distance < 0)
		{
			moveToPosition(distance+40);
		}
		else
		{
			moveToPosition(distance);
		}
		//Fixes an error which accours when the nextPosition has been sat past start
		nextPosition = position;
		
	}


	/**
	 * Each player has their own set of dice which keeps track of their rolls. 
	 */
	
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
	
	private void moveToPosition (int afstand){
		final int ANTALSLOTS = 40; 
		final int STARTBONUS = 4000;
		position += afstand; 
		//add the moved distance to the old position.
		
		/**
		 * Decide wether or not the new position exeeds the board.
		 * If it does, it take the amount of fields from the position
		 * to find the new position.
		 */
		if(position >= ANTALSLOTS){ //since we are 0-index, 40 is the 0th field
			position -= ANTALSLOTS;
			if(cashAtStart)
				account.addGold(STARTBONUS);
		}
	}
	
	public void move(int afstand, boolean cashAtStart) {
		nextPosition += afstand;
		this.cashAtStart = cashAtStart;
	}
	
	public int getPosition(){
		return position;
	}
	
	public boolean hasGetOutOfPrisonCard() {
		return getOutOfPrisonCard;
	}
	
	public void setHasGetOutOfPrisonCard(boolean b) {
		getOutOfPrisonCard = b;
	}

	@Override
	public String toString() {
		return "Player [, getName()=" + getName() + ", getAccount()=" + getAccount() + ", getProperty().getPropertyCount()="
				+ getProperty().getPropertyCount() + ", getPosition()=" + getPosition() + "]";
	}
}
 