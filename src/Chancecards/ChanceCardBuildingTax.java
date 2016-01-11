package Chancecards;

import game.Player;

public class ChanceCardBuildingTax extends ChanceCard{
	private int houseTax;
	private int hotelTax;
	
	public ChanceCardBuildingTax(int translateID, int houseTax, int hotelTax) {
		super(translateID);
		this.houseTax = houseTax;
		this.hotelTax = hotelTax;
	}

	@Override
	public void onDrawn(Player player) {
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
