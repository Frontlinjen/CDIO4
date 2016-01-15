package slots;

public class BreweryData extends OwnableData{
	
	private int baserent;
	
	public BreweryData(int rent, int translateID, int price, int pawnvalue) {
		super(translateID, price, pawnvalue);
		this.baserent = rent;
	}
	
	public String toString(){
		return "baserent=" + baserent;
	}
}
