package slots;

public class GoToPrisonData extends FieldData{

	private int prisonPosition;
	
	public GoToPrisonData(int i, int prisonPosition) {
		super(i);
		this.prisonPosition = prisonPosition;
	}
	
	public int getPrisonPosition(){
		return prisonPosition;
	}
	
	public void setPrisonPosition(int a){
		prisonPosition = a;
	}

}
