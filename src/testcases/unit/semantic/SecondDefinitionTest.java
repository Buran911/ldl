package testcases.unit.semantic;

import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.PositionEstimater;

import org.junit.Assert;
import org.junit.Test;

import parse.errhandler.ErrorClass;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;

public class SecondDefinitionTest {

    @Test
    public void test1() {
	AppTest app = new AppTest(("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/desc.ldl").split(" "));
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 2, null);
	templateErr2.setErrorPos(2);
	app.readFiles();
	app.checkErrors();

	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdRedefinedChecker(new IdParsigStrategy(), app.getErrh()));

	app.getErrh().printErrors();

	Assert.assertTrue(app.getErrh().hasError(templateErr2));
    }

    @Test
    public void test2() {
	AppTest app = new AppTest(("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/desc1.ldl").split(" "));
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 2, null);
	ParseError templateErr4 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 4, null);
	templateErr2.setErrorPos(2);
	templateErr4.setErrorPos(4);
	app.readFiles();
	app.checkErrors();

	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdRedefinedChecker(new IdParsigStrategy(), app.getErrh()));

	app.getErrh().printErrors();

	Assert.assertTrue(app.getErrh().hasError(templateErr2));
	Assert.assertTrue(app.getErrh().hasError(templateErr4));
    }

    @Test
    public void test3() {
	AppTest app = new AppTest(("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/desc2.ldl").split(" "));
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 2, null);
	templateErr2.setErrorPos(2);
	app.readFiles();
	app.checkErrors();

	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdRedefinedChecker(new IdParsigStrategy(), app.getErrh()));

	app.getErrh().printErrors();

	Assert.assertTrue(app.getErrh().hasError(templateErr2));
    }

    @Test
    public void test4() {
	AppTest app = new AppTest(("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/desc3.ldl").split(" "));
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 2, null);
	ParseError templateErr3 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 3, null);
	templateErr2.setErrorPos(2);
	templateErr3.setErrorPos(3);
	
	app.readFiles();
	app.checkErrors();

	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdRedefinedChecker(new IdParsigStrategy(), app.getErrh()));

	Assert.assertTrue(app.getErrh().hasError(templateErr2));
	Assert.assertTrue(app.getErrh().hasError(templateErr3));
    }

    @Test
    public void predtest1() {
	AppTest app = new AppTest(("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/pred.ldl").split(" "));

	ParseError templateErr4 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 4, null);
	templateErr4.setErrorPos(4);
	
	app.readFiles();
	app.checkErrors();

	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdRedefinedChecker(new IdParsigStrategy(), app.getErrh()));

	app.getErrh().printErrors();

	Assert.assertTrue(app.getErrh().hasError(templateErr4));
    }

    @Test
    public void predtest2() {
	AppTest app = new AppTest(("-p testdata/semantictests/2nddef/property.xml " + "-s testdata/semantictests/2nddef/pred2.ldl").split(" "));

	ParseError templateErr4 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 4, null);
	ParseError templateErr8 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierRedefenition, 8, null);

	templateErr4.setErrorPos(4);
	templateErr8.setErrorPos(8);
	
	app.readFiles();
	app.checkErrors();

	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdRedefinedChecker(new IdParsigStrategy(), app.getErrh()));

	app.getErrh().printErrors();

	Assert.assertTrue(app.getErrh().hasError(templateErr4));
	Assert.assertTrue(app.getErrh().hasError(templateErr8));
    }
}
