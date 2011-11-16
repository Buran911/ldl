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

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import application.util.Halt;
import application.util.StackTrace;

public class XMLParser {
    private String pathToFile;
    private Document doc = null;
    private Element rootElem = null;
    private Logger logger = Logger.getLogger(XMLParser.class);

    public XMLParser(String pathToFile) {
	this.pathToFile = pathToFile;
    }

    public void parse() {
	File settings = new File(pathToFile);
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = null;

	try {
	    docBuilder = factory.newDocumentBuilder();
	} catch (ParserConfigurationException e) {
	    logger.error("��������� ������ � ���������� XML �������.");
	    logger.trace( StackTrace.getStackTrace(e));
	    throw new Halt();
	}

	try {
	    doc = docBuilder.parse(settings);
	} catch (SAXException e) {
	    logger.error("SAX ������ � XML �������.");
	    logger.trace( StackTrace.getStackTrace(e));
	    throw new Halt();
	} catch (IOException e) {
	    System.err.println("I/O ������ � XML �������.");
	    e.printStackTrace();
	}

	rootElem = doc.getDocumentElement();
	rootElem.normalize();
    }

    public ArrayList<String> getFunctionGroups() {
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

    public String getPriority() {

	return getAttribute(rootElem, "priority", "volume");
    }

    public String getPriorityType() {

	return getAttribute(rootElem, "priority", "type");
    }

    public String getPolicy() {
	return getAttribute(rootElem, "db", "returnPolicy");
    }

    public String getUser() {
	return getAttribute(rootElem, "db", "user");
    }

    public String getPassword() {
	return getAttribute(rootElem, "db", "password");
    }

    private String getTextValue(Element elem, String tag) {

	return getValue(elem, tag, 0);
    }

    /**
     * ������� ���� �������� ����� xml ��������� �� ����. ���������� ������� �
     * ��������� ��������.
     * */
    private String getValue(Element elem, String tag, int index) {
	String textVal = null;

	NodeList nodeList = elem.getElementsByTagName(tag);

	if (nodeList != null) {
	    Element el = (Element) nodeList.item(index);
	    textVal = el.getFirstChild().getNodeValue();
	}

	return textVal;
    }

    private String getAttribute(Element elem, String tag, String type) {
	String attr = null;

	NodeList nodeList = elem.getElementsByTagName(tag);

	if (nodeList != null) {
	    Element el = (Element) nodeList.item(0);
	    attr = el.getAttribute(type);
	}

	return attr;
    }

}
