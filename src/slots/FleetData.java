package slots;

public class FleetData extends OwnableData{
	private final int[] RENT = {500, 1000, 2000, 4000};
	private int pawnvalue;
	
	public FleetData(int i, int price, int pawnvalue) {
		super(i, price);
		this.pawnvalue = pawnvalue;
	}
	public int getRent(int shipAmount)
	{
		return RENT[shipAmount];
	}
	
	public String toString(){
		return "RENT[]=" + RENT;
	}
}
