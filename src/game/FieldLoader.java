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
	
	private static TerritoryController parseTerritory(Element e) throws Exception
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
			return new TerritoryController(newData);
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse Territory", exc);
		}
		
	}
	
	private static EmptyFieldController parseEmptyField(Element e) throws Exception
	{
		System.out.println("Parsing empty field...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			int translateID = parseInteger(translateNode);
			FieldData newData = new FieldData(translateID);
			return new EmptyFieldController(newData);
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse EmptyField", exc);
		}
		
	}
	
	private static ParkinglotController parseParkinglot(Element e, Account parkingAcc) throws Exception
	{
		System.out.println("Parsing refuge...");
		try {
			
			Node translateNode = getUnique(e, "translateID");
			Node bonusNode = getUnique(e, "bonus");
			int translateID = parseInteger(translateNode);
			ParkinglotData newData = new ParkinglotData(translateID, parkingAcc);
			return new ParkinglotController(newData);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Refuge", exc);
		
		}
		
	}
	private static BreweryController parseBrewery(Element e) throws Exception
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
			return new BreweryController(newData);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse LaborCamp", exc);
		
		}
	}
	private static TaxController parseTax(Element e, Account parkingAcc) throws Exception
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
			return new TaxController(newData, parkingAcc);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Tax", exc);
		
		}
	}
	private static FleetController parseFleet(Element e) throws Exception
	{
		System.out.println("Parsing fleet...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node priceNode = getUnique(e, "price");
			
			int translateID = parseInteger(translateNode);
			int price = parseInteger(priceNode);
			
			FleetData newData = new FleetData(translateID, price);
			return new FleetController(newData);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Tax", exc);
		
		}
	}
	static public FieldController[] parseFields(String path, ShuffleBag<ChanceCardController> chanceCards, Prison prison, Account parkinglotAccount)
	{
		
			
			try
			{
				
				Document fields = getXMLDocument(path);
				
				/***
				 * Parses over the fields in the XML document, seperated by types. 
				 */
				NodeList fieldNodes = fields.getElementsByTagName("field");
				List<FieldController> fieldList = new ArrayList<FieldController>();
				System.out.println(fieldList.size());
				for(int index=0;index < fieldNodes.getLength();++index)
				{
					Node node = fieldNodes.item(index);
					//Saveguard to check if the node actually is an element and not a comment, etc. 
					if(node.getNodeType()==Node.ELEMENT_NODE)
					{
						Element element = (Element)node;
						switch(element.getAttribute("type"))
						{
							case "territory":
							{
								FieldController f = parseTerritory(element);
								fieldList.add(f);
								break;
							}
							case "empty":
							{
								FieldController f = parseEmptyField(element);
								fieldList.add(f);
								break;
							}
							case "brewery":
							{
								FieldController f = parseBrewery(element);
								fieldList.add(f);
								break;
							}
							case "tax":
							{
								FieldController f = parseTax(element, parkinglotAccount);
								fieldList.add(f);
								break;
							}
							case "fleet":
							{
								FieldController f = parseFleet(element);
								fieldList.add(f);
								break;
							}
							case "chancefield":
							{
								FieldController f = parseChanceCard(element, chanceCards);
								fieldList.add(f);
								break;
							}
							case "gotoprison":
							{
								FieldController f = parseGotoPrison(element, prison);
								fieldList.add(f);
								break;
							}
							case "parkinglot":
							{
								FieldController f = parseParkinglot(element, parkinglotAccount);
								fieldList.add(f);
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
	private static FieldController parseGotoPrison(Element e, Prison p) throws Exception {
		System.out.println("Parsing gotoPrison...");
		try {
			
			Node translateNode = getUnique(e, "translateID");
			Node prisonNode = getUnique(e, "prisonPosition");
			int translateID = parseInteger(translateNode);
			int prisonLocation = parseInteger(prisonNode);
			GoToPrisonData newData = new GoToPrisonData(translateID, prisonLocation, p);
			return new GoToPrisonController(newData);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse gotoPrison", exc);
		
		}
	}
	private static FieldController parseChanceCard(Element element, ShuffleBag<ChanceCardController> cards) throws Exception {
		
		System.out.println("Parsing chanceCard...");
		try {
			return new ChanceFieldController(cards);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse chancecardfield", exc);
		
		}
	}
	
}
