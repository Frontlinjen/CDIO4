package Chancecards;

import game.Player;

public class ChanceCardCashBonusController extends ChanceCardController{
	private ChanceCardCashBonusData chanceCardCashBonusData;
	
	public ChanceCardCashBonusController(ChanceCardCashBonusData chanceCardCashBonusData) {
		this.chanceCardCashBonusData = chanceCardCashBonusData;
	}
	
	@Override
	public void onDrawn(Player player) {
		player.getAccount().addGold(chanceCardCashBonusData.getBonus());
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
