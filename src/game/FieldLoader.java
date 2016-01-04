package game;
import slots.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import slots.Field.Types;

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
	
	private static Territory parseTerritory(Element e) throws Exception
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
			return new Territory(translateID, Types.TERRITORY,price, rent);
		}
		catch(Exception exc)
		{
			throw new Exception("Failed to parse Territory", exc);
		}
		
	}
	private static Refuge parseRefuge(Element e) throws Exception
	{
		System.out.println("Parsing refuge...");
		try {
			
			Node translateNode = getUnique(e, "translateID");
			Node bonusNode = getUnique(e, "bonus");
			int translateID = parseInteger(translateNode);
			int bonus = parseInteger(bonusNode);
			return new Refuge(translateID, Types.REFUGE, bonus);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Refuge", exc);
		
		}
		
	}
	private static LaborCamp parseLaborCamp(Element e) throws Exception
	{
		System.out.println("Parsing labotCamp...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node rentNode = getUnique(e, "rent");
			Node priceNode = getUnique(e, "price");
			int translateID = parseInteger(translateNode);
			int rent = parseInteger(rentNode);
			int price = parseInteger(priceNode);
			return new LaborCamp(translateID, Types.REFUGE, price, rent);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse LaborCamp", exc);
		
		}
	}
	private static Tax parseTax(Element e) throws Exception
	{
		System.out.println("Parsing tax...");
		try {
			Node translateNode = getUnique(e, "translateID");
			Node taxNode = getUnique(e, "tax");
			int translateID = parseInteger(translateNode);
			int tax = parseInteger(taxNode);
			return new Tax(translateID, Types.TAX, tax);
			
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
			
			return new Fleet(translateID, Types.REFUGE, price);
			
		} catch (Exception exc) {
			
			throw new Exception("Failed to parse Tax", exc);
		
		}
	}
	static public Field[] parseFields(String path)
	{
		try
		{
			//Does not need to be closed, as it just represents a path to the file. 
			//the actual read/writing is done by the XMLparser. 
			File fieldFile = new File(path);
			System.out.println(fieldFile.getAbsolutePath());
			//No need to store the DocumentBuilderFactory instance as we are using default settings:
			DocumentBuilder XMLparser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document fields = XMLparser.parse(fieldFile);
			
			/***
			 * Parses over the fields in the XML document, seperated by types. 
			 */
			NodeList fieldNodes = fields.getElementsByTagName("field");
			List<Field> fieldList = new ArrayList<Field>();
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
							Field f = parseTerritory(element);
							fieldList.add(f);
							break;
						}
						case "refuge":
						{
							Field f = parseRefuge(element);
							fieldList.add(f);
							break;
						}
						case "laborCamp":
						{
							Field f = parseLaborCamp(element);
							fieldList.add(f);
							break;
						}
						case "tax":
						{
							Field f = parseTax(element);
							fieldList.add(f);
							break;
						}
						case "fleet":
						{
							Field f = parseFleet(element);
							fieldList.add(f);
							break;
							
						}
					}
				}
			}
			Field[] retFields = new Field[fieldList.size()];
			retFields = fieldList.toArray(retFields);
			return retFields;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		 
		
		
		
	}
	public static void main(String[] args) {
		Field[] f = FieldLoader.parseFields("Fields.xml");
		int i=1;
		for(Field fi : f)
		{
			System.out.println(fi.getName()+ " " + i++);
			System.out.println(fi.getDescription());
		}
		
	}
	
}
