package test;
import slots.FieldController;
import utilities.ShuffleBag;

import static org.junit.Assert.*;

import org.junit.Test;

import Chancecards.ChanceCardController;
import game.Account;
import game.FieldLoader;
import game.Prison;

public class FieldLoaderTest {
	private Prison prison;
	private Account parkinglotAccount;
	private ShuffleBag<ChanceCardController> chanceCards;
	private int[] buildingtax = new int[6];
	
	final int EXPECTEDFIELDAMOUNT = 40;
	@Test
	public void testParseFields() {
		FieldController[] fields = FieldLoader.parseFields("Fields.xml", chanceCards, prison, parkinglotAccount, buildingtax);
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
