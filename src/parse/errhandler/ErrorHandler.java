package parse.errhandler;

import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import parse.util.PairSet;
import parse.util.Source;

// TODO ������� �������� �������

// TODO ��������� �� ��������������� ������, ���������� ���������� �
// ������

/**
 * ����� �����������, �������� � ������������ ������ �������� � �������
 * ��������� ����(�������������� � �������������). ������ ������ ��������
 * ��������� ��������. ��� ������������� ������ ������������� �����������
 * ����������� ������ ����������, ���� ���������� ����������� ����� �����������
 * ������ ����������.
 * 
 * @author hindu
 * */
public final class ErrorHandler {
    private LinkedList<Error> errors;
    private final int errCritCount; // ���������� ������, ����� ��������
				    // ���������� �������� ���������������
    private PairSet incompatibleErrors; // ����� ������������� ���� � ������
					// ������
    private Source src;
    private Logger logger = Logger.getLogger(ErrorHandler.class);

    {
	errors = new LinkedList<Error>();
	errCritCount = 15;
	incompatibleErrors = new PairSet();
    }

    public ErrorHandler(Source src) {
	this.src = src;
    }

    public void addError(Error error) {
	if (error instanceof ParseError) {
	    setInfoAboutError((ParseError) error);
	    error.setParseError();
	}else{
	    error.setRuntimeError();
	}
	errors.add(error);
	handle();
    }
    
    private void handle(){
	
    }

    public boolean hasError(Error error) {
	return errors.contains(error);
    }

    public boolean hasErrors() {
	return errors.size() > 0 ? true : false;
    }

    private void setInfoAboutError(ParseError error) {
	String errorLine = src.getLine(error.getLineNo());
	String context = src.getLineContext(error.getLineNo());

	error.setFileName(src.getFileName(error.getLineNo()));
	error.setErrorPos(src.getLineNo(error.getLineNo()));
	error.setContextPos(src.getLineContextNo(error.getLineNo()));
	error.setErrorLine(errorLine.trim());
	error.setContext(context.trim());
    }
}
