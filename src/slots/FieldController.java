package slots;

import game.*;

public abstract class FieldController {

	public abstract void landOnField (Player player);
	/**
	 * Adds the field to the GUI. Should be called before the GUI is created
	 * @param position
	 */
	public abstract	desktop_fields.Field pushToGUI(int position);
}

