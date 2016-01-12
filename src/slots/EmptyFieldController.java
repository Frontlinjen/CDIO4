package slots;

import desktop_fields.Field;
import game.Player;
import game.Translator;

public class EmptyFieldController extends FieldController{
	
	public EmptyFieldController(FieldData fieldData) {
		super(fieldData);
	}
//Field is supposed to do nothing, so there for it is empty.
	@Override
	public void landOnField(Player player) {


	}

	@Override
	public String toString() {
		return "EmptyFieldController";
	}

	@Override
	public Field pushToGUI(int position) {
		return null;
	}
	@Override
	public String getDescription() {
		if(fieldData.getPosition() == 1) {
		return Translator.getString("EMPTYFIELDDSC",2);
		} else {
		return Translator.getString("EMPTYFIELDDSC",1);
		}
	}
}
