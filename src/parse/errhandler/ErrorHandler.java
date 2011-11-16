package parse.errhandler;

import java.util.LinkedList;
import java.util.Queue;

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

	lastErr.setErrorLine(errorLine);
    }

    public void printErrors() {
	// XXX ����������� � ������� ���� ���������� �� �������
	for (ParseError error : errors) {
//	    System.err.println(error.getErrType() + " error in line number " + error.getLineNo() + " .Possible reason = " + error.getPossibleReason());
	}
    }

}
