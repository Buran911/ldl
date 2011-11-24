package testcases.unit.semantic;

import java.lang.reflect.Field;

import org.junit.Test;

import parse.errhandler.ErrorHandler;
import parse.parser.Parser;
import parse.util.Source;
import application.App;
import application.util.Halt;

public class ErrorHandlerContext {
    
    private App app;
    private ErrorHandler errh;

    public void setUp(String str) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
  	
      }
    
    @Test
    public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	app = new App("-p testdata/semantictests/errorhandlercontext/property.xml -s testdata/semantictests/errorhandlercontext/1.ldl".split(" "));

  	app.readFiles();

  	errh = getHandler(app);

  	Parser parser = new Parser(getSrc(app), errh);
  	parser.parse();

  	// Синтаксические ошибки
  	if (errh.hasErrors()) {
  	    errh.printErrors();
  	    throw new Halt();
  	}
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
