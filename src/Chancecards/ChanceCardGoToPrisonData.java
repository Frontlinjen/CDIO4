package Chancecards;

public class ChanceCardGoToPrisonData extends ChanceCardData{
	private int prisonLocation;
	
	public ChanceCardGoToPrisonData(int translateID, int prisonLocation) {
		super(translateID);
		this.prisonLocation = prisonLocation;
	}

	public int getPrisonLocation() {
		return prisonLocation;
	}
	
	public String toString(){
		return "getPrisonLocation()=" + getPrisonLocation();
	}
}
