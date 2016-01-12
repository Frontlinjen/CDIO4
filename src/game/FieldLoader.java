package game;
import slots.*;
import utilities.ShuffleBag;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Chancecards.ChanceCardController;


public class FieldLoader extends XMLParser {
	
	private static TerritoryData parseTerritory(Element e) throws Exception
	{
		System.out.println("Parsing territory...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			Node groupNode = getUnique(e, "groupID");
			Node rentNode = getUnique(e, "rent");
			Node priceNode = getUnique(e, "price");
			Node housepriceNode = getUnique(e, "houseprice");
			int translateID = parseInteger(translateNode);
			int groupID = parseInteger(groupNode);
			int rent = parseInteger(rentNode);
			int price = parseInteger(priceNode);
			int houseprice = parseInteger(housepriceNode);
			TerritoryData newData = new TerritoryData(translateID, groupID, price, rent, houseprice);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse Territory", exc);
		}
		
	}
	
	private static FieldData parseEmptyField(Element e) throws Exception
	{
		System.out.println("Parsing empty field...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			int translateID = parseInteger(translateNode);
			FieldData newData = new FieldData(translateID);
			return newData;
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse EmptyField", exc);
		}
		
	}
	
	private static ParkinglotData parseParkinglot(Element e, Account parkingAcc) throws Exception
	{
		System.out.println("Parsing refuge...");
		try {
			
			Node translateNode = getUnique(e, "translateID");
			Node bonusNode = getUnique(e, "bonus");
			int translateID = parseInteger(translateNode);
			ParkinglotData newData = new ParkinglotData(translateID, parkingAcc);
			return newData;
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Refuge", exc);
		
		}
		
	}
	private static BreweryData parseBrewery(Element e) throws Exception
	{
		System.out.println("Parsing laborCamp...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node rentNode = getUnique(e, "rent");
			Node priceNode = getUnique(e, "price");
			int translateID = parseInteger(translateNode);
			int rent = parseInteger(rentNode);
			int price = parseInteger(priceNode);
			BreweryData newData = new BreweryData(rent,translateID, price );
			return newData;
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse LaborCamp", exc);
		
		}
	}
	private static TaxData parseTax(FieldController fieldController, Element e, Account parkingAcc) throws Exception
	{
		System.out.println("Parsing tax...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node taxNode = getUnique(e, "tax");
			Node taxPercentageNode = getUnique(e, "taxPercentage");
			int translateID = parseInteger(translateNode);
			int tax = parseInteger(taxNode);
			int taxPercentage = parseInteger(taxPercentageNode); 
			TaxData newData = new TaxData(translateID, tax, taxPercentage);
			return newData;
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Tax", exc);
		
		}
	}
	private static FleetData parseFleet(Element e) throws Exception
	{
		System.out.println("Parsing fleet...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node priceNode = getUnique(e, "price");
			int translateID = parseInteger(translateNode);
			int price = parseInteger(priceNode);
			FleetData newData = new FleetData(translateID, price);
			return newData;
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Tax", exc);
		
		}
	}
	private static GoToPrisonData parseGoToPrison(Element e, Prison p) throws Exception {
		System.out.println("Parsing gotoPrison...");
		try {
			
			Node translateNode = getUnique(e, "translateID");
			Node prisonNode = getUnique(e, "prisonPosition");
			int translateID = parseInteger(translateNode);
			int prisonLocation = parseInteger(prisonNode);
			GoToPrisonData newData = new GoToPrisonData(translateID, prisonLocation, p);
			return newData;
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse gotoPrison", exc);
		
		}
	}
	static public FieldController[] parseFields(String path, ShuffleBag<ChanceCardController> chanceCards, Prison prison, Account parkinglotAccount, FieldController fieldController, FieldData data)
	{
		
			
			try
			{
				
				Document fields = getXMLDocument(path);
				
				/***
				 * Parses over the fields in the XML document, seperated by types. 
				 */
				NodeList fieldNodes = fields.getElementsByTagName("field");
				List<FieldController> fieldList = new ArrayList<FieldController>();
				for(int index=0;index < fieldNodes.getLength();++index)
				{
					Node node = fieldNodes.item(index);
					//Saveguard to check if the node actually is an element and not a comment, etc. 
					if(node.getNodeType()==Node.ELEMENT_NODE)
					{
						FieldController newController = null;
						Element element = (Element)node;
						switch(element.getAttribute("type"))
						{
							case "territory":
							{
								TerritoryData newField = parseTerritory(element);
								newController = new TerritoryController(newField);
								break;
							}
							case "empty":
							{
								FieldData newField = parseEmptyField(element);
								newController = new EmptyFieldController(newField);
								break;
							}
							case "brewery":
							{
								BreweryData newField = parseBrewery(element);
								newController = new BreweryController(newField);
								break;
							}
							case "tax":
							{
								TaxData newField = parseTax(fieldController, element, parkinglotAccount);
								newController = new TaxController(fieldController, newField, parkinglotAccount);
								break;
							}
							case "fleet":
							{
								FleetData newField = parseFleet(element);
								newController = new FleetController(newField);
								break;
							}
							case "chancefield":
							{
								FieldData newField = new FieldData(3);
								newController = new ChanceFieldController(chanceCards, newField);
								break;
							}
							case "gotoprison":
							{
								GoToPrisonData newField = parseGoToPrison(element, prison);
								newController = new GoToPrisonController(newField);
								break;
							}
							case "parkinglot":
							{
								ParkinglotData newField = parseParkinglot(element, parkinglotAccount);
								newController = new ParkinglotController(newField);
								break;
							}
							default:
							{
								System.out.println("Unknown type: " + element.getAttribute("type") + " detected!");
							}
						}
					}
				}
				FieldController[] retFields = new FieldController[fieldList.size()];
				retFields = fieldList.toArray(retFields);
				return retFields;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

}
