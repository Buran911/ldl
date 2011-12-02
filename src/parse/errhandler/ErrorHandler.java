package parse.errhandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import parse.util.Source;
import application.util.Halt;

// TODO Научить работать хэндлер

// TODO проверить на несовместимость ошибки, превышение криткаунта и
// прочая
//
// Halt cемантических ошибкок в WalkerRunner 

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
    private ProgramState programState;

    private HashMap<ErrorType, LinkedList<ErrorType>> compatibilityMap;

    {
	errors = new LinkedList<Error>();
	compatibilityMap = new HashMap<ErrorType, LinkedList<ErrorType>>();

	fillCompatibilityMap();
    }

    private void fillCompatibilityMap() {
	LinkedList<ErrorType> identifierRedefenition = new LinkedList<ErrorType>();
	identifierRedefenition.add(ErrorType.UncompatibleTypes);
	identifierRedefenition.add(ErrorType.NotImplementedYet);

	LinkedList<ErrorType> uncompatibleTypes = new LinkedList<ErrorType>();
	uncompatibleTypes.add(ErrorType.IdentifierRedefenition);
	uncompatibleTypes.add(ErrorType.IdentifierUndefined);
	uncompatibleTypes.add(ErrorType.NotImplementedYet);

	LinkedList<ErrorType> identifierUndefined = new LinkedList<ErrorType>();
	identifierUndefined.add(ErrorType.UncompatibleTypes);
	identifierUndefined.add(ErrorType.NotImplementedYet);

	LinkedList<ErrorType> notImplementedYet = new LinkedList<ErrorType>();
	notImplementedYet.add(ErrorType.IdentifierRedefenition);
	notImplementedYet.add(ErrorType.IdentifierUndefined);
	notImplementedYet.add(ErrorType.UncompatibleTypes);
	notImplementedYet.add(ErrorType.ParamCount);

	LinkedList<ErrorType> paramCount = new LinkedList<ErrorType>();
	paramCount.add(ErrorType.NotImplementedYet);

	compatibilityMap.put(ErrorType.IdentifierRedefenition, identifierRedefenition);
	compatibilityMap.put(ErrorType.UncompatibleTypes, uncompatibleTypes);
	compatibilityMap.put(ErrorType.IdentifierUndefined, identifierUndefined);
	compatibilityMap.put(ErrorType.NotImplementedYet, notImplementedYet);
	compatibilityMap.put(ErrorType.ParamCount, paramCount);
    }

    public ErrorHandler(Source src) {
	this.src = src;
    }

    public void addError(RuntimeError error) {

    }

    public void addError(Error error) {
	switch (error.getErrorClass()){
	    case syntax:
		error.setParseError();
		errors.add(error);
		break;
	    case semantic:
		setInfoAboutError((ParseError) error);
		error.setParseError();
		errors.add(error);
		break;
	    case runtime:
		error.setRuntimeError();
		errors.add(error);
		printErrors();
		throw new Halt();
	}
    }

    public boolean hasError(Error error) {
	return errors.contains(error);
    }

    public boolean isPermitted(List<ErrorType> returnErrorTypes) {
	for (ErrorType errorType : returnErrorTypes) {
	    for (Error error : errors) {
		if (hasErrorCompatibility(errorType, error.getErrorType())) {
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
	List<ErrorType> errTypes = compatibilityMap.get(err2);
	
	return errTypes.contains(err1);
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

    public ProgramState getProgramState() {
	return programState;
    }

    public void setProgramState(ProgramState programState) {
	this.programState = programState;
	switch (programState){
	    case SyntaxChecked:
		if (hasErrors()) {
		    throw new Halt();
		}
		break;
	    case SemanticChecked:
		if (hasErrors()) {
		    throw new Halt();
		}
		break;
	}
    }

    public void printErrors() {
	STGroup group = new STGroupFile("generation/templates/errors.stg");
	ST st = group.getInstanceOf("errors");
	st.add("errs", errors);
	logger.error(st.render());
    }
}
