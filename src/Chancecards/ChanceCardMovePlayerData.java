package Chancecards;

public class ChanceCardMovePlayerData extends ChanceCardData{
	private int fieldPosition;
	private boolean cashInAtStart;

	public ChanceCardMovePlayerData(int translateID, int fieldPosition, boolean cashInAtStart) {
		super(translateID);
		this.fieldPosition = fieldPosition;
		this.cashInAtStart = cashInAtStart;
	}
	
	public int getFieldPosition() {
		return fieldPosition;
	}
	
	public boolean getCashInAtStart() {
		return cashInAtStart;
	}

	@Override
	public String toString() {
		return "ChanceCardMovePlayerData [fieldPosition=" + fieldPosition + ", cashInAtStart=" + cashInAtStart + "]";
	}
}
