package testcases.functional.syntax;


import java.io.StringReader;

import org.junit.Ignore;
import org.junit.Test;

import parse.parser.Parser;
import parse.util.Source;

public class Description {
	
	@Test
	public void testParserWorks(){
		Source src = new Source("testdata/grammartest/desc.ldl");
		Parser parser = new Parser( new StringReader(src.getProgram()), null);
		parser.setDebugModeOn();
		parser.parse();
	}

	@Test
	@Ignore
	public void testParserFails(){
		// FIXME обработчик ошибок не пашет
		Source src = new Source("testdata/grammartest/descFail.ldl");
		Parser parser = new Parser( new StringReader(src.getProgram()), null);
		parser.setDebugModeOn();
		parser.parse();
	}
}
