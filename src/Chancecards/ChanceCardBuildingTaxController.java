package Chancecards;

import game.Player;
import game.Account;

public class ChanceCardBuildingTaxController extends ChanceCardController{
	private ChanceCardBuildingTaxData chanceCardData;
	
	public ChanceCardBuildingTaxController(ChanceCardBuildingTaxData chanceCardData) {
		this.chanceCardData = chanceCardData;
	}

	@Override
	public boolean onDrawn(Player player) {
		int hu = player.getProperty().getTotalHouseCount();
		int ho = player.getProperty().getTotalHotelCount();
		Account acc = chanceCardData.getParkinglotAccount();
		int housecount = chanceCardData.getHouseTax();
		int hotelcount = chanceCardData.getHotelTax();
		
		if(hu+ho > 0) {
			player.getAccount().transferTo(acc, housecount*hu);
			player.getAccount().transferTo(acc, hotelcount*ho);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
