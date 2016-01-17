package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Board;
import game.DiceCup;
import game.TestDice;

public class GotoPrisonTest {

	@Test
	public void test() {
		TestDice dice = new TestDice(new int[][]{{30,0},{30,0},{6,6},{6,6}});
		Board board = new Board((DiceCup)dice);
		board.startGame();
	}

}
