package test;

import org.junit.Test;

import game.Board;
import game.DiceCup;
import game.TestDice;

public class PrisonTest {

	@Test
	public void test(){
		TestDice dice = new TestDice(new int[][] {{5,5}});
		Board board = new Board((DiceCup)dice);
		board.startGame();
	}
}
