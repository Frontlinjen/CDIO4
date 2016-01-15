package slots;

public class BreweryData extends OwnableData{
	
	private int baserent;
	private int pawnvalue;
	
	public BreweryData(int rent, int translateID, int price, int pawnvalue) {
		super(translateID, price);
		this.baserent = rent;
		this.pawnvalue = pawnvalue;
	}
	
	public int getPawnValue(){
		return pawnvalue;
	}
	
	public String toString(){
		return "baserent=" + baserent;
	}
}
