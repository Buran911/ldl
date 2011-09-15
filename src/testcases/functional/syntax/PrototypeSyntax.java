package testcases.functional.syntax;

import java.io.StringReader;

import org.junit.Test;

import parse.parser.Parser;
import parse.util.Source;


public class PrototypeSyntax {
	@Test
	public void testParserWorks(){
		Source src = new Source("testdata/grammartest/prototypeSyntax.ldl");
		Parser parser = new Parser( new StringReader(src.getProgram()), null);
		parser.setDebugModeOn();
		parser.parse(); 
	}
}
