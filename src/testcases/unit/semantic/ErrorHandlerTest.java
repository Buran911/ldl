package testcases.unit.semantic;

import java.lang.reflect.Field;

import org.junit.Test;

import parse.errhandler.ErrorHandler;
import parse.parser.Parser;
import parse.util.Source;
import application.App;

public class ErrorHandlerTest {

    private App app;
    private ErrorHandler errh;

    @Test
    public void test1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	// 3 ошибки : 2 совместимые и 1 не совместимая
	app = new App("-p testdata/oracle/property.xml -s testdata/compatibility/file.ldl".split(" "));

	app.readFiles();

	errh = getHandler(app);

	Parser parser = new Parser(getSrc(app), errh);
	parser.parse();
	app.parseAndCheckErrors();
    }

    @Test
    public void test2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	//	
	app = new App("-p testdata/oracle/property.xml -s testdata/compatibility/file2.ldl".split(" "));

	app.readFiles();

	errh = getHandler(app);

	Parser parser = new Parser(getSrc(app), errh);
	parser.parse();
	app.parseAndCheckErrors();
	
	
    }
    @Test
    public void test3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	//	
	app = new App("-p testdata/oracle/property.xml -s testdata/compatibility/file3.ldl".split(" "));

	app.readFiles();

	errh = getHandler(app);

	Parser parser = new Parser(getSrc(app), errh);
	parser.parse();
	app.parseAndCheckErrors();
	
	
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
