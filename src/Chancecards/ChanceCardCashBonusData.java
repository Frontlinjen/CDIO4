package Chancecards;

import game.Player;

public class ChanceCardCashBonusData extends ChanceCardData{
	private int bonus;

	public ChanceCardCashBonusData(int translateID, int bonus) {
		super(translateID);
		this.bonus = bonus;
	}
	
	public int getBonus()
	{
		return bonus;
	}
}
