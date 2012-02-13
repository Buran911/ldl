package parse.ldlsettingsparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import application.util.Halt;
import application.util.StackTrace;

/**
 * Этот класс парсит настроечный файл(создается DOM) и предоставляет доступ к
 * его полям и аттрибутам.
 * 
 * @author hindu
 * */
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
	    logger.error("Серьёзная ошибка в настройках XML парсера.");
	    logger.trace(StackTrace.getStackTrace(e));
	    throw new Halt();
	}

	try {
	    doc = docBuilder.parse(settings);
	    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new StreamSource(new File("xsd/ldl-project.xsd"))).newValidator().validate(new DOMSource(doc));
	} catch (SAXException e) {
	    logger.error("SAX ошибка в XML парсере.");
	    logger.trace(StackTrace.getStackTrace(e));
	    throw new Halt();
	} catch (IOException e) {
	    System.err.println("I/O ошибка в XML парсере.");
	    e.printStackTrace();
	}

	rootElem = doc.getDocumentElement();
	rootElem.normalize();
    }

    public ArrayList<String> getFunctionGroups() {
	ArrayList<String> functionGroups = new ArrayList<String>();

	// FIXME корявенько
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
     * Функция ищет значения полей xml документа по тегу. Выбирается элемент с
     * указанным индексом.
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
