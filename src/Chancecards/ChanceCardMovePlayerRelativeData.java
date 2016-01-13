package Chancecards;

public class ChanceCardMovePlayerRelativeData extends ChanceCardData{
	private int distance;
	private boolean cashAtStart;
	
	public ChanceCardMovePlayerRelativeData(int translateID, int distance, boolean cashAtStart) {
		super(translateID);
		this.distance = distance;
		this.cashAtStart = cashAtStart;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public boolean getCashAtStart() {
		return cashAtStart;
	}
}
