package game;

import slots.*;
import utilities.ShuffleBag;
import desktop_resources.GUI;

public class GameBoard {

	FieldController[] fields;
	public void initializeBoard()
	{	
		//desktop_fields.Brewery b = new desktop_fields.Brewery.Builder().setRent("2000").build();
		
		System.out.println("Loading board...");
		fields = FieldLoader.parseFields("Fields.xml");
		for(FieldController f : fields)
		{
			System.out.println(f.getName());
		}
		desktop_fields.Field[] guiFields = new desktop_fields.Field[fields.length];
		
		int pos = 1;
		for(FieldController f : fields)
		{
			desktop_fields.Field guiField = f.pushToGUI(pos++);
			guiFields[pos-1] = guiField;
			
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
