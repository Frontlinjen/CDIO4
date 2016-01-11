package Chancecards;

import game.Player;
import game.Translator;

public abstract class ChanceCard {
	protected int translateID;
	
	public ChanceCard(int translateID) {
		this.translateID = translateID;
	}
	
	public abstract void onDrawn(Player player);
	
	public abstract String getDescription();
	
}
