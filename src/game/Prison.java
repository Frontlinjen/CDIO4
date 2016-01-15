package game;

public class Prison {

	private Inmate[] inmates;

	public final int SERVINGDAYS = 4;

	public Prison(int maxInmates){
		inmates = new Inmate [maxInmates];  
	}

	public Inmate getInmate(Player player)
	{
		for (Inmate inmate : inmates)
		{
			if(inmate!=null && inmate.isPlayer(player))
			{
				return inmate;
			}
		}
		return null;
	}

	public void addInmate(Player player){
		for (int i = 0; i < inmates.length; i++)
		{
			if(inmates[i] == null)
			{
				Inmate newInmate = new Inmate(SERVINGDAYS, player);
				inmates[i] = newInmate;
				break;
			}
		}
	}
	public void advanceDay() {

		for (int i=0;i<inmates.length;++i)
		{
			if(inmates[i] != null)
			{
				inmates[i].decreaseDaysLeft();
			
				if(inmates[i].getDaysLeft()<=0)
				{	
					inmates[i] = null;
				}
			}
		}
		

	}

}

