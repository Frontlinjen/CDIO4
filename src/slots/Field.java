package slots;

import game.*;

public abstract class Field {

	private int translateID;
	private Types type;
	protected int position;
	//private String type;
	
	public enum Types {
		TERRITORY, REFUGE, LABORCAMP, TAX, FLEET
	}
		
	
	
	public Field(int i, Types type){
		translateID = i;
		this.type = type;
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

