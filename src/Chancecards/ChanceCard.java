package Chancecards;

import game.Player;
import game.Translator;

public abstract class ChanceCard {
	private int translateID;
	
	public abstract void onDrawn(Player player);
	
	public abstract String getDescription();
	
}
