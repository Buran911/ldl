package parse.exceptions;

/**
 * Ошибка: неправильно задан идентификатор.
 * @author hindu
 * */
public class InvalidIdentifierException extends ParseException {
	public InvalidIdentifierException(String sourceFileName, int lineNo) {
		super(sourceFileName, lineNo);
	}
}
