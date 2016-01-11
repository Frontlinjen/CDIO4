package Chancecards;

import game.Account;
import game.Player;

public class ChanceCardTax extends ChanceCard{
	private int tax;
	private Account parkinglotAccount;
	
	public ChanceCardTax(int translateID, int tax, Account account) {
		super(translateID);
		this.tax = tax;
		this.parkinglotAccount = account;
	}

	@Override
	public void onDrawn(Player player) {
		player.getAccount().transferTo(parkinglotAccount, tax);;
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
