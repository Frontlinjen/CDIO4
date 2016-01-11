package Chancecards;

import game.Player;
import game.Prison;

public class ChanceCardGoToPrisonController extends ChanceCardController{
	private Prison prison;
	private ChanceCardGoToPrisonData chanceCardData;
	
	public ChanceCardGoToPrisonController(ChanceCardGoToPrisonData chanceCardData, Prison prison) {
		this.chanceCardData = chanceCardData;
		this.prison = prison;
	}
	
	@Override
	public void onDrawn(Player player) {
		prison.addInmate(player);
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
