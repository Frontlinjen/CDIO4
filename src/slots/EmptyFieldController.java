package slots;


import java.awt.Color;
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
		desktop_fields.Street field = new desktop_fields.Street.Builder().setBgColor(new Color(255f/255, 165f/255, 48f/255)).build();
		field.setDescription(getDescription());
		field.setTitle(this.getName());
		field.setSubText("");

		return field;
	}
	@Override
	public String getDescription() {
		return Translator.getString("EMPTYFIELDDSC");
	}
}
