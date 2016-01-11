package Chancecards;

import game.Account;

public class ChanceCardBuildingTaxData extends ChanceCardData{
	private int houseTax;
	private int hotelTax;
	private Account parkinglotAccount;
	
	public ChanceCardBuildingTaxData(int translateID, int houseTax, int hotelTax, Account parkinglotAccount) {
		super(translateID);
		this.houseTax = houseTax;
		this.hotelTax = hotelTax;
		this.parkinglotAccount = parkinglotAccount;
	}
	
	public int getHouseTax() {
		return houseTax;
	}
	
	public int getHotelTax() {
		return hotelTax;
	}
	
	public Account getParkinglotAccount(){
		return parkinglotAccount;
	}
}
