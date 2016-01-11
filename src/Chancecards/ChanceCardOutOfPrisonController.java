package Chancecards;

import game.Player;

public class ChanceCardOutOfPrisonController extends ChanceCardController{
	private ChanceCardData chanceCardData;
	
	public ChanceCardOutOfPrisonController(ChanceCardData chanceCardData){
		this.chanceCardData = chanceCardData;
	}
	
	@Override
	public boolean onDrawn(Player player) {
		if(player.hasGetOutOfPrisonCard() == false) 
		{
			player.setHasGetOutOfPrisonCard(true);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
