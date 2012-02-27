package testcases.functional.syntax;

import generation.idtable.IdTable;
import generation.templateengine.QueryConstraints;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;

import org.junit.Assert;
import org.junit.Test;

import parse.errhandler.ErrorHandler;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
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
