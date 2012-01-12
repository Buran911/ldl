package application;

import org.apache.log4j.Logger;

import application.util.Halt;
import application.util.StackTrace;
/**
 * �������, �����������, ����� ����������. ��������� ���������� � ����������� RTE.
 * @author hindu
 * */
public class Application {

    private static Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) {
	logger.info("������ ����������.");
	App app = new App(args);
	try {
	    app.readFiles();
	    app.parseAndCheckErrors();
	    app.generateEQ();
//	    app.makeQuery();
//	    app.writeYAML();
	    logger.info("������ ���������� ��������� ���������. ���������� � ����� out.");
	} catch (Halt halt) {
	    logger.error("������ ���������� ����������. Stack trace � ����.");
	} catch (Exception e) {
	    logger.error("������ ���������� ����������. Stack trace � ����.");
	    logger.trace( StackTrace.getStackTrace(e));
	} finally{
	    logger.trace("����� ����\n\n\n#\n#\n#\n#\n#\n\n".replaceAll("#", 
		    "####################################################################################################"));
	}
	
    }

}
