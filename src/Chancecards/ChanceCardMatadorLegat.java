package Chancecards;

import game.Account;
import game.Player;

public class ChanceCardMatadorLegat extends ChanceCardCashBonus{
	
	public ChanceCardMatadorLegat(int translateID, int bonus) {
		super(translateID, bonus);
	}
	
	@Override
	public void onDrawn(Player player) {
		final int MAXALLOWEDVALUE = 15000;
		int g = player.getAccount().getGold();
		
		if(g < MAXALLOWEDVALUE){
			player.getAccount().addGold(bonus);
		}
	}
	
	@Override
	public String getDescription() {
		return null;
	}
}
