package game;

public class Prison {
	
	private Inmate[] inmates;
	
	private final int SERVINGDAYS = 3;
	
	public Prison(int maxInmates){
		inmates = new Inmate [maxInmates];  
	}
	
	Inmate getInmate(Player player)
	{
		for (Inmate inmate : inmates)
		{
			if(inmate!=null)
			{
				/*
				 * inmate is player
				 */
			}
		}
	}
	
	public void release(Inmate inmate) {
		for (int i = 0; i < inmates.length; i++) {
			if(inmate==inmates[i])
				inmates[i] = null;
		}
	}
	
	private final int getSERVINGDAYS() {
		return SERVINGDAYS;
	}
	
	public void advanceDay() {
		for (int i = 0; i < inmates.length;i++){
			if(inmates[i] != null)
				inmate[i].decreaseDay();
		}
			
		
	}
	
	
}
