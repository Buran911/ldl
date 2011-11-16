package application;

import org.apache.log4j.Logger;

import application.util.Halt;
import application.util.StackTrace;
/**
 * 
 * */
public class Application {

    private static Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) {
	logger.trace("Запуск приложения.");
	App app = new App(args);
	try {
	    app.readFiles();
	    app.parseAndCheckErrors();
	    app.generateEQ();
	    app.makeQuery();
	    app.writeYAML();
	    logger.info("Работа приложения завершена корректно. Результаты в папке out.");
	} catch (Halt halt) {
	    logger.error("Ошибка выполнения приложения. Stack trace в логе.");
	} catch (Exception e) {
	    logger.error("Ошибка выполнения приложения. Stack trace в логе.");
	    logger.trace( StackTrace.getStackTrace(e));
	} finally{
	    logger.trace("конец лога\n\n\n\n\n\n");
	}
	
    }

}
