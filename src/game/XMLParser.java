package game;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class XMLParser {
	
	
	protected static Document getXMLDocument(String path)
	{
		Document fields;
		try{
			//Does not need to be closed, as it just represents a path to the file. 
			//the actual read/writing is done by the XMLparser. 
			File fieldFile = new File(path);
			System.out.println(fieldFile.getAbsolutePath());
			//No need to store the DocumentBuilderFactory instance as we are using default settings:
			DocumentBuilder XMLparser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			 fields = = XMLparser.parse(fieldFile);
			
		}
		catch(IOException fileEx)
		{
			JOptionPane.showMessageDialog(desktop_board.Board.getInstance().getComponent(0), "File not found at: " + fieldFile.getAbsolutePath() + "\nPlease restore the file or the board cannot be created.", "Critical error accoured", JOptionPane.ERROR_MESSAGE);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			return 	fields;
		}
	}
	/**
	 * Checks if multiple accourances exists within the Element node and prints to the console if true
	 * @param The element containing the element
	 * @param The name of the element to get
	 * @return The first occourance of the element 
	 * @throws An exception is thrown if no elements were found.  
	 */
	protected static Node getUnique(Element e, String elementNameTag) throws Exception
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
	protected static int parseInteger(Node n) throws Exception
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
	
}
