package Chancecards;

import game.Account;
import game.Player;

public class ChanceCardCashBonus extends ChanceCard{
	private int bonus;
	private Account parkinglotAccount;

	public ChanceCardCashBonus(int translateID, int bonus, Account account) {
		super(translateID);
		this.bonus = bonus;
		this.parkinglotAccount = account;
	}
	
	@Override
	public void onDrawn(Player player) {
		if(bonus>0) 
		{
		player.getAccount().addGold(bonus);
		}
		else
		{
			player.getAccount().transferTo(parkinglotAccount, Math.abs(bonus));
		}
	}

	@Override
	public String getDescription() {
		
		return null;
	}
	
	
	
}
