package parse.errhandler;

import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import parse.util.PairSet;
import parse.util.Source;

// TODO ������� �������� �������
/**
 * ����� �����������, �������� � ������������ ������ �������� � �������
 * ��������� ����(�������������� � �������������). ������ ������ ��������
 * ��������� ��������. ��� ������������� ������ ������������� �����������
 * ����������� ������ ����������, ���� ���������� ����������� ����� �����������
 * ������ ����������.
 * 
 * @author hindu
 * */
public class ErrorHandler {
    private LinkedList<ParseError> errors;
    private final int errCritCount; // ���������� ������, ����� ��������
				    // ���������� �������� ���������������
    private PairSet incompatibleErrors; // ����� ������������� ���� � ������
					// ������
    private Source src;
    private Logger logger = Logger.getLogger(ErrorHandler.class);

    {
	errors = new LinkedList<ParseError>();
	errCritCount = 15;
	incompatibleErrors = new PairSet();
    }

    public ErrorHandler(Source src) {
	this.src = src;
    }

    public void addError(ParseError error) {
	errors.add(error);
	handle();
    }

    public boolean hasError(ParseError error) {
	return errors.contains(error);
    }

    public boolean hasErrors() {
	return errors.size() > 0 ? true : false;
    }

    private void handle() {
	// TODO ��������� �� ��������������� ������, ���������� ���������� �
	// ������
	ParseError lastErr = errors.getLast();
	String errorLine = src.getLine(lastErr.getLineNo());
	String context = src.getContext(lastErr.getLineNo());
	// TODO ������ unit-���� ������ getContext()
	lastErr.setFileName(src.getFileName(lastErr.getLineNo()));
	lastErr.setErrorPos(src.getLinePos(lastErr.getLineNo()));
	lastErr.setContextPos(src.getContextPos(lastErr.getLineNo()));
	lastErr.setErrorLine(errorLine.trim());
	lastErr.setContext(context.trim());
    }

    public void printErrors() {
	STGroup group = new STGroupFile("generation/templates/errors.stg");
	ST st = group.getInstanceOf("errors");
	st.add("errs", errors);
	logger.error(st.render());
    }

}
