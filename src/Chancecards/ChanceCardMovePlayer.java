package Chancecards;

import game.Player;

public class ChanceCardMovePlayer extends ChanceCard{
	private int fieldPosition;
	private boolean cashInAtStart;

	public ChanceCardMovePlayer(int translateID, int fieldPosition, boolean cashInAtStart) {
		super(translateID);
		this.fieldPosition = fieldPosition;
		this.cashInAtStart = cashInAtStart;
	}
	
	@Override
	public void onDrawn(Player player) {
		player.setNextPosition(fieldPosition, cashInAtStart);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
