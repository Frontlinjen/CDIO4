package Chancecards;

import game.Player;

public class ChanceCardCashTransferData extends ChanceCardData{
	private Player[] players;
	private int money;
	
	public ChanceCardCashTransferData(int translateID, Player[] player, int bonus) {
		super(translateID);
		this.players = player;
		this.money = bonus;
	}
	
	public Player[] getPlayers() {
		return this.players;
	}
	
	public int getBonus() {
		return this.money;
	}
}
