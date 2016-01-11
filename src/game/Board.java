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
				String releaseField = Translator.getString("PAWNFIELD", currentPlayer.getName());
				String rollTurn = Translator.getString("ROLLTURN", currentPlayer.getName());
				String response = GUI.getUserSelection(Translator.getString("ASKUSER", currentPlayer.getName()), buyHouse, pawnField, releaseField, rollTurn);
				switch(response){
				case buyHouse: 
					int fieldResponse = GUI.getUserInteger(Translator.getString("WHATFIELD", currentPlayer.getName()));
					if(slots.getField(fieldResponse)){
						
						
					}
					break;
				case pawnField: pawnField(null);
					break;
				case releaseField: releaseField(null);
					break;
				case rollTurn: res = currentPlayer.getDice().rollDice();
					break;
				default:
					break;
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
					
					GUI.setCar(currentPlayer.getPosition(), currentPlayer.getName());
					slots.getField(currentPlayer.getPosition()).landOnField(currentPlayer);
				}
				
				
				if (currentPlayer.getAccount().getGold() <= 0) {
					Iterator<OwnableController> iterator = currentPlayer.getProperty().getPropertiesOwned();
						while(iterator.hasNext()){
							iterator.next().removeOwner();
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
	public void pawnField(OwnableData data){
		if(!data.pawned()){
			data.getOwner().getAccount().addGold(data.getWorth(null));
			data.setPawned(true);
		}
		
	}
	
	/*Releases a field from it's pawn, 
	but only if the field are pawned 
	and the owner have enough gold to pay the pawn gold back*/
	public void releaseField(OwnableData data){
		if(data.pawned() && data.getOwner().getAccount().withdraw(data.getWorth(null))){
			data.setPawned(false);
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
