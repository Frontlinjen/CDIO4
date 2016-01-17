package Chancecards;


import game.Player;

public class ChanceCardCashTransferController extends ChanceCardController{
	private ChanceCardCashData chanceCardData;
	private  Player[]  players;
	
	public ChanceCardCashTransferController(ChanceCardCashData chanceCardData, Player[] players) {
		super(chanceCardData);
		this.chanceCardData = chanceCardData;
		this.players = players;
	}
	
	@Override
	public boolean onDrawn(Player player) {
		for(Player p : players)
		{
			if(p != player && p != null) {
				p.getAccount().transferTo(player.getAccount(), chanceCardData.getMoney());
			}
		}
		return false;
	}
	
	public String toString(){
		return "No applicable data";
	}


}
