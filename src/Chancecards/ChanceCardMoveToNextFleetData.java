package Chancecards;

import java.util.Arrays;

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

	@Override
	public String toString() {
		return "ChanceCardMoveToNextFleetData [fleetPositions=" + Arrays.toString(fleetPositions) + ", cashAtStart="
				+ cashAtStart + "]";
	}
}
