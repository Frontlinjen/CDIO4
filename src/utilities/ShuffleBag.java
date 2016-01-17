package utilities;

import java.util.Random;

public class ShuffleBag<T> {
	private T[] values;
	private int currentPos;
	private Random rng = new Random(System.currentTimeMillis());
	public ShuffleBag(T[] values)
	{
		this.values = values;
		reset();
	}

	public int getElementsLeft()
	{
		//+1 since currentPos goes from 0, and hence if the first element(0) is left it would return 0. 
		return currentPos+1;
	}
	public void reset()
	{
		currentPos = values.length-1;
	}
	
	private void swapToEnd(int index)
	{
		T tmpValue = values[index];
		values[index] = values[currentPos];
		values[currentPos] = tmpValue;
		//Removed the used index out of scope. 
		--currentPos;
	}
	public void pushBackLastCard()
	{
		if(currentPos < values.length-1)
		{
			currentPos++;
		}
	}
	public T getNext() throws Exception
	{
		if(currentPos==-1)
		{
			throw new Exception("Shuffle bag has run out of free elements. If this is intended call reset before getting the next variable.");
		}
		//+1 due to the last index being exclusive
		int index = rng.nextInt(currentPos+1);
		T value = values[index];
		swapToEnd(index);
		return value;		
	}
	
}
