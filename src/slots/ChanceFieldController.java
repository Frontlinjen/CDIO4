package slots;

import java.awt.Color;

import Chancecards.ChanceCardController;
import desktop_fields.Field;
import desktop_resources.GUI;
import game.Player;
import game.Translator;
import utilities.ShuffleBag;

public class ChanceFieldController extends FieldController {
	ShuffleBag<ChanceCardController> cards;
	
	
	public ChanceFieldController(ShuffleBag<ChanceCardController> cards, FieldData data) {
		super(data);
		this.cards = cards;
	}

	@Override
	public void landOnField(Player player) {
		try{
			ChanceCardController newCard = cards.getNext();
			//If onDrawn returns false, then the card should be put back into the pile
			if(!newCard.onDrawn(player))
			{
				GUI.showMessage(Translator.getString("CARDCOULDNOTBEUSED"));
				cards.pushBackLastCard();
			}
			else
			{
				GUI.displayChanceCard(newCard.getDescription());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		
	}

	@Override
	public Field pushToGUI(int position) {
		desktop_fields.Chance newField = new desktop_fields.Chance.Builder().setBgColor(Color.gray).build();
		newField.setTitle(getName());
		return newField;
	}
	@Override
	public String getDescription() {
		
		return Translator.getString("CHANCEFIELDDSC");
	}
}
