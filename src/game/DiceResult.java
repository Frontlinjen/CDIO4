package game;

import java.util.Arrays;

/*
 * A compact OO-way of storing the eyes of the two dices. Is mainly a storage class, but also has some utility functions. 
 */

public class DiceResult
{
	private int[] dice;
	
	public DiceResult(int[] result){
		dice = result;
	}
	/**
	 * @param n
	 * a die
	 * @return
	 * instance we wanted from the die
	 */
	public int getDice(int n){
		
		try
		{
			return dice[n];
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new IndexOutOfBoundsException("Could not reach the correct element(dice array):\n" + e.getMessage());
		}
	
		
	}
	public int getSum(){
		
		int sumOfDice = 0;
		
		for(int i=0; i < dice.length; i++){
			sumOfDice += dice[i];
			
		}
		/**
		 * Decides amount of dice depended on the length of the array
		 */
		return sumOfDice;
	}
	
	public int getDiceAmount(){
		return dice.length;
	}
	
	/*
	 * Checks if the dice are two of a kind, in order to incoorperate rule of extra roles
	 * in case of two of a kind.
	 */
	
	public boolean areDiceEqual(){
		if(getDiceAmount() < 2)
			return true;
		else{
		for(int i = 1; i < getDiceAmount(); i++){
			if(dice[i] != dice[i-1])
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Checks if rolls are the same, in order to incoorperate rule, that if this happens 
	 * 3 times in a row, player is thrown in prison.
	 */
	
	public boolean areRollsEqual(DiceResult res){
		for(int i = 0; i < getDiceAmount(); i++){
			if(dice[i] != res.getDice(i))
				return false;
			}
		return true;
	}
	
	@Override
	public String toString() {
		return "DiceResult [dice=" + Arrays.toString(dice) + ", getSum()=" + getSum() + ", getDiceAmount()="
				+ getDiceAmount() +  "]";
	}
}

