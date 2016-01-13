package Chancecards;

import game.Player;

public class ChanceCardMovePlayerRelativeController extends ChanceCardController{
	private ChanceCardMovePlayerRelativeData chanceCardData;
	
	public ChanceCardMovePlayerRelativeController(ChanceCardMovePlayerRelativeData chanceCardData) {
		super(chanceCardData);
		this.chanceCardData = chanceCardData;
	}
	
	@Override
	public boolean onDrawn(Player player) {
		player.move(chanceCardData.getDistance(), chanceCardData.getCashAtStart());
		return false;
	}

	

}
