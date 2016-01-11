package Chancecards;

import java.util.List;

import game.Player;

public class ChanceCardCashTransferController extends ChanceCardController{
	private ChanceCardCashData chanceCardData;
	private  List<Player>  players;
	
	public ChanceCardCashTransferController(ChanceCardCashData chanceCardData, List<Player> players) {
		this.chanceCardData = chanceCardData;
		this.players = players;
	}
	
	@Override
	public void onDrawn(Player player) {
		for(Player p : players)
		{
			if(p != player) {
			p.getAccount().transferTo(player.getAccount(), chanceCardData.getMoney());
			}
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
