package Chancecards;

import game.Player;

public class ChanceCardOutOfPrisonController extends ChanceCardController{
	private ChanceCardData chanceCardData;
	
	public ChanceCardOutOfPrisonController(ChanceCardData chanceCardData){
		super(chanceCardData);
		this.chanceCardData = chanceCardData;
	}
	
	@Override
	public boolean onDrawn(Player player) {
		if(player.hasGetOutOfPrisonCard() == false) 
		{
			player.setHasGetOutOfPrisonCard(true);
			return false;
		}
		else
		{
			return true;
		}
	}

	

}
