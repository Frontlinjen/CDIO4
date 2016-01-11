package Chancecards;

public class ChanceCardBuildingTaxData extends ChanceCardData{
	private int houseTax;
	private int hotelTax;
	
	public ChanceCardBuildingTaxData(int translateID, int houseTax, int hotelTax) {
		super(translateID);
		this.houseTax = houseTax;
		this.hotelTax = hotelTax;
	}
}
