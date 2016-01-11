package game;

import slots.BreweryController;
import slots.FleetController;
import slots.OwnableController;
import slots.TerritoryController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Property {
/**
 * Keeps track of how many fleets and breweries each player has.
 * 'Expand' adds an additional fleet/breweries, when the plays buys on of them
 */

	private List<slots.OwnableController> properties = new ArrayList<slots.OwnableController>();
	
	 
	public Iterator<OwnableController> getPropertiesOwned()
	{
		return properties.iterator();
	}
	public void addProperty(OwnableController p)
	{
		properties.add(p);
	}
	public int getPropertyCount()
	{
		return properties.size();
	}
	public int getTotalHotelCount()
	{
		int amount = 0;
		for(OwnableController ownable : properties)
		{
			if(properties instanceof TerritoryController)
			{
				TerritoryController territory = (TerritoryController)ownable;
				amount += territory.getHotelAmount();
			}
		}
		return amount;
	}
	public int getTotalHouseCount()
	{
		int amount = 0;
		for(OwnableController ownable : properties)
		{
			if(properties instanceof TerritoryController)
			{
				TerritoryController territory = (TerritoryController)ownable;
				amount += territory.getHouseAmount();
			}
		}
		return amount;
	}
	public int getBreweriesOwned()
	{
		int breweriesOwned = 0;
		for(int i = 0; i<=properties.size(); i++)
		{
			if(properties.get(i) instanceof BreweryController)
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
			if(properties.get(i) instanceof FleetController)
				{
					fleetsOwned++;
				}
		}
		return fleetsOwned;
	}
	
	public int getPropertyWorth()
	{
		int propertyWorth = 0;
		for (OwnableController property : properties)
		{
			propertyWorth += property.getWorth();
		}
		return propertyWorth;
	}
}
