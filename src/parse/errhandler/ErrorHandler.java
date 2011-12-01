package parse.errhandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import parse.util.PairSet;
import parse.util.Source;
import application.util.Halt;

// TODO Научить работать хэндлер

// TODO проверить на несовместимость ошибки, превышение криткаунта и
// прочая

/**
 * Класс накапливает, содержит и обрабатывает ошибки парсинга и анализа
 * исходного кода(синтаксические и семантические). Каждая ошибка содержит
 * подробное описание. При возникновение ошибок препятсвующих возможности
 * продолжения работы приложения, либо накоплении критической массы заверщается
 * работа приложения.
 * 
 * @author hindu
 * */
public final class ErrorHandler {
    private LinkedList<Error> errors;
    private Source src;
    private Logger logger = Logger.getLogger(ErrorHandler.class);
    private static ProgramStates programState = ProgramStates.Deployed;
    
    private HashMap<ErrorType, LinkedList<ErrorType>> hm;

    {
	errors = new LinkedList<Error>();

	hm = new HashMap<ErrorType, LinkedList<ErrorType>>();

	fillHM();

    }

    private void fillHM() {
	LinkedList<ErrorType> IdentifierRedefenition = new LinkedList<ErrorType>();
	IdentifierRedefenition.add(ErrorType.UncompatibleTypes);
	IdentifierRedefenition.add(ErrorType.NotImplementedYet);

	LinkedList<ErrorType> UncompatibleTypes = new LinkedList<ErrorType>();
	UncompatibleTypes.add(ErrorType.IdentifierRedefenition);
	UncompatibleTypes.add(ErrorType.IdentifierUndefined);
	UncompatibleTypes.add(ErrorType.NotImplementedYet);

	LinkedList<ErrorType> IdentifierUndefined = new LinkedList<ErrorType>();
	IdentifierUndefined.add(ErrorType.UncompatibleTypes);
	IdentifierUndefined.add(ErrorType.NotImplementedYet);

	LinkedList<ErrorType> NotImplementedYet = new LinkedList<ErrorType>();
	NotImplementedYet.add(ErrorType.IdentifierRedefenition);
	NotImplementedYet.add(ErrorType.IdentifierUndefined);
	NotImplementedYet.add(ErrorType.UncompatibleTypes);
	NotImplementedYet.add(ErrorType.ParamCount);

	LinkedList<ErrorType> ParamCount = new LinkedList<ErrorType>();
	ParamCount.add(ErrorType.NotImplementedYet);

	hm.put(ErrorType.IdentifierRedefenition, IdentifierRedefenition);
	hm.put(ErrorType.UncompatibleTypes, UncompatibleTypes);
	hm.put(ErrorType.IdentifierUndefined, IdentifierUndefined);
	hm.put(ErrorType.NotImplementedYet, NotImplementedYet);
	hm.put(ErrorType.ParamCount, ParamCount);
    }

    public ErrorHandler(Source src) {
	this.src = src;
    }

    public void addError(RuntimeError error) {
	error.setRuntimeError();
	errors.add(error);

	printErrors();

	throw new Halt();
    }

    public void addError(ParseError error) {
	if (error.getErrorClass() == ErrorClass.syntax) {
	    logger.error("Syntax пыщ-пыщ");
	}
	else {
	    setInfoAboutError(error);
	    error.setParseError();

	    errors.add(error);
	}
    }

    public boolean hasError(Error error) {
	return errors.contains(error);
    }

    public boolean isPermitted(LinkedList<ErrorType> errors) {

	for (ErrorType error : errors) {

	    for (Error err : this.errors) {
		if (hasErrorCompatibility(error, err.getErrorType())) {
		    return false;
		}
	    }
	}
	return true;
    }

    public boolean hasErrors() {
	return errors.size() > 0 ? true : false;
    }

    // Найти совместимость err1 и err2
    public boolean hasErrorCompatibility(ErrorType err1, ErrorType err2) {
	Iterator<ErrorType> iterator = hm.get(err2).iterator();
	while (iterator.hasNext()) {
	    if (iterator.next() == err1) {
		return true;
	    }
	}
	return false;
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

    public static ProgramStates getProgramState() {
        return programState;
    }

    public static void setProgramState(ProgramStates programState) {
        ErrorHandler.programState = programState;
        switch(programState){
        case  SyntaxChecked: for() break;
            
        }
    }

    // TODO удалить этот метод и поправить где он есть
    public void printErrors() {
	STGroup group = new STGroupFile("generation/templates/errors.stg");
	ST st = group.getInstanceOf("errors");
	st.add("errs", errors);
	logger.error(st.render());
    }
}
