package slots;

import desktop_fields.Field;
import game.Player;
import game.Translator;

public class ChanceFieldController extends FieldController {

	@Override
	public void landOnField(Player player) {
		// TODO Auto-generated method stub
		
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
