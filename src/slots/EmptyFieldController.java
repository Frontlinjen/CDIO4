package slots;

import desktop_fields.Field;
import game.Player;

public class EmptyFieldController extends FieldController{

	public EmptyFieldController() {
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

}
