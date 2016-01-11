package Chancecards;

import game.Player;

public class ChanceCardOutOfPrisonController extends ChanceCardController{
	
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
		
		return null;
	}

}
