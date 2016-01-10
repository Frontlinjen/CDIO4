package Chancecards;

import game.Player;

public abstract class ChanceCard {
	private String description;
	private Player player;
	
	public abstract void onDrawn(Player player);
	
	public String getDescription() 
	{
		return description;
	}
	
	public void chanceCard(String description)
	{
		
	}
}
