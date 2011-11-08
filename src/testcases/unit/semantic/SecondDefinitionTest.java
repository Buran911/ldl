package testcases.unit.semantic;

import generation.idtable.IdTable;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.SecondDefinition;

import org.junit.Assert;
import org.junit.Test;

import parse.errhandler.ErrorClass;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;

public class SecondDefinitionTest {

	@Test
	public void test1() {
		AppTest app = new AppTest(
				"-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/2nddef/desc.ldl"
						.split(" "));
		ParseError templateErr2 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 2, null);
		app.readFiles();
		app.checkErrors();

		IdTable idTable = new IdTable();
		app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
		app.getTree().accept(
				new SecondDefinition(new IdParsigStrategy(), idTable, app
						.getErrh()));
		app.getErrh().printErrors();
		Assert.assertTrue(app.getErrh().hasError(templateErr2));
	}

	@Test
	public void test2() {
		AppTest app = new AppTest(
				"-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/2nddef/desc1.ldl"
						.split(" "));
		ParseError templateErr2 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 2, null);
		ParseError templateErr4 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 4, null);
		app.readFiles();
		app.checkErrors();

		IdTable idTable = new IdTable();
		app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
		app.getTree().accept(
				new SecondDefinition(new IdParsigStrategy(), idTable, app
						.getErrh()));
		app.getErrh().printErrors();
		Assert.assertTrue(app.getErrh().hasError(templateErr2));
		Assert.assertTrue(app.getErrh().hasError(templateErr4));
	}

	@Test
	public void test3() {
		AppTest app = new AppTest(
				"-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/2nddef/desc2.ldl"
						.split(" "));
		ParseError templateErr2 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 2, null);
		app.readFiles();
		app.checkErrors();

		IdTable idTable = new IdTable();
		app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
		app.getTree().accept(
				new SecondDefinition(new IdParsigStrategy(), idTable, app
						.getErrh()));
		app.getErrh().printErrors();
		Assert.assertTrue(app.getErrh().hasError(templateErr2));
	}

	@Test
	public void test4() {
		AppTest app = new AppTest(
				"-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/2nddef/desc3.ldl"
						.split(" "));
		ParseError templateErr2 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 2, null);
		ParseError templateErr3 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 3, null);
		
		
		app.readFiles();
		app.checkErrors();

		IdTable idTable = new IdTable();
		app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
		app.getTree().accept(
				new SecondDefinition(new IdParsigStrategy(), idTable, app
						.getErrh()));
		app.getErrh().printErrors();
		
		Assert.assertTrue(app.getErrh().hasError(templateErr2));
		Assert.assertTrue(app.getErrh().hasError(templateErr3));
	}
	
	@Test
	public void predtest1() {
		AppTest app = new AppTest(
				"-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/2nddef/pred.ldl"
						.split(" "));

		ParseError templateErr4 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 4, null);
		
		app.readFiles();
		app.checkErrors();

		IdTable idTable = new IdTable();
		app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
		app.getTree().accept(
				new SecondDefinition(new IdParsigStrategy(), idTable, app.getErrh()));
		app.getErrh().printErrors();
		System.out.println();
		Assert.assertTrue(app.getErrh().hasError(templateErr4));
	}
	@Test
	public void predtest2() {
		AppTest app = new AppTest(
				"-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/2nddef/pred2.ldl"
						.split(" "));

		ParseError templateErr4 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 4, null);
		ParseError templateErr8 = new ParseError(ErrorClass.semantic,
				ErrorType.IdentifierRedefenition, 8, null);
		
		app.readFiles();
		app.checkErrors();

		IdTable idTable = new IdTable();
		app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
		app.getTree().accept(
				new SecondDefinition(new IdParsigStrategy(), idTable, app.getErrh()));
		app.getErrh().printErrors();
		System.out.println();
		Assert.assertTrue(app.getErrh().hasError(templateErr4));
		Assert.assertTrue(app.getErrh().hasError(templateErr8));
	}
}
