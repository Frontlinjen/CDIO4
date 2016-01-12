package Chancecards;

import game.Account;
import game.Player;

public class ChanceCardTaxController extends ChanceCardController{
	private ChanceCardCashData chanceCardData;
	private Account parkinglotAccount;
	
	public ChanceCardTaxController(ChanceCardCashData chanceCardData, Account parkinlotAccount) {
		this.chanceCardData = chanceCardData;
		this.parkinglotAccount = parkinlotAccount;
	}
	
	
	@Override
	public boolean onDrawn(Player player) {
		player.getAccount().transferTo(parkinglotAccount, chanceCardData.getMoney());
		return false;
	}

	@Override
	public String getDescription() {
		return null;
	}

}
