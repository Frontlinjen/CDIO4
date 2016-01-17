package Chancecards;

public class ChanceCardMoveToNextFleetData extends ChanceCardData{
	private int[] fleetPositions;
	private boolean cashAtStart;
	
	public ChanceCardMoveToNextFleetData(int translateID, int[] fleetPositions, boolean cashAtStart) {
		super(translateID);
		this.fleetPositions = fleetPositions;
		this.cashAtStart = cashAtStart;
	}

	public int[] getFleetPositions() {
		return fleetPositions;
	}
}
