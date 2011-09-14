package testcases.functional.syntax;

import java.io.StringReader;

import org.junit.Test;

import parse.parser.Parser;
import parse.util.Source;


public class Predicates {
	@Test
	public void testParserWorks(){
		Source src = new Source("testdata/grammartest/predicateDesc.ldl");
		Parser parser = new Parser( new StringReader(src.getProgram()), null);
		parser.setDebugModeOn();
		parser.parse(); 
	}
}
