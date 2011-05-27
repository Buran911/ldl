package parse.exceptions;

/**
 * ������: ����������� �����.
 * @author hindu
 * */
public class UnknownTokenException extends ParseException {
	public UnknownTokenException(String sourceFileName, int lineNo) {
		super(sourceFileName, lineNo);
	}
}
