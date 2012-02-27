package testcases.unit.semantic;

import generation.idtable.IdTable;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.PositionEstimater;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import parse.errhandler.ErrorClass;
import parse.errhandler.ErrorHandler;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;
import application.App;
import application.util.Halt;

public class SecondDefinitionTest {
    private App app;
    private SyntaxTree tree;
    private ErrorHandler errh;
    
    public void setUp(String str) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	app = new App(str.split(" "));

	app.readFiles();

	errh = getHandler(app);

	Parser parser = new Parser(getSrc(app), errh);
	parser.parse();

	// Синтаксические ошибки
	if (errh.hasErrors()) {
	    errh.printErrors();
	    throw new Halt();
	}

	tree = new SyntaxTree(parser.getTree());
    }
    
    @Test
    public void test1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/desc.ldl");
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);
	templateErr2.setErrorPos(2);

	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));

	Assert.assertTrue(errh.hasError(templateErr2));
    }

    @Test
    public void test2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/desc1.ldl");
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);
	ParseError templateErr4 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);
	templateErr2.setErrorPos(2);
	templateErr4.setErrorPos(4);

	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));

	
	
	Assert.assertTrue(errh.hasError(templateErr2));
	Assert.assertTrue(errh.hasError(templateErr4));
    }

    @Test
    public void test3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/desc2.ldl");
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);
	templateErr2.setErrorPos(2);
	
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));

	errh.printErrors();

	Assert.assertTrue(errh.hasError(templateErr2));
    }

    @Test
    public void test4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/desc3.ldl");
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);
	ParseError templateErr3 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);
	templateErr2.setErrorPos(2);
	templateErr3.setErrorPos(3);
	
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));

	Assert.assertTrue(errh.hasError(templateErr2));
	Assert.assertTrue(errh.hasError(templateErr3));
    }

    @Test
    public void predtest1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/pred.ldl");

	ParseError templateErr4 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);
	templateErr4.setErrorPos(4);
	
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));

	errh.printErrors();

	Assert.assertTrue(errh.hasError(templateErr4));
    }

    @Test
    public void predtest2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/pred2.ldl");

	ParseError templateErr4 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);
	ParseError templateErr8 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, null, null);

	templateErr4.setErrorPos(4);
	templateErr8.setErrorPos(8);

	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));

	errh.printErrors();

	Assert.assertTrue(errh.hasError(templateErr4));
	Assert.assertTrue(errh.hasError(templateErr8));
    }
    
    private ErrorHandler getHandler(App app) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	Field errorHandler = App.class.getDeclaredField("errh");

	errorHandler.setAccessible(true);

	return (ErrorHandler) errorHandler.get(app);
    }

    private Source getSrc(App app) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	Field src = App.class.getDeclaredField("src");

	src.setAccessible(true);

	return (Source) src.get(app);
    }
    
}
