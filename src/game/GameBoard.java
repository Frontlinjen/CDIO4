package game;

import slots.*;
import utilities.ShuffleBag;
import desktop_resources.GUI;

import java.util.List;

import Chancecards.*;

public class GameBoard {

	FieldController[] fields;
	public void initializeBoard(Prison prison, List<Player> players)
	{	
		//desktop_fields.Brewery b = new desktop_fields.Brewery.Builder().setRent("2000").build();
		
		System.out.println("Loading board...");
		Account acc = new Account(0, null);
		int[] houseRent = new int[6];
		ChanceCardController[] chanceCards = ChanceCardLoader.parseChanceCards("ChanceCard.xml", acc, prison, players);
		fields = FieldLoader.parseFields("Fields.xml", new ShuffleBag<ChanceCardController>(chanceCards), prison,acc, houseRent);
		desktop_fields.Field[] guiFields = new desktop_fields.Field[fields.length];
		
		for (FieldController field : fields) {
			System.out.println(field.getName());
		}
		System.out.println("Loaded: " + fields.length + "fields..");
		
		int pos = 1;
		for(FieldController f : fields)
		{
			desktop_fields.Field guiField = f.pushToGUI(pos);
			if(guiField==null)
			{
				System.out.println("[WARNING]" + f.getName() + " returned null!");
			}
			guiFields[pos-1] = guiField;
			pos++;	
		}
		GUI.create(guiFields);
	}
	public int getFieldCount()
	{
		return fields.length;
	}
	public FieldController getField(int index)
	{
		try
		{
			return fields[index];
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new IndexOutOfBoundsException("Attempt to access a non-existing field");
		}
	}
}
