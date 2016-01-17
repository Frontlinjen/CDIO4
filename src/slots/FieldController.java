package slots;

import game.*;

public abstract class FieldController {

	private FieldData data;
	
	public FieldController(FieldData d)
	{
		data = d;
	}
	
	public abstract void landOnField (Player player);
	
	/**
	 * Adds the field to the GUI. Should be called before the GUI is created
	 * @param position
	 */
	public abstract	desktop_fields.Field pushToGUI(int position);
	public abstract String getDescription();
	public String getName()
	{
		return data.getName();
	}
	
	public String toString(){
		return "No applicable data";
	}
}

