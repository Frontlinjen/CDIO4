package game;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Chancecards.ChanceCardBuildingTaxController;
import Chancecards.ChanceCardBuildingTaxData;
import Chancecards.ChanceCardCashBonusController;
import Chancecards.ChanceCardCashData;
import Chancecards.ChanceCardCashTransferController;
import Chancecards.ChanceCardController;
import Chancecards.ChanceCardData;
import Chancecards.ChanceCardGoToPrisonController;
import Chancecards.ChanceCardGoToPrisonData;
import Chancecards.ChanceCardMatadorLegatController;
import Chancecards.ChanceCardMovePlayerController;
import Chancecards.ChanceCardMovePlayerData;
import Chancecards.ChanceCardMovePlayerRelativeController;
import Chancecards.ChanceCardMovePlayerRelativeData;
import Chancecards.ChanceCardMoveToNextFleetController;
import Chancecards.ChanceCardMoveToNextFleetData;
import Chancecards.ChanceCardOutOfPrisonController;
import Chancecards.ChanceCardTaxController;

public class ChanceCardLoader extends XMLParser{

	private static ChanceCardBuildingTaxData parseBuildingTax(Element e) throws Exception
	{
		System.out.println("Parsing building tax chancecard...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			Node houseTaxNode = getUnique(e, "prhouse");
			Node hotelTaxNode = getUnique(e, "prhotel");
			int translateID = parseInteger(translateNode);
			int houseTax = parseInteger(houseTaxNode);
			int hotelTax = parseInteger(hotelTaxNode);
			ChanceCardBuildingTaxData newData = new ChanceCardBuildingTaxData(translateID, houseTax, hotelTax);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse ChancecardBuildingTax", exc);
		}
	}
	
	private static ChanceCardCashData parseCash(Element e) throws Exception
	{
		System.out.println("Parsing cash chancecard...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			Node cashNode = getUnique(e, "cash");
			int translateID = parseInteger(translateNode);
			int cash = parseInteger(cashNode);
			ChanceCardCashData newData = new ChanceCardCashData(translateID, cash);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse cash chancecard", exc);
		}
	}
	
	private static ChanceCardMovePlayerData parseMovePlayer(Element e) throws Exception
	{
		System.out.println("Parsing cash chancecard...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			Node fieldnumberNode = getUnique(e, "fieldnumber");
			Node cashatstartNode = getUnique(e, "cashatstart");
			int translateID = parseInteger(translateNode);
			int fieldnumber = parseInteger(fieldnumberNode);
			int cashatstart = parseInteger(cashatstartNode);
			ChanceCardMovePlayerData newData = new ChanceCardMovePlayerData(translateID, fieldnumber, cashatstart==1);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse cash chancecard", exc);
		}	
	}
	
	private static ChanceCardMovePlayerRelativeData parseMovePlayerRelative(Element e) throws Exception
	{
		System.out.println("Parsing cash chancecard...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			Node fieldsNode = getUnique(e, "fieldnumber");
			Node cashatstartNode = getUnique(e, "cashatstart");
			int translateID = parseInteger(translateNode);
			int fields = parseInteger(fieldsNode);
			int cashatstart = parseInteger(cashatstartNode);
			ChanceCardMovePlayerRelativeData newData = new ChanceCardMovePlayerRelativeData(translateID, fields, cashatstart==1);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse cash chancecard", exc);
		}	
	}
	
	private static ChanceCardMoveToNextFleetData parseMoveToNextFleet(Element e) throws Exception
	{
		System.out.println("Parsing cash chancecard...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			Node fleetPosition1Node = getUnique(e, "fleetPosition1");
			Node fleetPosition2Node = getUnique(e, "fleetPosition2");
			Node fleetPosition3Node = getUnique(e, "fleetPosition3");
			Node fleetPosition4Node = getUnique(e, "fleetPosition4");
			Node cashatstartNode = getUnique(e, "cashatstart");
			int translateID = parseInteger(translateNode);
			int fleetPosition1 = parseInteger(fleetPosition1Node);
			int fleetPosition2 = parseInteger(fleetPosition2Node);
			int fleetPosition3 = parseInteger(fleetPosition3Node);
			int fleetPosition4 = parseInteger(fleetPosition4Node);
			int cashatstart = parseInteger(cashatstartNode);
			ChanceCardMoveToNextFleetData newData = new ChanceCardMoveToNextFleetData(translateID, new int[] {fleetPosition1, fleetPosition2, fleetPosition3, fleetPosition4}, cashatstart==1);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse cash chancecard", exc);
		}	
	}
	
	private static ChanceCardData parseChanceCard(Element e) throws Exception
	{
		System.out.println("Parsing cash chancecard...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			int translateID = parseInteger(translateNode);
			ChanceCardData newData = new ChanceCardData(translateID);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse cash chancecard", exc);
		}	
	}
	
	private static ChanceCardGoToPrisonData parseGoToPrison(Element e) throws Exception
	{
		System.out.println("Parsing cash chancecard...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			Node fieldnumberNode = getUnique(e, "fieldnumber");
			int translateID = parseInteger(translateNode);
			int fieldnumber = parseInteger(fieldnumberNode);
			ChanceCardGoToPrisonData newData = new ChanceCardGoToPrisonData(translateID, fieldnumber);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse cash chancecard", exc);
		}	
	}
	
	static public ChanceCardController[] parseChanceCards(String path, Account parkinglotAcc, Prison prison, Player[] players)
	{
		
			
			try
			{
				
				Document cards = getXMLDocument(path);
				
				/***
				 * Parses over the chancecards in the XML document, seperated by types. 
				 */
				NodeList cardNodes = cards.getElementsByTagName("card");
				List<ChanceCardController> cardList = new ArrayList<ChanceCardController>();
				for(int index=0;index < cardNodes.getLength();++index)
				{
					Node node = cardNodes.item(index);
					//Saveguard to check if the node actually is an element and not a comment, etc. 
					if(node.getNodeType()==Node.ELEMENT_NODE)
					{
						ChanceCardController newController = null;
						Element element = (Element)node;
						switch(element.getAttribute("type"))
						{
							case "bonus":
							{
								ChanceCardCashData newCard = parseCash(element);
								newController = new ChanceCardCashBonusController(newCard);
								break;
							}
							case "tax":
							{
								ChanceCardCashData newCard = parseCash(element);
								newController = new ChanceCardTaxController(newCard, parkinglotAcc);
								break;
							}
							case "cashtransfer":
							{
								ChanceCardCashData newCard = parseCash(element);
								newController = new ChanceCardCashTransferController(newCard, players);
								break;
							}
							case "matadorlegat":
							{
								ChanceCardCashData newCard = parseCash(element);
								newController = new ChanceCardMatadorLegatController(newCard);
								break;
							}
							case "moveplayer":
							{
								ChanceCardMovePlayerData newCard = parseMovePlayer(element);
								newController = new ChanceCardMovePlayerController(newCard);
								break;
							}
							case "moveplayerrelative":
							{
								ChanceCardMovePlayerRelativeData newCard =parseMovePlayerRelative(element);
								newController = new ChanceCardMovePlayerRelativeController(newCard);
								break;
							}
							case "movetonextfleet":
							{
								ChanceCardMoveToNextFleetData newCard =parseMoveToNextFleet(element);
								newController = new ChanceCardMoveToNextFleetController(newCard);
								break;
							}
							case "outofprison":
							{
								ChanceCardData newCard =parseChanceCard(element);
								newController = new ChanceCardOutOfPrisonController(newCard);
								break;
							}
							case "buildingtax":
							{
								ChanceCardBuildingTaxData newCard =parseBuildingTax(element);
								newController = new ChanceCardBuildingTaxController(newCard);
								break;
							}
							case "gotoprison":
							{
								ChanceCardGoToPrisonData newCard =parseGoToPrison(element);
								newController = new ChanceCardGoToPrisonController(newCard, prison);
								break;
							}
							default:
							{
								System.out.println("Unknown type: " + element.getAttribute("type") + " detected!");
							}
							
						}
						if(newController!=null)
						{
							int amount = 1;
							if(element.hasAttribute("amount"))
							{
								String strAmount = element.getAttribute("amount");
								 amount = Integer.parseInt(strAmount);
							}
							
							while(amount-->0)
							{
								cardList.add(newController);
							}
						}
					}
				}
				ChanceCardController[] retCards = new ChanceCardController[cardList.size()];
				retCards = cardList.toArray(retCards);
				return retCards;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	
}
