package testcases.unit.semantic;

import generation.idtable.IdTable;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TypeMismatch;

import org.junit.Assert;
import org.junit.Test;

import parse.errhandler.ErrorClass;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;

public class TypeMismatchTest {

//    @Test
//    public void test1() {
//
//	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEvar.ldl".split(" "));
//	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 4, null);
//	app.readFiles();
//	app.checkErrors();
//
//	IdTable idTable = new IdTable();
//	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
//	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
//	app.getTree().accept(new IdConvertor(new IdParsigStrategy(), idTable));
//	app.getTree().accept(new TypeMismatch(new IdParsigStrategy(), idTable, app.getErrh()));
//
//	Assert.assertTrue(app.getErrh().hasError(templateErr));
//
//    }

    // @Test
    // public void test2() {
    //
    // AppTest app = new
    // AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEconst.ldl".split(" "));
    // ParseError templateErr = new ParseError(ErrorClass.semantic,
    // ErrorType.UncompatibleTypes, 6, null);
    // app.readFiles();
    // app.checkErrors();
    //
    // IdTable idTable = new IdTable();
    // app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
    // app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
    // app.getTree().accept(new IdConvertor(new IdParsigStrategy(), idTable));
    // app.getTree().accept(new TypeMismatch(new IdParsigStrategy(), idTable,
    // app.getErrh()));
    //
    // Assert.assertTrue(app.getErrh().hasError(templateErr));
    //
    // }

//    @Test
//    public void test1() {
//
//	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEvarDvar.ldl".split(" "));
//	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 3, null);
//	app.readFiles();
//	app.checkErrors();
//
//	IdTable idTable = new IdTable();
//	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
//	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
//	app.getTree().accept(new IdConvertor(new IdParsigStrategy(), idTable));
//	app.getTree().accept(new TypeMismatch(new IdParsigStrategy(), idTable, app.getErrh()));
//
//	Assert.assertTrue(app.getErrh().hasError(templateErr));
//
//    }
    
    @Test
    public void test1() {

	AppTest app = new AppTest("-p testdata/semantictests/2nddef/property.xml -s testdata/semantictests/tmismatch/testeq_varEpred.ldl".split(" "));
	ParseError templateErr = new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, 4, null);
	app.readFiles();
	app.checkErrors();

	IdTable idTable = new IdTable();
	app.getTree().accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	app.getTree().accept(new PositionEstimater(new IdParsigStrategy()));
	app.getTree().accept(new IdConvertor(new IdParsigStrategy(), idTable));
	app.getTree().accept(new TypeMismatch(new IdParsigStrategy(), idTable, app.getErrh()));

	Assert.assertTrue(app.getErrh().hasError(templateErr));

    }

}
