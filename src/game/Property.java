package game;

import slots.Brewery;
import slots.Fleet;
import slots.Ownable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Property {
/**
 * Keeps track of how many fleets and breweries each player has.
 * 'Expand' adds an additional fleet/breweries, when the plays buys on of them
 */

	private List<slots.Ownable> properties = new ArrayList<slots.Ownable>();
	
	 
	public Iterator<Ownable> getPropertiesOwned()
	{
		return properties.iterator();
	}
	public void addProperty(Ownable p)
	{
		properties.add(p);
	}
	public int getPropertyCount()
	{
		return properties.size();
	}
	public int getBreweriesOwned()
	{
		int breweriesOwned = 0;
		for(int i = 0; i<=properties.size(); i++)
		{
			if(properties.get(i) instanceof Brewery)
			{
				breweriesOwned++;
			}
		}
		return breweriesOwned;
	}
	public int getFleetOwned()
	{
		int fleetsOwned = 0;
		for(int i = 0; i<=properties.size(); i++)
		{
			if(properties.get(i) instanceof Fleet)
				{
					fleetsOwned++;
				}
		}
		return fleetsOwned;
	}
}
