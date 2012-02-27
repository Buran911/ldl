package application.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Класс фомирует Stack Trace ошибки и позволяет получить его в виде строки.
 * Необходимо для логгирования.
 * @author hindu 
 * */
public class StackTrace {
    public static String getStackTrace(Exception e) {
	StringWriter out = new StringWriter();
	e.printStackTrace(new PrintWriter(out));
	
	return out.toString();
    }
}
