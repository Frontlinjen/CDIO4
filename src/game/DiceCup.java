package game;

import java.util.Arrays;
import java.util.Random;

public class DiceCup {

	private int[] diceArray;
	private Random rGen = new Random(System.currentTimeMillis());
	
	public DiceCup(int numberOfDice){
		
		diceArray = new int[numberOfDice];
				
	}
		public DiceResult rollDice(){
		
/*	
 * Inserts Ints into the array, depending on the amount of dice chosen
 * 	for the game. The array extends depending on the amount of dice
 * 	chosen. And spits out a random generated number for each array slot. 
 */
			
			for(int i=0; i < diceArray.length; i++){
				diceArray[i] = rGen.nextInt(6)+1;
			}
			return new DiceResult(diceArray);
		}
		@Override
		public String toString() {
			return "DiceCup [diceArray=" + Arrays.toString(diceArray) + ", random Generator=" + rGen + ", rollDice()=" + rollDice()
					+ "]";
		}
		
}
