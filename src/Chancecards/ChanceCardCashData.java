package Chancecards;

import game.Account;
import game.Player;

public class ChanceCardCashData extends ChanceCardData{
	private int money;
	
	public ChanceCardCashData(int translateID, int tax) {
		super(translateID);
		this.money = tax;
	}
	
	public int getMoney() {
		return money;
	}
}
