package slots;

import game.Prison;

public class GoToPrisonData extends FieldData{

	private int prisonPosition;
	
	private Prison prison;
	
	public GoToPrisonData(int i, int prisonPosition, Prison prison) {
		super(i);
		this.prisonPosition = prisonPosition;
	}
	
	public int getPrisonPosition(){
		return prisonPosition;
	}
	
	public void setPrisonPosition(int a){
		prisonPosition = a;
	}

	public Prison getPrison() {
		return prison;
	}

}
