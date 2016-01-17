package game;

public class TestDice extends DiceCup {
	private int[][] diceSequence;
	//Has to be -1 since it gets increased by one before its used for the first time
	private int currentIndex = -1;
	
	public TestDice(int[][] diceSequence)
	{
		super(2);
		this.diceSequence = diceSequence;
	}
	@Override 
	public DiceResult rollDice(){
		if(currentIndex+1==diceSequence.length)
		{
			currentIndex = 0;
		}
		else
		{
			++currentIndex;
		}
		return new DiceResult(diceSequence[currentIndex]);
	}
}
