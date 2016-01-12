package game;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.Icon;

import desktop_codebehind.Car;
import desktop_fields.Ownable;
import desktop_resources.GUI;
import slots.FieldController;
import slots.OwnableController;
import slots.OwnableData;
import slots.TerritoryController;
import slots.TerritoryData;
import utilities.ShuffleBag;

public class Board {
	private GameBoard slots = new GameBoard();
	private List<Player> players = new ArrayList<Player>();
	private final int PLAYERSTARTINGCASH = 30000;
	private Player currentPlayer;
	private ShuffleBag<Color> availableCarColors = new ShuffleBag<Color>(new Color[]{Color.BLUE, Color.YELLOW, new Color(0, 107f/255, 15f/255), Color.PINK, Color.RED, Color.MAGENTA});
	private Prison prison;
	
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

		}
		else
		{
			//If it doesn't, then advance to the next player. 
			currentPlayer = players.get(pos+1);
		}
	}

	private void advanceGame()
	{
		while(players.size() > 1) {
			prison.advanceDay();
			DiceResult res = null;
			int rollsLeft = 2;
			GUI.getUserButtonPressed(Translator.getString("NEXTTURN", currentPlayer.getName()), Translator.getString("ROLL"));
			Inmate inmate = prison.getInmate(currentPlayer);
			if (inmate != null){
					for(int i = 0; i != 3; i++){
						 res = currentPlayer.dice.rollDice();
						 if(res.areDiceEqual())
						 {
							 inmate.release();
							 break;
						 }
					}
					//If you failed to roll two equal dices, you skip your turn. 
					if(!res.areDiceEqual())
					{
						rollsLeft = 0;
					}	
			}
			else
			{
				String buyHouse = Translator.getString("BUYHOUSE", currentPlayer.getName());
				String pawnField = Translator.getString("PAWNFIELD", currentPlayer.getName());
				String releasePawn = Translator.getString("UNPAWNFIELD", currentPlayer.getName());
				String rollTurn = Translator.getString("ROLLTURN", currentPlayer.getName());
				while(true)
				{
					String response = GUI.getUserSelection(Translator.getString("ASKUSER", currentPlayer.getName()), rollTurn, buyHouse, pawnField, releasePawn);
					
					if(buyHouse.equals(response))
					{
						String[] selections = currentPlayer.getProperty().getTerritoryNames();
						String[] extendedSelections = new String[selections.length+1];
						//This could be implemented by an array loop as well
						System.arraycopy(selections, 0, extendedSelections, 0, selections.length);
						extendedSelections[extendedSelections.length-1] = "Cancel";
						String fieldResponse = GUI.getUserSelection(Translator.getString("UNPAWNFIELD"),  extendedSelections);
						if(!fieldResponse.equals("Cancel"))
						{
							TerritoryController selectedField = currentPlayer.getProperty().findTerritoryByName(fieldResponse);
							selectedField.buyHouse(currentPlayer);
						}
					}
					else if(pawnField.equals(response))
					{
						String[] selections = currentPlayer.getProperty().getPawnablePropertyList();
						String[] extendedSelections = new String[selections.length+1];
						//This could be implemented by an array loop as well
						System.arraycopy(selections, 0, extendedSelections, 0, selections.length);
						extendedSelections[selections.length-1] = "Cancel";
						String fieldResponse = GUI.getUserSelection(Translator.getString("UNPAWNFIELD"), extendedSelections);
						if(!fieldResponse.equals("Cancel"))
						{
							OwnableController selectedField = currentPlayer.getProperty().findOwnableByName(fieldResponse);
							pawnField(selectedField);
						}
					
					}
					else if(releasePawn.equals(response))
					{
						String[] selections = currentPlayer.getProperty().getPawnedPropertyList();
						String[] extendedSelections = new String[selections.length+1];
						//This could be implemented by an array loop as well
						System.arraycopy(selections, 0, extendedSelections, 0, selections.length);
						extendedSelections[selections.length-1] = "Cancel";
						String fieldResponse = GUI.getUserSelection(Translator.getString("UNPAWNFIELD"), extendedSelections);
						if(!fieldResponse.equals("Cancel"))
						{
							OwnableController selectedField = currentPlayer.getProperty().findOwnableByName(fieldResponse);
							releaseField(selectedField);
						}
						
					}
						
					else if (rollTurn.equals(response))
					{
						res = currentPlayer.getDice().rollDice();
						break;
					}
					
				}
				
			}
			while(rollsLeft!=0)
			{
				GUI.setDice(res.getDice(0), 3, 7, res.getDice(1), 4,8);
				
				currentPlayer.move(res.getSum(), true);
				while(currentPlayer.getNextPosition()!=currentPlayer.getPosition())
				{
					GUI.removeAllCars(currentPlayer.getName());
					
					currentPlayer.moveToNextPosition();
					
					GUI.setCar(currentPlayer.getPosition()+1, currentPlayer.getName());
					slots.getField(currentPlayer.getPosition()).landOnField(currentPlayer);
				}
				
				
				if (currentPlayer.getAccount().getGold() <= 0) {
					OwnableController[] properties = currentPlayer.getProperty().getPropertiesOwned();
						for (OwnableController property : properties) {
							property.removeOwner();
						}
						GUI.showMessage(Translator.getString("LOSINGPLAYER", currentPlayer.getName()));
						players.remove(currentPlayer);
						GUI.removeAllCars(currentPlayer.getName());
						break;
				}
				if(res.areDiceEqual())
				{
					res = currentPlayer.getDice().rollDice();
					--rollsLeft;
				}
				else
				{
					break;
				}
				if(rollsLeft==0)
				{
					currentPlayer.setNextPosition(11, false);
				}
			} //what do?
			swapPlayers();
		}
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
