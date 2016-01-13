package Chancecards;

import game.Player;

public class ChanceCardMatadorLegatController extends ChanceCardController{
private ChanceCardCashData chanceCardData;
	
	public ChanceCardMatadorLegatController(ChanceCardCashData chanceCardData) {
		super(chanceCardData);
		this.chanceCardData = chanceCardData;
	}
	
	
	@Override
	public boolean onDrawn(Player player) {
		final int MAXALLOWEDVALUE = 15000;
		int g = player.getAccount().getGold();
		int n = player.getProperty().getPropertyWorth();
		if(g + n < MAXALLOWEDVALUE){
			player.getAccount().addGold(chanceCardData.getMoney());
			return false;
		}
		else {
			return true;
		}
	}

	

}
