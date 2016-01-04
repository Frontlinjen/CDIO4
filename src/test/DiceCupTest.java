package test;

import static org.junit.Assert.*;
import game.DiceCup;
import game.DiceResult;

import org.junit.Test;

public class DiceCupTest {

	@Test
	public void testArrayLength() {
		final int TRUELENGTH = 1000;
		DiceCup dice = new DiceCup(TRUELENGTH);
		int a = dice.rollDice().getDiceAmount();
		
		assertTrue("Failed to create array with requested length", a == TRUELENGTH);
		
	}

	@Test
	public void testProbability(){
		int antalSlag = 1000;
		DiceCup dice = new DiceCup(antalSlag);
		DiceResult a = dice.rollDice();
		double[] sider = new double[6];
		double[] probability = {(1.0d/36*100),(1.0d/18*100),(1.0d/12*100),(1.0d/9*100),(5.0d/36*100),(1.0d/6*100),(5.0d/36*100),(1.0d/9*100),(1.0d/12*100),(1.0d/18*100),(1.0d/36*100)};
		
		
		for(int i = 0; i < a.getDiceAmount(); i++){
			System.out.println(a.getDice(i));
			
			switch(a.getDice(i)){
			case 1: sider[0]++; break;
			case 2: sider[1]++; break;
			case 3: sider[2]++; break;
			case 4: sider[3]++; break;
			case 5: sider[4]++; break;
			case 6: sider[5]++; break;
			}
			System.out.println(sider[0] +" "+ sider[1] +" "+ sider[2] +" "+ sider[3] +" "+ sider[4] +" "+ sider[5]);
		}
		
		for(int j = 0; j < 6; j++ ){
		double percent = ((sider[j] / antalSlag)*100);
		double deviation = Math.abs(percent - 1.0d/6*100);
		System.out.println("Die face: " + (j+1) + ", percent: " + percent + "%, deviation: " + deviation);
		assertTrue("The deviation was above accepted limit",2.5 > deviation);
		}
	}
}
