package Chancecards;

import game.Player;

public class ChanceCardTaxController extends ChanceCardController{
	private ChanceCardTaxData chanceCardData;
	
	public ChanceCardTaxController(ChanceCardTaxData chanceCardData) {
		this.chanceCardData = chanceCardData;
	}
	
	
	@Override
	public void onDrawn(Player player) {
		player.getAccount().transferTo(chanceCardData.getAccount(), chanceCardData.getTax());;
	}

	@Override
	public String getDescription() {
		return null;
	}

}
