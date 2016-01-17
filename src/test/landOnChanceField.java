package test;



import org.junit.Test;

import game.Board;
import game.DiceCup;
import game.TestDice;

public class landOnChanceField {

	@Test
	public void test() {
		TestDice dice = new TestDice(new int[][]{{2,0},{2,0},{2,0},{2,0},{2,0},{2,0},{5,0},{5,0},{5,0},{5,0},{5,0},{5,0},{10,0},{10,0},{10,0},{10,0},{10,0},{10,0}});
		Board board = new Board((DiceCup)dice);
		board.startGame();
	}

}
