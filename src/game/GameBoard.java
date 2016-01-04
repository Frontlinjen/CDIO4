package game;

import slots.*;
import desktop_resources.GUI;

public class GameBoard {

	Field[] fields;
	public void initializeBoard()
	{	
		//desktop_fields.Brewery b = new desktop_fields.Brewery.Builder().setRent("2000").build();
		
		System.out.println("Loading board...");
		Field[] tmpFields = FieldLoader.parseFields("Fields.xml");
		for(Field f : tmpFields)
		{
			System.out.println(f.getName());
		}
		ShuffleBag<Field> shuffle = new ShuffleBag<Field>(tmpFields);
		desktop_fields.Field[] guiFields = new desktop_fields.Field[tmpFields.length];
		fields = new Field[tmpFields.length];
		
		int pos = 1;
		while(shuffle.elementsLeft()>0)
		{
			Field f;
			try {
				f = shuffle.getNext();
				desktop_fields.Field guiField = f.pushToGUI(pos);
				guiFields[pos-1] = guiField;
				fields[pos-1] = f;
				++pos;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GUI.create(guiFields);
	}
	public int getFieldCount()
	{
		return fields.length;
	}
	public Field getField(int index)
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
