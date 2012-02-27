package testcases.functional.semantic;

import generation.idtable.IdTable;
import generation.templateengine.QueryConstraints;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.PositionEstimater;

import org.junit.Assert;
import org.junit.Test;

import parse.errhandler.ErrorHandler;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;


public class NotImplementedYetTest {
	@Test
	public void testParserWorks(){
		Source src = new Source("testdata/semantictest/unimplemented/BoolInPredicate");
		ErrorHandler errHandler = new ErrorHandler(src);
		Parser parser = new Parser( src, errHandler );
		parser.parse(); 
		

		IdTable table = new IdTable();
		SyntaxTree tree = new SyntaxTree(parser.getTree());
		
		tree.accept( new PositionEstimater( new IdParsigStrategy()));
		tree.accept( new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), errHandler));
		
		Assert.assertTrue(errHandler.hasErrors());
	}
}
