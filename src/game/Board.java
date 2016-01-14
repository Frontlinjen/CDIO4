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
	private List<Player> players = new ArrayList<Player>();
	private final int PLAYERSTARTINGCASH = 30000;
	private Player currentPlayer;
	private ShuffleBag<Color> availableCarColors = new ShuffleBag<Color>(new Color[]{Color.BLUE, Color.YELLOW, new Color(0, 107f/255, 15f/255), Color.PINK, Color.RED, Color.MAGENTA});
	private Prison prison;
	
	DiceCup dice = new DiceCup(2);
	
	public DiceCup getDice()
	{
		return dice;
	}
	
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	public void createPlayer(String name)
	{
		Player newPlayer = new Player(name);
		players.add(newPlayer);
		Color color = Color.white;
		try {
			color = availableCarColors.getNext();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Car car;
		int result = (int)((Math.random())*3+1);
		if(result == 1)
		{
			car = new Car.Builder().primaryColor(color).secondaryColor(Color.black).patternZebra().build();

		}
		else if(result == 2)
		{
			car = new Car.Builder().primaryColor(color).secondaryColor(Color.black).patternDotted().build();
		}
		else
		{
			car = new Car.Builder().primaryColor(color).secondaryColor(Color.black).patternCheckered().build();
		}
		GUI.addPlayer(name, PLAYERSTARTINGCASH, car);

		desktop_board.Board.getInstance().updatePlayers();
	}
	private boolean verifyName(String s)
	{
		if(s.isEmpty())
		{
			return false;
		}
		
			//Checks if the string contains a whitespace character
			Pattern pattern = Pattern.compile("\\s");
			java.util.regex.Matcher m = pattern.matcher(s);
			
			return (!m.find());
		
	}
	private boolean setupPlayer(String user){
		if (!verifyName(user) || user.length() > 15)
		{
			return false;
		}
		for(Player i : players) {
			if (i.getName().equals(user)){
				return false;
			}
		}
		createPlayer(user);
		return true;
	}
	
	private void setupPlayers(int i) {
		for(int j = 0; j < i; j++) {
			String user;
			if(players.isEmpty())
			{
				 user = GUI.getUserString(Translator.getString("ENTERNAME1"));
			}
			else
			{
				user = GUI.getUserString(Translator.getString("ENTERNAME2"));
			}
		while(setupPlayer(user) == false) {
			user = GUI.getUserString(Translator.getString("NAMEERROR"));
		}
		}
		currentPlayer = players.get(0);
	}

	/**
	 * Advances to the next player. 
	 */
	private void swapPlayers()
	{
		int pos = players.indexOf(currentPlayer);
		//Checks to see if we will surpass the bonds 
		if(pos+1 >= players.size())
		{
			//If it does, then we have reached the end of the list and shall therefore start over
			currentPlayer = players.get(0);
			
			//If everyone has had their turn we also decrease the amount of days the players has to be in prison:
			prison.advanceDay();
		}
		else
		{
			//If it doesn't, then advance to the next player. 
			currentPlayer = players.get(pos+1);
		}
	}
	private DiceResult tryGetOutOfPrison(Inmate inmate)
	{
		if(currentPlayer.hasGetOutOfPrisonCard() && GUI.getUserLeftButtonPressed(
				Translator.getString("YOUAREINPRISONWITHCARD", currentPlayer.getName()), Translator.getString("YES"), Translator.getString("NO")))
		{
			currentPlayer.setHasGetOutOfPrisonCard(false);
			inmate.release();
		}
		else
		{
			if(GUI.getUserLeftButtonPressed(Translator.getString("YOUAREINPRISON", currentPlayer.getName(), inmate.getDaysLeft()), Translator.getString("PAY1KKR"), Translator.getString("ROLL")))
			{
				if(currentPlayer.getAccount().withdraw(1000))
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
			for(int i = 0; i != 3; i++){
				 res = currentPlayer.dice.rollDice();
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

	private void advanceGame()
	{
		while(players.size() > 1) {
			DiceResult res = null;
			int rollsLeft = 3;
			Inmate inmate = prison.getInmate(currentPlayer);
			if (inmate != null){
				res = tryGetOutOfPrison(inmate);
				if(inmate.getDaysLeft()==0)
				{
					GUI.showMessage(Translator.getString("NOWOUTOFPRISON"));
				}
			}
			
			if(inmate==null || inmate.getDaysLeft()==0)
			{
				String buyHouse = Translator.getString("BUYHOUSE", currentPlayer.getName());
				String pawnField = Translator.getString("PAWNFIELD", currentPlayer.getName());
				String releasePawn = Translator.getString("RELEASEFIELD", currentPlayer.getName());
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
					String response = GUI.getUserSelection(Translator.getString("ASKUSER", currentPlayer.getName()), rollTurn, buyHouse, pawnField, releasePawn,buyAnothersField);
					
					
					if(rollTurn.equals(response))
					{
						//In case the player already rolled the dice to get out of prison
						if(res==null)
							res = currentPlayer.getDice().rollDice();
						break;
					}
					else if(buyHouse.equals(response))
					{
						String[] selections = currentPlayer.getProperty().getTerritoryNames();
						String[] extendedSelections = new String[selections.length+1];
						//This could be implemented by an array loop as well
						System.arraycopy(selections, 0, extendedSelections, 0, selections.length);
						extendedSelections[extendedSelections.length-1] = Translator.getString("CANCEL");;
						String fieldResponse = GUI.getUserSelection(Translator.getString("UNPAWNFIELD"),  extendedSelections);
						if(!fieldResponse.equals(Translator.getString("CANCEL")))
						{
							TerritoryController selectedField = currentPlayer.getProperty().findTerritoryByName(fieldResponse);
							selectedField.buyHouse(currentPlayer);
						}
					}
					else if(pawnField.equals(response))
					{
						String[] selections = currentPlayer.getProperty().getPawnablePropertyList();
						if(selections.length<1)
						{
							GUI.showMessage(Translator.getString("CANNOTUNPAWN"));
						}
						else
						{
							String[] extendedSelections = new String[selections.length+1];
							//This could be implemented by an array loop as well
							System.arraycopy(selections, 0, extendedSelections, 0, selections.length);
							extendedSelections[extendedSelections.length-1] = Translator.getString("CANCEL");;
							String fieldResponse = GUI.getUserSelection(Translator.getString("UNPAWNFIELD"), extendedSelections);
							if(!fieldResponse.equals(Translator.getString("CANCEL")))
							{
								System.out.println(fieldResponse);
								OwnableController selectedField = currentPlayer.getProperty().findOwnableByName(fieldResponse);
								pawnField(selectedField);
							}
						
						}
					
					}
					else if(releasePawn.equals(response))
					{
						String[] selections = currentPlayer.getProperty().getPawnedPropertyList();
						if(selections.length<1)
						{
							GUI.showMessage(Translator.getString("CANNOTUNPAWN"));
						}
						else
						{
							String[] extendedSelections = new String[selections.length+1];
							//This could be implemented by an array loop as well
							System.arraycopy(selections, 0, extendedSelections, 0, selections.length);
							extendedSelections[extendedSelections.length-1] = Translator.getString("CANCEL");
							String fieldResponse = GUI.getUserSelection(Translator.getString("UNPAWNFIELD"), extendedSelections);
							if(!fieldResponse.equals(Translator.getString("CANCEL")))
							{
								OwnableController selectedField = currentPlayer.getProperty().findOwnableByName(fieldResponse);
								releaseField(selectedField);
							}
						}
						
						
					}
					else if(buyAnothersField.equals(response))
					{
						String[] playerNames = new String[players.size()-1];
						int index = 0;
						for (Player player : players) {
							if(player!=currentPlayer)
							{
								playerNames[index++] = player.getName();
							}
						}
						String playerSelections = GUI.getUserSelection(Translator.getString("WHOOWNSPROPERTY"), appendCancelOption(playerNames));
						if(!playerSelections.equals(Translator.getString("CANCEL")))
						{
							Player selectedPlayer = getPlayerByName(playerSelections);
							//Cannot buy a pawned field, so we are getting those which are able to be pawned(ie. not pawned already)
							String[] selections = selectedPlayer.getProperty().getPawnablePropertyList();
							if(selections.length<1)
							{
								GUI.showMessage(Translator.getString("CANNOTUNPAWN"));
							}
							else
							{
								String[] extendedSelections = appendCancelOption(selections);
								String fieldResponse = GUI.getUserSelection(Translator.getString("UNPAWNFIELD"), extendedSelections);
								if(!fieldResponse.equals(Translator.getString("CANCEL")))
								{
									OwnableController selectedField = selectedPlayer.getProperty().findOwnableByName(fieldResponse);
									buyPlayerField(selectedField);
								}
							}
						}
						
					}
					
				}
				
			}
			//2nd check is necessary is send to prison during his turn
			while(rollsLeft>0 && (prison.getInmate(currentPlayer)==null || prison.getInmate(currentPlayer).getDaysLeft()==0))
			{
				//Since the player has already rolled when selecting to move, we decrease this here
				--rollsLeft;
				if(rollsLeft==0)
				{
					GUI.showMessage(Translator.getString("TOOMANYDOUBLES"));
					prison.addInmate(getCurrentPlayer());
					currentPlayer.setNextPosition(10, false);
					updateCurrentPlayerPosition();
					continue;
				}
				
				GUI.setDice(res.getDice(0), 3, 7, res.getDice(1), 4,8);
				
				currentPlayer.move(res.getSum(), true);
				while(currentPlayer.getNextPosition()!=currentPlayer.getPosition())
				{
					updateCurrentPlayerPosition();
				}
				
				
				if (currentPlayer.getAccount().getGold() <= 0) {
						currentPlayer.getProperty().resetPlayerProperties();
						GUI.showMessage(Translator.getString("LOSINGPLAYER", currentPlayer.getName()));
						GUI.removeAllCars(currentPlayer.getName());
						players.remove(currentPlayer);
						break;
				}
				if(res.areDiceEqual())
				{
					GUI.showMessage(Translator.getString("EXTRATURN"));
					res = currentPlayer.getDice().rollDice();
				}
				else
				{
					break;
				}
			
				
			} //what do?
			swapPlayers();
		}
	}
	
	private Player getPlayerByName(String name)
	{
		for (Player player : players) {
			if(player.getName().equals(name))
				return player;
		}
		return null;
	}
	private String[] appendCancelOption(String[] source)
	{
		String[] extendedSelections = new String[source.length+1];
		//This could be implemented by an array loop as well
		System.arraycopy(source, 0, extendedSelections, 0, source.length);
		extendedSelections[extendedSelections.length-1] = Translator.getString("CANCEL");
		return extendedSelections;
	}
	
	private void buyPlayerField(OwnableController selectedField) {
		while(true)
		{
			int cost = GUI.getUserInteger(Translator.getString("PLAYERFIELDCOST", selectedField.getOwner().getName()));
			if(GUI.getUserLeftButtonPressed(Translator.getString("PLAYERFIELDACCEPT", currentPlayer.getName(), selectedField.getName()), Translator.getString("YES"), Translator.getString("NO")))
			{
				if(currentPlayer.getAccount().withdraw(cost))
				{
					selectedField.removeOwner();
					selectedField.setOwner(currentPlayer);
					GUI.showMessage(Translator.getString("BOUGHTFIELD", currentPlayer.getName(), cost));
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
	public void updateCurrentPlayerPosition()
	{
		GUI.removeAllCars(currentPlayer.getName());
		
		currentPlayer.moveToNextPosition();
		
		GUI.setCar(currentPlayer.getPosition()+1, currentPlayer.getName());
		slots.getField(currentPlayer.getPosition()).landOnField(currentPlayer);
	}
	//Pawns a field, if the field aren't pawned already, and add the pawn gold to the owner
	public void pawnField(OwnableController data){
		if(!data.pawned()){
						
			if(GUI.getUserLeftButtonPressed(Translator.getString("TOPAWN", data.getWorth()), 
					Translator.getString("YES"),
					Translator.getString("NO")))
			{
			data.getOwner().getAccount().addGold(data.getWorth());
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
			if(GUI.getUserLeftButtonPressed(Translator.getString("TOUNPAWN", data.getWorth()),
					Translator.getString("YES"),
					Translator.getString("NO")))
			{
			data.getOwner().getAccount().withdraw(data.getWorth());
			data.setPawned(false);
			
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
		int amount = GUI.getUserInteger(Translator.getString("NUMBEROFPLAYERS"));
		final int PLAYERAMOUNTMIN = 2;
		final int PLAYERAMOUNTMAX = 6;
		while(amount < PLAYERAMOUNTMIN || amount > PLAYERAMOUNTMAX){
			GUI.showMessage(Translator.getString("NUMBEROFPLAYERSERROR",PLAYERAMOUNTMIN,PLAYERAMOUNTMAX));
			
			amount = GUI.getUserInteger(Translator.getString("NUMBEROFPLAYERS"));	
		}
		setupPlayers(amount);
		advanceGame();
		
		GUI.showMessage(Translator.getString("WINNINGPLAYERNAME", currentPlayer.getName(), currentPlayer.getAccount().getGold()));
		GUI.close();
	}
	public static void main(String[] args) {
		Board board = new Board();
		board.startGame();
	}
	@Override
	public String toString() {
		return "Board []";
	}
	

}
