/**
 * ���� ����� ������ ����������� ����(��������� DOM) �
 * ������������� ������ � ��� ����� � ����������.
 * */
package parse.ldlsettingsparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	private String pathToFile;
	private Document doc = null;
	private Element rootElem = null;
	
	public XMLParser(String pathToFile){
		this.pathToFile = pathToFile;
	}
	
	public void parse() {
		File settings = new File(pathToFile);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		
		
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("������ � ������� ������������ �����");
			e.printStackTrace();
		}
		
		try {
			doc = docBuilder.parse(settings);
		} catch (SAXException e) {
			System.err.println("������ � ������� ������������ �����");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("������ � ������� ������������ �����");
			e.printStackTrace();
		}
		
		rootElem = doc.getDocumentElement();
		rootElem.normalize();
	}
	
	public ArrayList<String> getFunctionGroups(){
		ArrayList<String> functionGroups = new ArrayList<String>();
		
		// FIXME ����������
		String tag = "group";
		int groupsCount = doc.getElementsByTagName(tag).getLength();
		
		for (int i = 0; i < groupsCount; i++) {
			functionGroups.add(getValue(rootElem, tag, i));
		}
		
		return functionGroups;
	}
	
	public String getConnectionString() {
		return getAttribute(rootElem, "db", "connectionString");
	}
	
	public String getPriority(){
		
		return getAttribute(rootElem, "priority", "volume");
	}
	
	public String getPriorityType(){
		
		return getAttribute(rootElem, "priority", "type");
	}
	
	public String getPolicy(){
		return getAttribute(rootElem, "db", "returnPolicy");
	}
	
	public String getUser(){
		return getAttribute(rootElem, "db", "user");
	}
	
	public String getPassword(){
		return getAttribute(rootElem, "db", "password");
	}
	
	private String getTextValue(Element elem, String tag){
		
		return getValue(elem, tag, 0);
	}
	
	/** 
	 * ������� ���� �������� ����� xml ��������� �� ����. ���������� ������� � ��������� ��������.
	 * */
	private String getValue(Element elem, String tag, int index){
		String textVal = null;
		
		NodeList nodeList = elem.getElementsByTagName(tag);
		
		if(nodeList != null){
			Element el = (Element)nodeList.item(index);
			textVal = el.getFirstChild().getNodeValue();
		}
		
		return textVal;
	}
	
	private String getAttribute(Element elem, String tag, String type){
		String attr = null;
		
		NodeList nodeList = elem.getElementsByTagName(tag);
		
		if(nodeList != null){
			Element el = (Element)nodeList.item(0);
			attr = el.getAttribute(type);
		}
		
		return attr;
	}
	
}
