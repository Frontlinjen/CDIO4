package Chancecards;

import game.Player;

public class ChanceCardBuildingTaxController extends ChanceCardController{
	private ChanceCardBuildingTaxData chanceCardData;
	
	public ChanceCardBuildingTaxController(ChanceCardBuildingTaxData chanceCardData) {
		this.chanceCardData = chanceCardData;
	}

	@Override
	public boolean onDrawn(Player player) {
		if(player.getProperty().getPropertiesOwned().) {
			
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
