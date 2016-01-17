package Chancecards;

import game.Player;

public class ChanceCardMoveToNextFleetController extends ChanceCardController{
	private ChanceCardMoveToNextFleetData chanceCardData;
	
	public ChanceCardMoveToNextFleetController(ChanceCardMoveToNextFleetData chanceCardData) {
		super(chanceCardData);
		this.chanceCardData = chanceCardData;
	}
	
	
	@Override
	public boolean onDrawn(Player player) {
		int plrPos = player.getPosition();
		int closestIndex = -1;
		int shortestDistance = Integer.MAX_VALUE;
		int[] fp = chanceCardData.getFleetPositions();
		int fl = chanceCardData.getFleetPositions().length;
		
		for(int i = 0; i < fl; i++) {
			int distance = fp[i] - plrPos;
			if(distance > 0 && distance < shortestDistance) {
				shortestDistance = distance;
				closestIndex = i;
			}	
		}
		if(shortestDistance == Integer.MAX_VALUE){
			for(int j : chanceCardData.getFleetPositions()) {
				if(j < shortestDistance){
					shortestDistance = j;
				}
			}
			player.setNextPosition(shortestDistance, true);
		}
		else
		{
			player.setNextPosition(fp[closestIndex], true);
		}
		return false;
	}


	@Override
	public String toString() {
		return chanceCardData.toString();
	}


}
