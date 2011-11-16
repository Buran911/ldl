package testcases.unit.semantic;

import generation.idtable.IdTable;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.PositionEstimater;

import org.junit.Assert;
import org.junit.Test;

import parse.errhandler.ErrorClass;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;

public class NoDefinitionTest {

    @Test
    public void srcBlockTest1() {
	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/nodef/srcBlockTest1.ldl".split(" "));
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, 3, null);
	app.readFiles();
	app.checkErrors();

	IdTable idTable = new IdTable();
	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, app.getErrh()));

	Assert.assertTrue(app.getErrh().hasError(templateErr));
    }

    @Test
    public void VariableTest1() {
	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/nodef/VariableTest1.ldl".split(" "));
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, 2, null);
	app.readFiles();
	app.checkErrors();

	IdTable idTable = new IdTable();
	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, app.getErrh()));

	app.getErrh().printErrors();
	
	Assert.assertTrue(app.getErrh().hasError(templateErr));
    }

    @Test
    public void OperationCallTest1() {
	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/nodef/OperationCallTest1.ldl".split(" "));
	ParseError templateErr1 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, 4, null);
	ParseError templateErr2 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, 8, null);
	ParseError templateErr3 = new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, 12, null);
	app.readFiles();
	app.checkErrors();

	IdTable idTable = new IdTable();
	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, app.getErrh()));

	Assert.assertTrue(app.getErrh().hasError(templateErr1));
	Assert.assertTrue(app.getErrh().hasError(templateErr2));
	Assert.assertTrue(app.getErrh().hasError(templateErr3));
    }

}