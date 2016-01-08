package slots;

import desktop_fields.Field;
import game.Player;

public class EmptyFieldController extends FieldController{

	public EmptyFieldController() {
	}

	@Override
	public void landOnField(Player player) {


	}

	@Override
	public String toString() {
		return "EmptyFieldController";
	}

	@Override
	public Field pushToGUI(int position) {
		// TODO Auto-generated method stub
		return null;
	}

}
