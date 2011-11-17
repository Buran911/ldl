package application.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Класс фомирует Stack Trace ошибки и позволяет получить его в виде строки.
 * Необходимо для логгирования.
 * @author hindu 
 * */
public class StackTrace {
    public static String getStackTrace(Exception e) {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	e.printStackTrace(ps);
	ps.close();
	
	return baos.toString();
    }
}
