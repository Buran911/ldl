package application.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ����� �������� Stack Trace ������ � ��������� �������� ��� � ���� ������.
 * ���������� ��� ������������.
 * @author hindu 
 * */
public class StackTrace {
    public static String getStackTrace(Exception e) {
	StringWriter out = new StringWriter();
	e.printStackTrace(new PrintWriter(out));
	
	return out.toString();
    }
}
