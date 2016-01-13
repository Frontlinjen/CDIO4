package slots;

public class TerritoryData extends OwnableData{

	private int houses;
	private int houseCost;
	private int groupID;
	private int[] buildingRent;
	
	public TerritoryData(int i, int id, int price, int houseCost, int[] buildingRent) {
		super(i, price);
		this.houseCost = houseCost;
		houses = 0;
		groupID = id;
		this.buildingRent = buildingRent;
	}


	public int getRent() {
		return buildingRent[getHouses()];
	}
	
	public int getHouses(){
		return houses;
	}
	public void resetHouses()
	{
		houses = 0;
	}
	public void addHouse(){
		houses++;
	}
	public int getHouseCost(){
		return houseCost;
	}
	
	public int getGroupID(){
		return groupID;
	}

}