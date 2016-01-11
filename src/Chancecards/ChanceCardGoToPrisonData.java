package Chancecards;

import game.Prison;

public class ChanceCardGoToPrisonData extends ChanceCardData{
	private int prisonLocation;
	
	public ChanceCardGoToPrisonData(int translateID, int prisonLocation) {
		super(translateID);
		this.prisonLocation = prisonLocation;
	}

	public int getPrisonLocation() {
		return prisonLocation;
	}
}
