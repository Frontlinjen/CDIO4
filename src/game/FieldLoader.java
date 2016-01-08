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


public class FieldLoader {
	/**
	 * Checks if multiple accourances exists within the Element node and prints to the console if true
	 * @param The element containing the element
	 * @param The name of the element to get
	 * @return The first occourance of the element 
	 * @throws An exception is thrown if no elements were found.  
	 */
	private static Node getUnique(Element e, String elementNameTag) throws Exception
	{
		NodeList element = e.getElementsByTagName(elementNameTag);
		if(element.getLength()>1)
		{
			System.out.println("Warning! " + e.getNodeName() + " had multiple of " + elementNameTag + ". Going with the first found...");
		}
		else if(element.getLength()<1)
		{
			throw new Exception("Failed to locate " + elementNameTag + " for " + e.getNodeName());
		}
		else if(element.item(0).getTextContent().length()==0)
		{
			throw new Exception("Failed to load " + elementNameTag + " because it was left empty!");
		}
		return element.item(0);
	}
	private static int parseInteger(Node n) throws Exception
	{
		String content = n.getTextContent();
		System.out.println("Got: "+ content+ " from: " + n.getNodeName());
		try
		{
			int translateId = Integer.parseInt(content); 
			return translateId;
		}
		catch(NumberFormatException exc)
		{
			throw new Exception("ERROR: Failed to parse: " + content + " integer from " + n.getNodeName(), exc);
		}
	}
	
	private static TerritoryData parseTerritory(Element e) throws Exception
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
			return new TerritoryData(translateID,price, rent);
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse Territory", exc);
		}
		
	}
	private static ParkinglotData parseRefuge(Element e) throws Exception
	{
		System.out.println("Parsing refuge...");
		try {
			
			Node translateNode = getUnique(e, "translateID");
			Node bonusNode = getUnique(e, "bonus");
			int translateID = parseInteger(translateNode);
			int bonus = parseInteger(bonusNode);
			return new ParkinglotData(translateID, bonus);
			
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
			return new BreweryController(translateID, price, rent);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse LaborCamp", exc);
		
		}
	}
	private static TaxData parseTax(Element e) throws Exception
	{
		System.out.println("Parsing tax...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node taxNode = getUnique(e, "tax");
			Node taxPercentageNode = getUnique(e, "taxPercentage");
			int translateID = parseInteger(translateNode);
			int tax = parseInteger(taxNode);
			int taxPercentage = parseInteger(taxPercentageNode); 
			return new TaxData(translateID, tax, taxPercentage);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Tax", exc);
		
		}
	}
	private static Fleet parseFleet(Element e) throws Exception
	{
		System.out.println("Parsing fleet...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node priceNode = getUnique(e, "price");
			
			int translateID = parseInteger(translateNode);
			int price = parseInteger(priceNode);
			
			return new Fleet(translateID, price);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Tax", exc);
		
		}
	}
	static public FieldController[] parseFields(String path)
	{
		File fieldFile;
		try{
			//Does not need to be closed, as it just represents a path to the file. 
			//the actual read/writing is done by the XMLparser. 
			fieldFile = new File(path);
			try
			{
				
				System.out.println(fieldFile.getAbsolutePath());
				//No need to store the DocumentBuilderFactory instance as we are using default settings:
				DocumentBuilder XMLparser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document fields = XMLparser.parse(fieldFile);
				
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
						}
					}
				}
				FieldController[] retFields = new FieldController[fieldList.size()];
				retFields = fieldList.toArray(retFields);
				return retFields;
			}
			catch(IOException fileEx)
			{
				JOptionPane.showMessageDialog(desktop_board.Board.getInstance().getComponent(0), "File not found at: " + fieldFile.getAbsolutePath() + "\nPlease restore the file or the board cannot be created.", "Critical error accoured", JOptionPane.ERROR_MESSAGE);
				return null;	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	public static void main(String[] args) {
		FieldController[] f = FieldLoader.parseFields("Fields.xml");
		int i=1;
		for(FieldController fi : f)
		{
			System.out.println(fi.getName()+ " " + i++);
			System.out.println(fi.getDescription());
		}
		
	}
	
}
