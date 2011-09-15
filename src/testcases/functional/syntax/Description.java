package testcases.functional.syntax;


import java.io.StringReader;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import parse.errhandler.ErrorHandler;
import parse.parser.Parser;
import parse.util.Source;

public class Description {
	
	@Test
	public void testParserWorks(){
		Source src = new Source("testdata/grammartest/desc.ldl");
		ErrorHandler errHandler = new ErrorHandler(src);
		Parser parser = new Parser( src, errHandler );
//		parser.setDebugModeOn();
		parser.parse();
		
		Assert.assertFalse(errHandler.hasErrors());
	}

	
	@Test
	public void testParserFails(){
		Source src = new Source("testdata/grammartest/descFail.ldl");
		ErrorHandler errHandler = new ErrorHandler(src);
		Parser parser = new Parser( src, errHandler );
//		parser.setDebugModeOn();
		parser.parse();
		
		Assert.assertTrue(errHandler.hasErrors());
	}
}
