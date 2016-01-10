package Chancecards;

import game.Player;

public class ChanceCardOutOfPrison extends ChanceCard{
	
	public ChanceCardOutOfPrison(int translateID) {
		super(translateID);
		
	}
	
	@Override
	public void onDrawn(Player player) {
		if(player.hasGetOutOfPrisonCard() == false) 
		{
			player.setHasGetOutOfPrisonCard(true);
		}
		else
		{
			
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
