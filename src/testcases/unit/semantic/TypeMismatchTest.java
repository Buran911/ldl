package testcases.unit.semantic;

import generation.idtable.IdTable;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TypeMismatchChecker;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import parse.errhandler.ErrorClass;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;

public class TypeMismatchTest {

    @Test
    public void test1() {
	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEvar.ldl".split(" "));
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 1, null);
	app.readFiles();
	app.checkErrors();

	IdTable idTable = new IdTable();
	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdConvertor(new IdParsigStrategy(), idTable));
	app.getTree().accept(new TypeMismatchChecker(new IdParsigStrategy(), app.getErrh()));

	Assert.assertTrue(app.getErrh().hasError(templateErr));
    }

    @Test
    public void test2() {
	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEvar2.ldl".split(" "));
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 6, null);
	app.readFiles();
	app.checkErrors();

	IdTable idTable = new IdTable();
	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdConvertor(new IdParsigStrategy(), idTable));
	app.getTree().accept(new TypeMismatchChecker(new IdParsigStrategy(), app.getErrh()));

	Assert.assertTrue(app.getErrh().hasError(templateErr));
    }

    @Test
    public void test3() {
	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEvarDvar.ldl".split(" "));
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 6, null);
	app.readFiles();
	app.checkErrors();

	IdTable idTable = new IdTable();
	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdConvertor(new IdParsigStrategy(), idTable));
	app.getTree().accept(new TypeMismatchChecker(new IdParsigStrategy(), app.getErrh()));

	Assert.assertTrue(app.getErrh().hasError(templateErr));
    }

    @Test
    @Ignore
    public void test4() {
	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEpred.ldl".split(" "));
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 4, null);
	app.readFiles();
	app.checkErrors();

	IdTable idTable = new IdTable();
	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdConvertor(new IdParsigStrategy(), idTable));
	app.getTree().accept(new TypeMismatchChecker(new IdParsigStrategy(), app.getErrh()));

	Assert.assertTrue(app.getErrh().hasError(templateErr));
    }
}
