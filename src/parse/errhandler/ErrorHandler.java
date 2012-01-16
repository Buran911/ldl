package parse.errhandler;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import parse.util.Source;
import application.util.Halt;

// TODO Научить работать хэндлер

// TODO превышение криткаунта и прочая
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
public final class ErrorHandler implements Cloneable {
    private LinkedList<Error> errors;
    private Source src;
    private Logger logger = Logger.getLogger(ErrorHandler.class);
    private ProgramState programState;

    public List<ErrorType> getErrors() {
	List<ErrorType> returnList = new LinkedList<ErrorType>();
	for (Error error : errors) {
	    if (error.isParseError() && (error.getErrorClass() == ErrorClass.semantic))
		returnList.add(error.getErrorType());
	}
	return returnList;
    }

    {
	errors = new LinkedList<Error>();
    }

    public ErrorHandler(Source src) {
	this.src = src;
    }

    public void addError(RuntimeError error) {

    }

    public void addError(Error error) {
	switch (error.getErrorClass())
	    {
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

    public boolean hasErrors() {
	return errors.size() > 0 ? true : false;
    }

    // Найти совместимость err1 и err2
    // Совместимость ошибок это когда одна ошибка не является следствием другой
    // ошибки и может быть обнаружены независимо

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
	switch (programState)
	    {
	    case SyntaxChecked:
		if (hasErrors())
		    throw new Halt();
		break;
	    case SemanticChecked:
		if (hasErrors())
		    throw new Halt();
		break;
	    }
    }

    public void printErrors() {
	STGroup group = new STGroupFile("generation/templates/errors.stg");
	ST st = group.getInstanceOf("errors");
	st.add("errs", errors);
	logger.error(st.render());
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((errors == null) ? 0 : errors.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ErrorHandler other = (ErrorHandler) obj;
	if (errors == null) {
	    if (other.errors != null)
		return false;
	}
	else if (!errors.equals(other.errors))
	    return false;
	return true;
    }

    @Override
    public ErrorHandler clone() {
	ErrorHandler eH = new ErrorHandler(null);

	eH.errors = new LinkedList<Error>();
	for(Error error:errors){
	eH.errors.add(error);
	}
	eH.programState = programState;
	eH.src = src.clone();

	return eH;
    }
}
