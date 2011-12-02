package testcases.unit.semantic;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import parse.errhandler.ErrorClass;
import parse.errhandler.ErrorHandler;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;
import parse.parser.Parser;
import parse.util.Source;
import application.App;

public class ErrorHandlerContext {

    private App app;
    private ErrorHandler errh;

    @Ignore
    @Test
    public void test1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	app = new App("-p testdata/semantictests/errorhandlercontext/property.xml -s testdata/semantictests/errorhandlercontext/1.ldl".split(" "));

	app.readFiles();

	errh = getHandler(app);

	Parser parser = new Parser(getSrc(app), errh);
	parser.parse();

	// Синтаксические ошибки
	// if (errh.hasErrors()) {
	// errh.printErrors();
	// throw new Halt();
	// }
    }
    @Ignore
    @Test
    public void test2() {
	ErrorHandler errh = new ErrorHandler(null);
	Assert.assertTrue(errh.hasErrorCompatibility(ErrorType.UncompatibleTypes, ErrorType.IdentifierUndefined));
	Assert.assertTrue(errh.hasErrorCompatibility(ErrorType.IdentifierRedefenition, ErrorType.NotImplementedYet));
	Assert.assertTrue(!errh.hasErrorCompatibility(ErrorType.IdentifierRedefenition, ErrorType.IdentifierUndefined));
    }

    @Test
    public void test3() {
	ErrorHandler errh = new ErrorHandler(new Source("testdata/compatibility/file.ldl"));
	ParseError idUndefined = new ParseError(ErrorType.IdentifierUndefined, null, null,ErrorType.IdentifierUndefined.getDescription());
	idUndefined.setErrorPos(4);
	idUndefined.setLineNo(4);
	ParseError idRedefined = new ParseError(ErrorType.IdentifierRedefenition, null, null,ErrorType.IdentifierRedefenition.getDescription());
	idRedefined.setErrorPos(2);
	idRedefined.setLineNo(2);
	ParseError typeMismatched = new ParseError(ErrorType.UncompatibleTypes, null, null,ErrorType.UncompatibleTypes.getDescription());
	typeMismatched.setErrorPos(4);
	typeMismatched.setLineNo(4);
	errh.addError(idUndefined);
	System.out.println("IdentifierUndefined added");
	errh.addError(idRedefined);
	System.out.println("IdentifierRedefenition added");
	errh.addError(typeMismatched);
	System.out.println("UncompatibleTypes added");
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
