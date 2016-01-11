package slots;

import game.Translator;

public abstract class FieldData {
	private int translateID;
	protected int position;

	public FieldData(int i){
		translateID = i;
	}

	public String getName() {
		return Translator.getString("SLOT" + translateID);
	}

	public abstract String getDescription();
	
	public void setPosition(int p)
	{
		position = p;
	}
	public int getPosition()
	{
		return position;
	}
}
