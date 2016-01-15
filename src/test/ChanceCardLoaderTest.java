package test;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import Chancecards.ChanceCardController;
import game.Account;
import game.ChanceCardLoader;
import game.Player;
import game.Prison;

public class ChanceCardLoaderTest {
	private Prison prison;
	private Account parkinglotAccount;
	private Player[] players;
	
	@Test
	public void testParseCards() {
		ChanceCardController[] cards = ChanceCardLoader.parseChanceCards("ChanceCard.xml", parkinglotAccount, prison, players);
		assertFalse("Failed to parse cards!", cards==null);
		for(ChanceCardController c : cards)
		{
			assertFalse("Parsed array contained a null reference", c==null);
			assertFalse("Parsed description contained an empty string", c.getDescription().isEmpty());
		}
		
	}
}
