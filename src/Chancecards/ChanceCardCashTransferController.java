package Chancecards;

import game.Player;

public class ChanceCardCashTransferController extends ChanceCardController{
	private ChanceCardCashTransferData chanceCardData;
	
	public ChanceCardCashTransferController(ChanceCardCashTransferData chanceCardData) {
		this.chanceCardData = chanceCardData;
	}
	
	@Override
	public void onDrawn(Player player) {
		for(Player p : chanceCardData.getPlayers())
		{
			if(p != player) {
			p.getAccount().transferTo(player.getAccount(), chanceCardData.getBonus());
			}
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
