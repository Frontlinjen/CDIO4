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
				inmate.isPlayer(player);

			}
		}
		return null;
	}

	public void release(Inmate inmate) 
	{
		for (int i = 0; i < inmates.length; i++)
			//for (Inmate inmate : inmates)
		{
			if(inmate==inmates[i])
				inmates[i] = null;
		}
	}

	private final int getSERVINGDAYS() {
		return SERVINGDAYS;
	}

	public void addInmate(Player player){
		for (int i = 0; i < inmates.length; i++)
		{
			if(inmates[i] == null)
			{
				Inmate newInmate = new Inmate(SERVINGDAYS, player);
				inmates[i] = newInmate;
			}
		}
	}
	public void advanceDay() {

		for (Inmate inmate : inmates)
		{
			if(inmates != null)
				inmate.decreaseDaysLeft();
		}
		

	}

}

