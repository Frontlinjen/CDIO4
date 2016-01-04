package test;
import game.*;
import slots.Field;

import static org.junit.Assert.*;
import org.junit.Test;

public class ShuffleBagTest {

	@Test
	public void testRandomOutput() {
		int amount = 0;
		Integer[] testtal1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		ShuffleBag<Integer> test = new ShuffleBag<Integer>(testtal1);
		int j = 0;
		boolean random = false;
		for(int i = 0; i < testtal1.length; i++) {
			try {
				int k = test.getNext();
				amount = amount + k;
				if (j > k) {
				random = true;
				}
				j = k;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		assertTrue(random);
		assertTrue(amount == 210);
		
		
	}
	
	@Test
	public void testResetSuffleBag() {
		Integer[] testtal1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		ShuffleBag<Integer> testreset = new ShuffleBag<Integer>(testtal1);
		int j = 0;
		boolean random = false;
		int amount = 0;
		for(int i = 0; i < testtal1.length; i++) {
			try {
				int k = testreset.getNext();
				amount = amount + k;
				if (j > k) {
				random = true;
				}
				j = k;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		testreset.Reset();
		amount = 0;
		for(int i = 0; i < testtal1.length; i++) {
			try {
				int k = testreset.getNext();
				amount = amount + k;
				if (j > k) {
				random = true;
				}
				j = k;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		assertTrue(amount == 210);
	}
	
}
