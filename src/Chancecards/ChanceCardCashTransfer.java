package Chancecards;

import game.Player;

public class ChanceCardCashTransfer extends ChanceCard{
	private Player[] players;
	private int bonus;
	
	public ChanceCardCashTransfer(int translateID, Player[] player, int bonus) {
		super(translateID);
		this.players = player;
		this.bonus = bonus;
	}

	@Override
	public void onDrawn(Player player) {
		for(Player p : players)
		{
			if(p != player) {
			p.getAccount().transferTo(player.getAccount(), bonus);
			}
		}
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
