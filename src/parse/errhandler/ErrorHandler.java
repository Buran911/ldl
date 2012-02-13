package parse.errhandler;

import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import parse.util.Source;

// TODO Ќаучить работать хэндлер
/**
 *  ласс накапливает, содержит и обрабатывает ошибки парсинга и анализа
 * исходного кода(синтаксические и семантические).  ажда€ ошибка содержит
 * подробное описание. ѕри возникновение ошибок преп€тсвующих возможности
 * продолжени€ работы приложени€, либо накоплении критической массы заверщаетс€
 * работа приложени€.
 * 
 * @author hindu
 * */
public class ErrorHandler {
    private LinkedList<ParseError> errors;
    private Source src;
    private Logger logger = Logger.getLogger(ErrorHandler.class);

    {
	errors = new LinkedList<ParseError>();
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
	// проча€
	ParseError lastErr = errors.getLast();
	String errorLine = src.getLine(lastErr.getLineNo());
	String context = src.getLineContext(lastErr.getLineNo());
	// TODO делать unit-тест метода getContext()
	lastErr.setFileName(src.getFileName(lastErr.getLineNo()));
	lastErr.setErrorPos(src.getLineNo(lastErr.getLineNo()));
	lastErr.setContextPos(src.getLineContextNo(lastErr.getLineNo()));
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
