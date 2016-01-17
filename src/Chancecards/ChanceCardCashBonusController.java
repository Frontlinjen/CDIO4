package Chancecards;

import game.Player;

public class ChanceCardCashBonusController extends ChanceCardController{
	private ChanceCardCashData chanceCardCashData;
	
	public ChanceCardCashBonusController(ChanceCardCashData chanceCardCashBonusData) {
		super(chanceCardCashBonusData);
		this.chanceCardCashData = chanceCardCashBonusData;
	}
	
	@Override
	public boolean onDrawn(Player player) {
		player.getAccount().addGold(chanceCardCashData.getMoney());
		return false;
	}

	public String toString(){
		return chanceCardCashData.toString();
	}

}
