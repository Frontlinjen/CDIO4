package game;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import desktop_codebehind.Car;
import desktop_resources.GUI;
import slots.OwnableController;
import slots.TerritoryController;
import utilities.ShuffleBag;

public class Board {
	private GameBoard slots = new GameBoard();
	//private List<Player> players = new ArrayList<Player>();
	private Player[] players;
	//private Player currentPlayer;
	private int currentPlayerIndex;
	
	private Prison prison;
	DiceCup dice = new DiceCup(2);
	
	public Board(DiceCup dice)
	{
		this.dice = dice;
		currentPlayerIndex = 0;
	}
	public DiceCup getDice()
	{
		return dice;
	}
	
	public Player getCurrentPlayer()
	{
		return players[currentPlayerIndex];
	}
	/**
	 * Advances to the next player. 
	 */
	private void swapPlayers()
	{		
			
		do
		{
			if(++currentPlayerIndex==players.length)
			{
				prison.advanceDay();
				currentPlayerIndex = 0;
			}
		}while(players[currentPlayerIndex]==null);
		
	}
	/**
	 * Returns the number of players left, so we can tell who the winner is, when count is returned as 1.
	 */
	private int getPlayersLeft()
	{
		int count = 0;
		for (Player player : players) {
			if(player!=null)
			{
				++count;
			}
		}
		return count;
	}
	
	/**
	 * This operation handles the situation where a player is in prison. First it checks if the player
	 * has a chancecard that can set him/her free. If he/she has one get the possibility of using the
	 * card. If not it takes the player to the option of paying 1000 DKK or roll the dice. 
	 * If the option of rolling the way out is chosen, the days left in prison for the player
	 * is checked. If the days left is bigger than zero, the dice has to be rolled. Here we have an
	 * automatic dice, that rolls three times automatic. If one of the rolls gives to equal die, the
	 * player is released, if not null is returned to say, the player is still in prison.
	 */
	private DiceResult tryGetOutOfPrison(Inmate inmate)
	{
		if(getCurrentPlayer().hasGetOutOfPrisonCard() && GUI.getUserLeftButtonPressed(
				Translator.getString("YOUAREINPRISONWITHCARD", getCurrentPlayer().getName()), Translator.getString("YES"), Translator.getString("NO")))
		{
			getCurrentPlayer().setHasGetOutOfPrisonCard(false);
			inmate.release();
		}
		else
		{
			if(GUI.getUserLeftButtonPressed(Translator.getString("YOUAREINPRISON", getCurrentPlayer().getName(), inmate.getDaysLeft()), Translator.getString("PAY1KKR"), Translator.getString("ROLL")))
			{
				if(getCurrentPlayer().getAccount().withdraw(1000))
				{
					inmate.release();
				}
				else
				{
					GUI.showMessage(Translator.getString("NOMONEYNOFUNNY"));
				}
			}
		}
		if(inmate.getDaysLeft()>0)
		{	
			DiceResult res = null;
			DiceCup prisonDice = new DiceCup(2);
			for(int i = 0; i != 3; i++){
				 res = prisonDice.rollDice();
				 GUI.setDice(res.getDice(0), 3, 7, res.getDice(1), 4,8);
				 try
				 {
					 Thread.sleep(400);
				 }
				 catch(Exception e)
				 {
					 System.out.println("Something interrupted the dice roll");
				 }
				 if(res.areDiceEqual())
				 {
					 inmate.release();
					 return res;
				 }
			}
//			//If you failed to roll two equal dices, you skip your turn. 
//			if(!res.areDiceEqual())
//			{
//				return null;
//			}	
		}
		
	return null;
		
	}
	
	/**
	 * This operation is made to make a list of the pawned properties a player owns. Of course, if a
	 * player has not pawned any properties, the GUI will show a message that says, that it is not 
	 * possible to get a list of pawned properties. If the player has a pawned property, the GUI
	 * will make it possible for the player to choose the property to unpawn or cancel the operation.
	 * If anything but cancel is chosen, the selected property is moved back to the array of the players
	 * fields, so it functions as a "normal" field again.
	 */
	private OwnableController getPawnedPropertySelection(Player owner)
	{
		String[] selections = owner.getProperty().getPawnedPropertyList();
		if(selections.length<1)
		{
			GUI.showMessage(Translator.getString("CANNOTUNPAWN"));
		}
		else
		{
			String fieldResponse = GUI.getUserSelection(Translator.getString("MAKESELECTION"), appendCancelOption(selections));
			if(!fieldResponse.equals(Translator.getString("CANCEL")))
			{
				System.out.println(fieldResponse);
				OwnableController selectedField = getCurrentPlayer().getProperty().findOwnableByName(fieldResponse);
				
				return selectedField;
			}
		
		}
		return null;
	}
	
	/**
	 * This operation makes it possible for the player to select a property to pawn.
	 */
	private OwnableController getUnPawnedPropertySelection(Player owner)
	{
		String[] selections = owner.getProperty().getPawnablePropertyList();
		if(selections.length<1)
		{
			GUI.showMessage(Translator.getString("CANNOTUNPAWN"));
		}
		else
		{
			String fieldResponse = GUI.getUserSelection(Translator.getString("MAKESELECTION"), appendCancelOption(selections));
			if(!fieldResponse.equals(Translator.getString("CANCEL")))
			{
				System.out.println(fieldResponse);
				OwnableController selectedField = owner.getProperty().findOwnableByName(fieldResponse);
				return selectedField;
			}
		
		}
		return null;
	}
	private void advanceGame()
	{
		while(getPlayersLeft() > 1) {
			DiceResult res = null;
			int rollsLeft = 3;
			Inmate inmate = prison.getInmate(getCurrentPlayer());
			if (inmate != null){
				res = tryGetOutOfPrison(inmate);
				if(inmate.getDaysLeft()==0)
				{
					GUI.showMessage(Translator.getString("NOWOUTOFPRISON"));
				}
			}
			
			if(inmate==null || inmate.getDaysLeft()==0)
			{
				String buyHouse = Translator.getString("BUYHOUSE", getCurrentPlayer().getName());
				String pawnField = Translator.getString("PAWNFIELD", getCurrentPlayer().getName());
				String releasePawn = Translator.getString("RELEASEFIELD", getCurrentPlayer().getName());
				String rollTurn;
						if(res!=null)
						{
							rollTurn = Translator.getString("MOVEOUTOFPRISON");
						}
						else
						{
							rollTurn = Translator.getString("ROLLTURN");
						}	
				String buyAnothersField = Translator.getString("BUYPLAYERPROPERTY");
				while(true)
				{
					String response = GUI.getUserSelection(Translator.getString("ASKUSER", getCurrentPlayer().getName()), rollTurn, buyHouse, pawnField, releasePawn,buyAnothersField);
					
					
					if(rollTurn.equals(response))
					{
						//In case the player already rolled the dice to get out of prison
						if(res==null)
							res = dice.rollDice();
						break;
					}
					else if(buyHouse.equals(response))
					{
						String[] selections = getCurrentPlayer().getProperty().getTerritoryNames();
						String fieldResponse = GUI.getUserSelection(Translator.getString("UNPAWNFIELD"),  appendCancelOption(selections));
						if(!fieldResponse.equals(Translator.getString("CANCEL")))
						{
							TerritoryController selectedField = getCurrentPlayer().getProperty().findTerritoryByName(fieldResponse);
							if(getCurrentPlayer().getProperty().ownsEntireGroup(selectedField.getFieldGroup())){
							selectedField.buyHouse(getCurrentPlayer());
							}
							else{
								GUI.showMessage(Translator.getString("YOUDONTOWNGROUP"));
							}
						}
					}
					else if(pawnField.equals(response))
					{
						OwnableController slot = getUnPawnedPropertySelection(getCurrentPlayer());
						if(slot!=null)
							pawnField(slot);
					
					}
					else if(releasePawn.equals(response))
					{
						OwnableController slot = getPawnedPropertySelection(getCurrentPlayer());
						if(slot!=null)
							releaseField(slot);
						
					}
					else if(buyAnothersField.equals(response))
					{
						String[] playerNames = new String[getPlayersLeft()-1];
						int index = 0;
						for (Player player : players) {
							if(player!=getCurrentPlayer() && player!=null)
							{
								playerNames[index++] = player.getName();
							}
						}
						String playerSelections = GUI.getUserSelection(Translator.getString("WHOOWNSPROPERTY"), appendCancelOption(playerNames));
						if(!playerSelections.equals(Translator.getString("CANCEL")))
						{
							Player selectedPlayer = getPlayerByName(playerSelections);
							//Cannot buy a pawned field, so we are getting those which are able to be pawned(ie. not pawned already)
									OwnableController selectedField = getUnPawnedPropertySelection(selectedPlayer);
									if(selectedField!=null)
										buyPlayerField(selectedField);
						}
						
					}
					
				}
				
			}
			//2nd check is necessary is send to prison during his turn
			while(rollsLeft>0 && (prison.getInmate(getCurrentPlayer())==null || prison.getInmate(getCurrentPlayer()).getDaysLeft()==0))
			{
				//Since the player has already rolled when selecting to move, we decrease this here
				--rollsLeft;
				GUI.setDice(res.getDice(0), 3, 7, res.getDice(1), 4,8);
				if(rollsLeft==0 && res.areDiceEqual())
				{
					GUI.showMessage(Translator.getString("TOOMANYDOUBLES"));
					prison.addInmate(getCurrentPlayer());
					getCurrentPlayer().setNextPosition(10, false);
					updateCurrentPlayerPosition();
					continue;
				}
				
				
				
				getCurrentPlayer().move(res.getSum(), true);
				while(getCurrentPlayer().getNextPosition()!=getCurrentPlayer().getPosition())
				{
					updateCurrentPlayerPosition();
				}
				
				
				if (getCurrentPlayer().getAccount().getGold() <= 0) {
					getCurrentPlayer().getProperty().resetPlayerProperties();
						GUI.showMessage(Translator.getString("LOSINGPLAYER", getCurrentPlayer().getName()));
						GUI.removeAllCars(getCurrentPlayer().getName());
						players[currentPlayerIndex] = null;
						break;
				}
				if(res.areDiceEqual())
				{
					GUI.showMessage(Translator.getString("EXTRATURN"));
					res = dice.rollDice();
				}
				else
				{
					break;
				}
			
				
			} //what do?
			swapPlayers();
		}
	}
	
	/**
	 * This operation makes it possible to get a player by the name.
	 */
	private Player getPlayerByName(String name)
	{
		for (Player player : players) {
			if(player!=null && player.getName().equals(name))
				return player;
		}
		return null;
	}
	
	/**
	 * 
	 */
	private String[] appendCancelOption(String[] source)
	{
		String[] extendedSelections = new String[source.length+1];
		//This could be implemented by an array loop as well
		System.arraycopy(source, 0, extendedSelections, 0, source.length);
		extendedSelections[extendedSelections.length-1] = Translator.getString("CANCEL");
		return extendedSelections;
	}
	
	/**
	 * This handles the situation where a player wants to buy a field from another player. It is the 
	 * correspondence between the owner and the possible buyer. It works so the owner of the field 
	 * comes with an offer, and the buyer (currentplayer) can choose to accept or not.
	 */
	private void buyPlayerField(OwnableController selectedField) {
		while(true)
		{
			int cost = GUI.getUserInteger(Translator.getString("PLAYERFIELDCOST", selectedField.getOwner().getName()));
			if(GUI.getUserLeftButtonPressed(Translator.getString("PLAYERFIELDACCEPT", getCurrentPlayer().getName(), selectedField.getName()), Translator.getString("YES"), Translator.getString("NO")))
			{
				if(getCurrentPlayer().getAccount().withdraw(cost))
				{
					selectedField.getOwner().getAccount().addGold(cost);
					selectedField.removeOwner();
					selectedField.setOwner(getCurrentPlayer());
					GUI.showMessage(Translator.getString("BOUGHTFIELD", getCurrentPlayer().getName(), cost));
					return;
				}
				else
				{
					GUI.showMessage(Translator.getString("NOMONEYNOFUNNY"));
				}
				
			}
			if(!GUI.getUserLeftButtonPressed(Translator.getString("PLAYERNEWOFFER", selectedField.getOwner().getName()), Translator.getString("YES"), Translator.getString("NO")))
			{
				break;
			}
		}
			
	}
	
	/**
	 * Everytime a player rolls a dice, the position has to be updated. First the car needs to be 
	 * moved from the board, then the players position is set, and at last, the car is put on the 
	 * new field.
	 */
	public void updateCurrentPlayerPosition()
	{
		GUI.removeAllCars(getCurrentPlayer().getName());
		
		getCurrentPlayer().moveToNextPosition();
		System.out.println(getCurrentPlayer().getName());
		GUI.setCar(getCurrentPlayer().getPosition()+1, getCurrentPlayer().getName());
		slots.getField(getCurrentPlayer().getPosition()).landOnField(getCurrentPlayer());
	}
	//Pawns a field, if the field aren't pawned already, and add the pawn gold to the owner
	public void pawnField(OwnableController data){
		if(!data.pawned()){
						
			if(GUI.getUserLeftButtonPressed(Translator.getString("TOPAWN", data.getPawnValue()), 
					Translator.getString("YES"),
					Translator.getString("NO")))
			{
			data.getOwner().getAccount().addGold(data.getPawnValue());
			data.setPawned(true);
			}
			
		}
		else{
			GUI.showMessage(Translator.getString("CANNOTPAWN"));
		}
	}
	
	/*Releases a field from it's pawn, 
	but only if the field are pawned 
	and the owner have enough gold to pay the pawn gold back*/
	public void releaseField(OwnableController data){
		if(data.pawned()){
			if(GUI.getUserLeftButtonPressed(Translator.getString("TOUNPAWN", data.getPawnValue()),
					Translator.getString("YES"),
					Translator.getString("NO")))
			{
			
			if(data.getOwner().getAccount().withdraw(data.getPawnValue()))
			{
				data.setPawned(false);
			}
			else
			{
				GUI.showMessage(Translator.getString("NOMONEYNOFUNNY"));
			}
			
		}
		else{
			GUI.showMessage(Translator.getString("CANNOTUNPAWN"));
		}
		}
}
	
	public void startGame(){
		System.out.println("Starting game..");
		prison = new Prison(6);
		slots.initializeBoard(prison,  players);
		PlayerCreator playerFactory = new PlayerCreator();
		players = playerFactory.setupPlayers();
		advanceGame();
		
		GUI.showMessage(Translator.getString("WINNINGPLAYERNAME", getCurrentPlayer().getName(), getCurrentPlayer().getProperty().getPropertyWorth()));
		GUI.close();
	}
	public static void main(String[] args) {
		Board board = new Board(new DiceCup(2));
		board.startGame();
	}
	@Override
	public String toString() {
		return "Board []";
	}
	

}
