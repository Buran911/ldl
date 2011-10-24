package testcases.functional;

import org.junit.Test;

import parse.errhandler.ErrorHandler;
import parse.parser.Parser;
import parse.util.Source;


public class ParseTree {
	@Test
	public void testTree() {
		Source src = new Source(
				"testdata/treetest/parsetree.ldl");
		ErrorHandler errh = new ErrorHandler(src);
		Parser parser = new Parser(src, errh);
//		parser.setDebugModeOn();
		
		parser.parse();
	}
}
