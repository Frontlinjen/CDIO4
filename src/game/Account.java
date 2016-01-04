package game;

import desktop_resources.GUI;

public class Account {	
	private int gold = 0;
	private String ownerName;
	public Account(int balance, String name)
	{
		ownerName = name;
		gold = balance;
	}
	public int getGold() {
		return gold;
	}
	/** 
	 * Attempts to withdraw gold from the player's account.
	 * @param gold
	 * The amount of gold that needs to be withdrawn 
	 * @return
	 * true if the withdrawal was sucessful, false if not. 
	 */
	public boolean withdraw(int gold)
	{
		if(this.gold<gold)
		{
			return false;
		}
		removeGold(gold);
		return true;
	}
	/**
	 * Sets the balance in the account, also updates the GUI to the new amount
	 * @param gold How much gold the account should be set to
	 */
	public void setGold(int gold) {
		this.gold = gold;
		if(getGold()<0)
		{
			setGold(0);
		}
		GUI.setBalance(ownerName, getGold());
	}
	/**
	 * Transfers as much money as possible to the other account. If the balance is too low
	 * then the reminder of the money is transfered and the account goes to 0.
	 * @param other
	 * The other account to transfer money to
	 * @param amount
	 * how much money should be transfered 
	 */
	public void transferTo(Account other, int amount){
		if((getGold()-amount)<=0){
			other.addGold(getGold());
			setGold(0);
		}else{
			removeGold(amount);
			other.addGold(amount);
		}
	}
	
	public void addGold(int gold){
		setGold(gold+getGold());
	}
	
	public void removeGold(int gold){
		setGold(getGold()-gold);
	}
	
	@Override
	public String toString() {
		return ownerName + "'s account currently contains: " + gold + " gold";
	}
}
