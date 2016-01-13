package test;

import static org.junit.Assert.*;
import game.DiceResult;

import org.junit.Test;


public class DiceResultTest {
	
	private int[] dice = {1,2,3,4,5,6};
	private DiceResult dicer = new DiceResult(dice);
	private int[] diceE = {3,3};
	private DiceResult dicerE = new DiceResult(diceE);
	
	@Test
	public void testGetDice() {
		assertTrue(dicer.getDice(3) == 4);
	}

	@Test
	public void testGetSum() {
		assertTrue(dicer.getSum() == 21);
	}
	
	@Test
	public void testGetDiceAmount() {
		assertTrue(dicer.getDiceAmount() == 6);
	}
	
	@Test
	public void testAreDiceEqual() {
		assertFalse(dicer.areDiceEqual());
		assertTrue(dicerE.areDiceEqual());
	}
	
	@Test
	public void testAreRollsEqual() {
		assertFalse(dicer.areRollsEqual(dicerE));
		assertTrue(dicer.areRollsEqual(dicer));
	}
}
