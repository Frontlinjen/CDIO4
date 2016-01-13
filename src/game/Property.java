package game;

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

	//private List<slots.OwnableController> properties = new ArrayList<slots.OwnableController>();
	private List<slots.FleetController> fleets = new ArrayList<slots.FleetController>();
	private List<slots.BreweryController> breweries = new ArrayList<slots.BreweryController>();
	private List<slots.TerritoryController> territories = new ArrayList<slots.TerritoryController>();
	
	public OwnableController findOwnableByName(String name)
	{
		OwnableController[] ownables = getPropertiesOwned();
		for (OwnableController ownableController : ownables) {
			String ownableName = ownableController.getName();
			if(ownableName.equals(name))
			{
				return ownableController;
			}
		}
		return null;
		
	}
	
	public TerritoryController findTerritoryByName(String name)
	{
		for(TerritoryController territory : territories)
		{
			if(territory.getName().equals(name))
			{
				return territory;
			}
		}
		return null;
	}
	public String[] getPawnedPropertyList()
	{
		OwnableController[] ownables = getPropertiesOwned();
		String[] names = new String[ownables.length];
		int index = 0;
		for (OwnableController cont : ownables) {
			if(cont.pawned())
			{
				names[index++] = cont.getName(); 
			}
		}
		String[] retNames= new String[index];//no need to +1 on index, since its always one larger than the current array contains
		System.arraycopy(names, 0, retNames, 0, index); 
		return retNames;
	}
	public String[] getPawnablePropertyList()
	{
		OwnableController[] ownables = getPropertiesOwned();
		String[] names = new String[ownables.length];
		int index = 0;
		for (OwnableController cont : ownables) {
			if(!cont.pawned())
			{
				names[index++] = cont.getName(); 
			}
		}
		String[] retNames= new String[index];
		System.arraycopy(names, 0, retNames, 0, index);
		return retNames;
	}

	public Iterator<slots.TerritoryController> getTerritories()
	{
		return territories.iterator();
	}
	public slots.OwnableController[] getPropertiesOwned()
	{
		slots.OwnableController[] collection = new slots.OwnableController[fleets.size()+breweries.size()+territories.size()];
		int collectionIndex = 0;
		for (int i = 0; i < fleets.size(); i++) {
			collection[collectionIndex++] =  fleets.get(i);
		}
		for(int i = 0; i < breweries.size();++i)
		{
			collection[collectionIndex++] = breweries.get(i);
		}
		for(int i=0;i<territories.size();++i)
		{
			collection[collectionIndex++] = territories.get(i);
		}
		return collection;
	}
	public void addTerritory(slots.TerritoryController p)
	{
		territories.add(p);
	}
	public void addFleet(slots.FleetController t)
	{
		fleets.add(t);
	}
	public void addBreweries(slots.BreweryController b)
	{
		breweries.add(b);
	}
	public int getPropertyCount()
	{
		return fleets.size() + territories.size() + breweries.size();
	}
	public int getTotalHotelCount()
	{
		int amount = 0;
		for(TerritoryController territory: territories)
		{
			amount += territory.getHotelAmount();
		}
		return amount;
	}
	public String[] getTerritoryNames()
	{
		
		String[] propertyNames = new String[territories.size()];
		for(int i=0;i<territories.size();++i)
		{
			propertyNames[i] = territories.get(i).getName();
		}
		return propertyNames;
	}
	public int getTotalHouseCount()
	{
		int amount = 0;
		for(TerritoryController territory: territories)
		{
			amount += territory.getHouseAmount();
		}
		return amount;
	}
	public int getBreweriesOwned()
	{
		return breweries.size();
	}
	public int getFleetOwned()
	{
		return fleets.size();
	}
	
	public int getPropertyWorth()
	{
		slots.OwnableController[] properties = getPropertiesOwned();
		int propertyWorth = 0;
		for (OwnableController property : properties)
		{
			propertyWorth += property.getWorth();
		}
		return propertyWorth;
	}
}
