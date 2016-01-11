package Chancecards;

import game.Player;

public class ChanceCardCashBonusController extends ChanceCardController{
	private ChanceCardCashData chanceCardCashData;
	
	public ChanceCardCashBonusController(ChanceCardCashData chanceCardCashBonusData) {
		this.chanceCardCashData = chanceCardCashData;
	}
	
	@Override
	public boolean onDrawn(Player player) {
		player.getAccount().addGold(chanceCardCashData.getMoney());
		return false;
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
