package Chancecards;

import game.Player;

public class ChanceCardMovePlayerRelativeController extends ChanceCardController{
	private ChanceCardMovePlayerRelativeData chanceCardData;
	
	public ChanceCardMovePlayerRelativeController(ChanceCardMovePlayerRelativeData chanceCardData) {
		this.chanceCardData = chanceCardData;
	}
	
	@Override
	public void onDrawn(Player player) {
		player.move(chanceCardData.getDistance(), chanceCardData.getCashAtStart());
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
