package slots;

import game.*;

public abstract class Field {

	private int translateID;
	protected int position;

		
	
	
	public Field(int i){
		translateID = i;
	}

	public String getName() {
		return Translator.getString("SLOT" + translateID);
	}

	public String getDescription() {
		return Translator.getString("SLOTDSC" + translateID);
	}
	public int getPosition()
	{
		return position;
	}
	
	public abstract void landOnField (Player player);
	/**
	 * Adds the field to the GUI. Should be called before the GUI is created
	 * @param position
	 */
	public abstract	desktop_fields.Field pushToGUI(int position);
}

