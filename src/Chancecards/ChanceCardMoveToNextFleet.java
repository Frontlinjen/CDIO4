package Chancecards;

import game.Player;

public class ChanceCardMoveToNextFleet extends ChanceCard{
	int[] fleetPositions;
	
	public ChanceCardMoveToNextFleet(int translateID, int[] fleetPositions) {
		super(translateID);
		this.fleetPositions = fleetPositions;
	}

	@Override
	public void onDrawn(Player player) {
		int plrPos = player.getPosition();
		int closestIndex = -1;
		int shortestDistance = Integer.MAX_VALUE;
		
		for(int i = 0; i < fleetPositions.length; i++) {
			int distance = fleetPositions[i] - plrPos;
			if(distance > 0 && distance < shortestDistance) {
				shortestDistance = distance;
				closestIndex = i;
			}	
		}
		if(shortestDistance == Integer.MAX_VALUE){
			for(int j : fleetPositions) {
				if(j < shortestDistance){
					shortestDistance = j;
				}
			}
			player.setNextPosition(shortestDistance, true);
		}
		else
		{
			player.setNextPosition(fleetPositions[closestIndex], true);
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
