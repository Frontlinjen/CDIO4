package slots;

import Chancecards.ChanceCardController;
import desktop_fields.Field;
import desktop_resources.GUI;
import game.Player;
import game.Translator;
import utilities.ShuffleBag;

public class ChanceFieldController extends FieldController {
	ShuffleBag<ChanceCardController> cards;
	
	
	public ChanceFieldController(ShuffleBag<ChanceCardController> cards) {
		super();
		this.cards = cards;
	}

	@Override
	public void landOnField(Player player) {
		try{
			ChanceCardController newCard = cards.getNext();
			//If onDrawn returns false, then the card should be put back into the pile
			if(!newCard.onDrawn(player))
			{
				cards.pushBackLastCard();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		
	}

	@Override
	public Field pushToGUI(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDescription() {
		
		return Translator.getString("CHANCEFIELDDSC");
	}
}
