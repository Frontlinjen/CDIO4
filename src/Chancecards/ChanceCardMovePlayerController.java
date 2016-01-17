package Chancecards;

import game.Player;

public class ChanceCardMovePlayerController extends ChanceCardController{
	private ChanceCardMovePlayerData chanceCardData;
	
	public ChanceCardMovePlayerController(ChanceCardMovePlayerData chanceCardData) {
		super(chanceCardData);
		this.chanceCardData = chanceCardData;
	}
	
	@Override
	public boolean onDrawn(Player player) {
		player.setNextPosition(chanceCardData.getFieldPosition(), chanceCardData.getCashInAtStart());
		return false;
	}

	@Override
	public String toString() {
		return chanceCardData.toString();
	}


}
