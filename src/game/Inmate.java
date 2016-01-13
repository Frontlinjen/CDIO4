package game;

public class Inmate {
	private int days;
	private Player player;
	
	public Inmate(int d, Player p)
	{
		days = d;
		player = p;
	}
	public void release()
	{
		days = 0;
	}
	public int getDaysLeft()
	{
		return days;
	}
	
	public int setDaysLeft(int daysLeft)
	{
		return daysLeft;
	}
	
	public void decreaseDaysLeft()
	{
		days--;
	}
	
	public boolean isPlayer(Player p)
	{
		return p==player;
	}
}
