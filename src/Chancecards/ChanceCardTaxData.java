package Chancecards;

import game.Account;
import game.Player;

public class ChanceCardTaxData extends ChanceCardData{
	private int tax;
	private Account parkinglotAccount;
	
	public ChanceCardTaxData(int translateID, int tax, Account account) {
		super(translateID);
		this.tax = tax;
		this.parkinglotAccount = account;
	}
	
	public int getTax() {
		return tax;
	}
	
	public Account getAccount() {
		return parkinglotAccount;
	}
}
