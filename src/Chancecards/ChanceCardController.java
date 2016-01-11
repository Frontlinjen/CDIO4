package Chancecards;

import game.Player;

public abstract class ChanceCardController {
	
	public abstract boolean onDrawn(Player player);
	
	public abstract String getDescription();
}
