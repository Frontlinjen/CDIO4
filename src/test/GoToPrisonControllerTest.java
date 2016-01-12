package test;

import static org.junit.Assert.*;

import org.junit.Test;
import game.*;
import slots.*;

public class GoToPrisonControllerTest {

	@Test
	public void testGoToPrisonController() {
		Player player1 = new Player("sheep");
		Prison prison = new Prison(6);
		GoToPrisonData prisonData = new GoToPrisonData(1,1, prison);
		GoToPrisonController pController = new GoToPrisonController(prisonData);
		
		pController.pushToGUI(1);
		pController.landOnField(player1);
		
		assertTrue("Fail,Prison should not be empty",prison.getInmate(player1)!=null);
		
	}

}
