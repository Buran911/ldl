package testcases.functional;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parse.ldlsettingsparser.XMLParser;

public class SettingsFile {
	private static XMLParser parser;
	
	@BeforeClass
	public static void setUp() {
		parser = new XMLParser("testdata/settingstest/settings.xml");
		parser.parse();
	}
	
	@Test 
	public void valuesParsing(){
		ArrayList<String> groups = new ArrayList<String>();
		groups.add("bic");
		groups.add("acc");
		groups.add("leaf");
		
		ArrayList<String> testGroups = parser.getFunctionGroups();
		
		if( !(groups.containsAll(testGroups) && testGroups.containsAll(groups))){
			Assert.assertTrue(false);
		}
		
	}
	
	@Test 
	public void attributesParsing(){
		String conStr = "jdbc:sqlite:TestData\\new.db";
		String priority = "4";
		String priorityType = "upper";
		
		Assert.assertEquals(conStr, parser.getConnectionString());
		Assert.assertEquals(priority, parser.getPriority());
		Assert.assertEquals(priorityType, parser.getPriorityType());
	}
}
