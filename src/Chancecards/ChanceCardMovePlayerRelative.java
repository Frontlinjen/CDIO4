package Chancecards;

import game.Player;

public class ChanceCardMovePlayerRelative extends ChanceCard{
	private int distance;
	private boolean cashAtStart;
	
	public ChanceCardMovePlayerRelative(int translateID, int distance, boolean cashAtStart) {
		super(translateID);
		this.distance = distance;
		this.cashAtStart = cashAtStart;
	}

	@Override
	public void onDrawn(Player player) {
		player.move(distance, cashAtStart);
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
