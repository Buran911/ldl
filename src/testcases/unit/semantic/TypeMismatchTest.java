package testcases.unit.semantic;

import java.lang.reflect.Field;

import generation.idtable.IdTable;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TypeMismatchChecker;

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

public class TypeMismatchTest {

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
    public void test1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEvar.ldl");
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 1, null);
	templateErr.setErrorPos(1);

	tree.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdConvertor(new IdParsigStrategy(), idTable));
	tree.accept(new TypeMismatchChecker(new IdParsigStrategy(), errh));

	Assert.assertTrue(errh.hasError(templateErr));
    }

    @Test
    public void test2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEvar2.ldl");
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 6, null);
	templateErr.setErrorPos(6);

	tree.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdConvertor(new IdParsigStrategy(), idTable));
	tree.accept(new TypeMismatchChecker(new IdParsigStrategy(), errh));

	Assert.assertTrue(errh.hasError(templateErr));
    }

    @Test
    public void test3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEvarDvar.ldl");
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 6, null);
	templateErr.setErrorPos(6);

	tree.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdConvertor(new IdParsigStrategy(), idTable));
	tree.accept(new TypeMismatchChecker(new IdParsigStrategy(), errh));

	Assert.assertTrue(errh.hasError(templateErr));
    }

    @Test
    public void test4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	setUp("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEpred.ldl");
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, null, null);
	templateErr.setErrorPos(4);

	tree.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdConvertor(new IdParsigStrategy(), idTable));
	tree.accept(new TypeMismatchChecker(new IdParsigStrategy(), errh));

	Assert.assertTrue(errh.hasError(templateErr));
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
