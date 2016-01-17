package Chancecards;

import game.Player;
import game.Translator;

public abstract class ChanceCardController {
	private ChanceCardData dat;
	
	ChanceCardController(ChanceCardData dat)
	{
		this.dat = dat;
	}
	
	public abstract boolean onDrawn(Player player);
	
	public final String getDescription()
	{
		return Translator.getString("CHANCECARDDSC"+dat.getTranslateID());
	}
	
	public String toString(){
		return "getDescription()=" + getDescription();
	}
}
