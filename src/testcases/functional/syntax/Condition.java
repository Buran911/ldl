package testcases.functional.syntax;

import org.junit.Assert;
import org.junit.Test;

import parse.errhandler.ErrorHandler;
import parse.parser.Parser;
import parse.util.Source;


public class Condition {
	@Test
	public void testParserWorks(){
		Source src = new Source("testdata/grammartest/condition.ldl");
		ErrorHandler errHandler = new ErrorHandler(src);
		Parser parser = new Parser( src, errHandler );
//		parser.setDebugModeOn();
		parser.parse(); 
		
		Assert.assertFalse(errHandler.hasErrors());
	}
}
