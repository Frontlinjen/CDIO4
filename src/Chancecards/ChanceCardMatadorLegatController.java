package Chancecards;

import game.Player;
import game.Property;

public class ChanceCardMatadorLegatController extends ChanceCardController{
private ChanceCardMatadorLegatData chanceCardData;
	
	public ChanceCardMatadorLegatController(ChanceCardMatadorLegatData chanceCardData) {
		this.chanceCardData = chanceCardData;
	}
	
	
	@Override
	public void onDrawn(Player player) {
		final int MAXALLOWEDVALUE = 15000;
		int g = player.getAccount().getGold();
		int n = player.getProperty().getPropertyWorth();
		if(g + n < MAXALLOWEDVALUE){
			player.getAccount().addGold(chanceCardData.getBonus());
		}
		else {
			
		}
	}

	@Override
	public String getDescription() {
		
		return null;
	}

}
