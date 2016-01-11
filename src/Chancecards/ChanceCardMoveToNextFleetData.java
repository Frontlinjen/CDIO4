package Chancecards;

import game.Player;

public class ChanceCardMoveToNextFleetData extends ChanceCardData{
	int[] fleetPositions;
	
	public ChanceCardMoveToNextFleetData(int translateID, int[] fleetPositions) {
		super(translateID);
		this.fleetPositions = fleetPositions;
	}

	public int[] getFleetPositions() {
		return fleetPositions;
	}
}
