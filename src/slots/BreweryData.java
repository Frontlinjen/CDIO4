package slots;

public class BreweryData extends OwnableData{

	private int baseRent;
	
	
	public BreweryData(int baseRent, int translateID, int price) {
		super(translateID, price);
		this.baseRent = baseRent;
	}

}
