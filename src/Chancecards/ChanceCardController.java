package Chancecards;

import game.Player;

public abstract class ChanceCardController {
	
	public abstract void onDrawn(Player player);
	
	public abstract String getDescription();
}
