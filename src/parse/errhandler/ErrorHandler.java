package parse.errhandler;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import parse.util.PairSet;
import parse.util.Source;

// TODO Научить работать хэндлер
/**
 * Класс накапливает, содержит и обрабатывает ошибки парсинга и анализа
 * исходного кода(синтаксические и семантические). Каждая ошибка содержит
 * подробное описание. При возникновение ошибок препятсвующих возможности
 * продолжения работы приложения, либо накоплении критической массы заверщается
 * работа приложения.
 * 
 * @author hindu
 * */
public class ErrorHandler {
    private LinkedList<ParseError> errors;
    private final int errCritCount; // количество ошибок, после которого
				    // дальнейшие проверки нецелесообразны
    private PairSet incompatibleErrors; // набор несовместимых друг с другом
					// ошибок
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
	// TODO проверить на несовместимость ошибки, превышение криткаунта и
	// прочая
	ParseError lastErr = errors.getLast();
	String errorLine = src.getLine(lastErr.getLineNo());

	lastErr.setErrorLine(errorLine);
    }

    public void printErrors() {
	// XXX распечатать в удобном виде информацию по ошибкам
	for (ParseError error : errors) {
	    logger.error("!!!Ошибка!!!\nКоординаты : строка " + error.getLineNo() + " символ " + error.getColumnNo() + " \nВыражение  : "  + error.getErrorLine().trim() + "\nОписание   : " + error.getErrType().toString() + "\nФайл       : " + src.getFileName(error.getLineNo()));
	}
    }

}
