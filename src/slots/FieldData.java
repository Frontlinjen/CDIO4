package slots;

import game.Translator;

public class FieldData {
	private int translateID;
	protected int position;

	public FieldData(int translateID){
		this.translateID = translateID;
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
	
	public String toString(){
		return "getName()=" + getName() + "getPosition()=" + getPosition();
	}
}
