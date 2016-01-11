package slots;

import game.Translator;

public class FieldData {
	private int translateID;
	protected int position;

	public FieldData(int i){
		translateID = i;
	}

	public String getName() {
		return Translator.getString("SLOT" + translateID);
	}
	
	public void setPosition(int p)
	{
		position = p;
	}
	public int getPosition()
	{
		return position;
	}
}
