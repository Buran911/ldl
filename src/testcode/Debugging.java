package testcode;

import generation.idtable.IdTable;
import generation.templateengine.Engine;
import generation.templateengine.QueryConstraints;
import generation.templateengine.QueryData;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;

import java.io.FileNotFoundException;

import parse.errhandler.ErrorHandler;
import parse.parser.Parser;
import parse.parsetree.ParseTree;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;



public class Debugging {
	public static void main(String[] args) throws FileNotFoundException {
		Source src = new Source(
				"testdata/test.ldl");
		ErrorHandler errh = new ErrorHandler(src);
		Parser parser = new Parser(src, errh);
		parser.setDebugModeOn();
		
		parser.parse();
		
		if(errh.hasErrors()){
			errh.printErrors();
			return;
		}
		
		
		ParseTree tree = parser.getTree();
		tree.printTree();
		
		SyntaxTree syntaxTree = new SyntaxTree(tree);
		
		IdTable table = new IdTable();
		QueryConstraints qConstraints = new QueryConstraints();
		syntaxTree.accept( new IdTableMaker( new IdParsigStrategy(), table));
		syntaxTree.accept( new TemplateTypeFiller( new BottomUpWalkingStrategy()));
		syntaxTree.accept( new IdConvertor( new IdParsigStrategy(),table));
		syntaxTree.accept( new TemplateEqClassesFiller(new BottomUpWalkingStrategy(), qConstraints));
		
		qConstraints.makeUnmodifiable();
		Engine engine = new Engine(new QueryData(table), qConstraints);
		engine.generate();
		
		System.out.println(engine.getQuery().get(0));
	}

}
