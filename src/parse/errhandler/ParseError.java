package parse.errhandler;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Класс содержит общие данные об ошибке: класс, тип, локализацию, контекст,
 * комментарии по возможному решению
 * 
 * @author hindu
 * */
public class ParseError extends Error {
    private ErrorType errType;
    private Integer lineNo;
    private Integer columnNo;
    private String errorLine;
    private String context;// контекст ошибки, на данный момент - строчка перед
    // ней
    private String fileName;
    private Integer errorPos;
    private Integer contextPos;
    private String info;

    private Logger logger = Logger.getLogger(ParseError.class);
    public ParseError() {

    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo) {
	this(errClass, errType, lineNo, columnNo, null, null, null);
    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo, String info) {
	this(errClass, errType, lineNo, columnNo, null, null, info);
    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo, String errorLine, String context, String info) {
	super();
	this.errType = errType;
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.errorLine = errorLine;
	this.context = context;
	this.info = info;
    }

    public ErrorClass getErrClass() {
	return null;
    }

    public ErrorType getErrType() {
	return errType;
    }

    public Integer getLineNo() {
	return lineNo;
    }

    public Integer getColumnNo() {
	return columnNo;
    }

    public String getErrorLine() {
	return errorLine;
    }

    public String getContext() {
	return context;
    }

    // public String getPossibleReason() {
    // return possibleSolution;
    // }

    public void setErrType(ErrorType errType) {
	this.errType = errType;
    }

    public void setLineNo(Integer lineNo) {
	this.lineNo = lineNo;
    }

    public void setColumnNo(Integer columnNo) {
	this.columnNo = columnNo;
    }

    public void setErrorLine(String errorLine) {
	this.errorLine = errorLine;
    }

    public void setContext(String context) {
	this.context = context;
    }

    // public void setPossibleSolution(String possibleSolution) {
    // this.possibleSolution = possibleSolution;
    // }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public Integer getErrorPos() {
	return errorPos;
    }

    public void setErrorPos(Integer errorPos) {
	this.errorPos = errorPos;
    }

    public Integer getContextPos() {
	return contextPos;
    }

    public void setContextPos(Integer contextPos) {
	this.contextPos = contextPos;
    }

    public String getInfo() {
	return info;
    }

    public void setInfo(String info) {
	this.info = info;
    }

    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((errType == null) ? 0 : errType.hashCode());
	result = prime * result + ((errorPos == null) ? 0 : errorPos.hashCode());
	return result;
    }

    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ParseError other = (ParseError) obj;
	if (errType != other.errType)
	    return false;
	if (errorPos == null) {
	    if (other.errorPos != null)
		return false;
	}
	else if (!errorPos.equals(other.errorPos))
	    return false;
	return true;
    }
    
    public void printError() {
	STGroup group = new STGroupFile("generation/templates/errors.stg");
	ST st = group.getInstanceOf("error");
	st.add("errs", error);
	logger.error(st.render());
    }
    
}
