package test;
import slots.FieldController;

import static org.junit.Assert.*;

import org.junit.Test;

import game.FieldLoader;

public class FieldLoaderTest {
	final int EXPECTEDFIELDAMOUNT = 21;
	@Test
	public void testParseFields() {
		FieldController[] fields = FieldLoader.parseFields("Fields.xml");
		assertFalse("Failed to parse fields!", fields==null);
		assertTrue("Failed to parse the expected amount of fields", fields.length==EXPECTEDFIELDAMOUNT);
		for(FieldController f : fields)
		{
			assertFalse("Parsed array contained a null reference", f==null);
			assertFalse("Parsed description contained an empty string", f.getDescription().isEmpty());
			assertFalse("Parsed name contained an empty string", f.getName().isEmpty());
		}
		
	}

}
