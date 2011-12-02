package testcases.unit.semantic;

import generation.idtable.IdTable;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.IdTableMaker;
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

public class NoDefinitionTest {
    private App app;
    private SyntaxTree tree;
    private IdTable idTable;
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
	idTable = new IdTable();
    }

    @Test
    public void srcBlockTest1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/nodef/srcBlockTest1.ldl");
	ParseError templateErr = new ParseError(ErrorType.IdentifierUndefined, null, null);
	templateErr.setErrorPos(3);

	tree.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, errh));

	errh.printErrors();
	
	Assert.assertTrue(errh.hasError(templateErr));
    }

    @Test
    public void VariableTest1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/nodef/VariableTest1.ldl");
	ParseError templateErr = new ParseError(ErrorType.IdentifierUndefined, null, null);
	templateErr.setErrorPos(2);

	tree.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, errh));

	Assert.assertTrue(errh.hasError(templateErr));
    }

    @Test
    public void OperationCallTest1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/nodef/srcBlockTestgood.ldl testdata/semantictests/nodef/OperationCallTest1.ldl testdata/semantictests/nodef/one.ldl");
	ParseError templateErr1 = new ParseError(ErrorType.IdentifierUndefined, null, null);
	ParseError templateErr2 = new ParseError(ErrorType.IdentifierUndefined, null, null);
	ParseError templateErr3 = new ParseError(ErrorType.IdentifierUndefined, null, null);
	templateErr1.setErrorPos(4);
	templateErr2.setErrorPos(8);
	templateErr3.setErrorPos(12);

	tree.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, errh));
	
	errh.printErrors();

	Assert.assertTrue(errh.hasError(templateErr1));
	Assert.assertTrue(errh.hasError(templateErr2));
	Assert.assertTrue(errh.hasError(templateErr3));
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