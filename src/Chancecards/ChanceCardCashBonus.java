package Chancecards;

import game.Player;

public class ChanceCardCashBonus extends ChanceCard{
	protected int bonus;

	public ChanceCardCashBonus(int translateID, int bonus) {
		super(translateID);
		this.bonus = bonus;
	}
	
	@Override
	public void onDrawn(Player player) {
		player.getAccount().addGold(bonus);
		
	}

	@Override
	public String getDescription() {
		
		return null;
	}
	
	
	
}
