package slots;

public class FleetData extends OwnableData{
	private final int[] RENT = {500, 1000, 2000, 4000};
	public FleetData(int i, int price) {
		super(i, price);
		// TODO Auto-generated constructor stub
	}
	public int getRent(int shipAmount)
	{
		return RENT[shipAmount];
	}
	
}
