package parse.errhandler;

import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import parse.util.PairSet;
import parse.util.Source;

// TODO Ќаучить работать хэндлер

// TODO проверить на несовместимость ошибки, превышение криткаунта и
// проча€

/**
 *  ласс накапливает, содержит и обрабатывает ошибки парсинга и анализа
 * исходного кода(синтаксические и семантические).  ажда€ ошибка содержит
 * подробное описание. ѕри возникновение ошибок преп€тсвующих возможности
 * продолжени€ работы приложени€, либо накоплении критической массы заверщаетс€
 * работа приложени€.
 * 
 * @author hindu
 * */
public final class ErrorHandler {
    private LinkedList<Error> errors;
    private final int errCritCount; // количество ошибок, после которого
				    // дальнейшие проверки нецелесообразны
    private PairSet incompatibleErrors; // набор несовместимых друг с другом
					// ошибок
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
