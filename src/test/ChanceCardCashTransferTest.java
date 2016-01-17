package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Chancecards.ChanceCardCashData;
import Chancecards.ChanceCardCashTransferController;
import game.Player;

public class ChanceCardCashTransferTest {

	private Player player1;
	private Player player2;
	private ChanceCardCashTransferController cf;
	private ChanceCardCashData cd;
	
	@Test
	public void Test() {
		player1 = new Player("p1");
		player2 = new Player("p2");
		Player[] players = new Player[2];
		players[0] = player1;
		players[1] = player2;
		cd = new ChanceCardCashData(0, 500);
		cf = new ChanceCardCashTransferController(cd, players);
		player1.getAccount().setGold(1000);
		player2.getAccount().setGold(1000);
		cf.onDrawn(player1);
		assertTrue(player1.getAccount().getGold() == 1500);
		assertTrue(player2.getAccount().getGold() == 500);
	}
	
}
