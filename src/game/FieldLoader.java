package game;
import slots.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Chancecards.ChanceCard;


public class FieldLoader extends XMLParser {
	
	private static TerritoryController parseTerritory(Element e) throws Exception
	{
		System.out.println("Parsing territory...");
		try
		{
			Node translateNode = getUnique(e, "translateID");
			Node rentNode = getUnique(e, "rent");
			Node priceNode = getUnique(e, "price");
			int translateID = parseInteger(translateNode);
			int rent = parseInteger(rentNode);
			int price = parseInteger(priceNode);
			TerritoryData newData = new TerritoryData(translateID, price, rent);
			return new TerritoryController(newData);
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse Territory", exc);
		}
		
	}
	private static ParkinglotController parseRefuge(Element e) throws Exception
	{
		System.out.println("Parsing refuge...");
		try {
			
			Node translateNode = getUnique(e, "translateID");
			Node bonusNode = getUnique(e, "bonus");
			int translateID = parseInteger(translateNode);
			int bonus = parseInteger(bonusNode);
			ParkinglotData newData = new ParkinglotData(translateID, bonus);
			return new ParkinglotController(newData);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Refuge", exc);
		
		}
		
	}
	private static BreweryController parseLaborCamp(Element e) throws Exception
	{
		System.out.println("Parsing labotCamp...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node rentNode = getUnique(e, "rent");
			Node priceNode = getUnique(e, "price");
			int translateID = parseInteger(translateNode);
			int rent = parseInteger(rentNode);
			int price = parseInteger(priceNode);
			BreweryData newData = new BreweryData(translateID, price, rent);
			return new BreweryController(newData);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse LaborCamp", exc);
		
		}
	}
	private static TaxController parseTax(Element e) throws Exception
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
			return new TaxController(newData);
			
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
	static public FieldController[] parseFields(String path, ChanceCard[] chanceCards, Prison prison)
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
						Element element = (Element)node;
						switch(element.getAttribute("type"))
						{
							case "territory":
							{
								FieldController f = parseTerritory(element);
								fieldList.add(f);
								break;
							}
							case "refuge":
							{
								FieldController f = parseRefuge(element);
								fieldList.add(f);
								break;
							}
							case "laborCamp":
							{
								FieldController f = parseLaborCamp(element);
								fieldList.add(f);
								break;
							}
							case "tax":
							{
								FieldController f = parseTax(element);
								fieldList.add(f);
								break;
							}
							case "fleet":
							{
								FieldController f = parseFleet(element);
								fieldList.add(f);
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
