package Chancecards;

import game.Player;

public class ChanceCardCashBonusController extends ChanceCardController{
	private ChanceCardCashData chanceCardCashData;
	
	public ChanceCardCashBonusController(ChanceCardCashData chanceCardCashBonusData) {
		this.chanceCardCashData = chanceCardCashData;
	}
	
	@Override
	public void onDrawn(Player player) {
		player.getAccount().addGold(chanceCardCashData.getMoney());
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
