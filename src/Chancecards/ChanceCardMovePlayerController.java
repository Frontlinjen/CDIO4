package Chancecards;

import game.Player;

public class ChanceCardMovePlayerController extends ChanceCardController{
	private ChanceCardMovePlayerData chanceCardData;
	
	public ChanceCardMovePlayerController(ChanceCardMovePlayerData chanceCardData) {
		this.chanceCardData = chanceCardData;
	}
	
	@Override
	public void onDrawn(Player player) {
		player.setNextPosition(chanceCardData.getFieldPosition(), chanceCardData.getCashInAtStart());
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
