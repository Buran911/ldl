package parse.errhandler;

/**
 * Класс содержит общие данные об ошибке: класс, тип, локализацию, контекст,
 * комментарии по возможному решению
 * 
 * @author hindu
 * */
public class ParseError extends Error {
    private Integer lineNo;
    private Integer columnNo;
    private String errorLine;
    private String context;// контекст ошибки - строчка перед ней
    private String fileName;
    private Integer errorPos;
    private Integer contextPos;
    private String info;

    public ParseError() {
	super();
    }

    public ParseError(ErrorType errorType, Integer lineNo, Integer columnNo) {
	this(errorType, lineNo, columnNo, null, null, null);
    }

    public ParseError(ErrorType errType, Integer lineNo, Integer columnNo, String info) {
	this(errType, lineNo, columnNo, null, null, info);
    }

    public ParseError(ErrorType errorType, Integer lineNo, Integer columnNo, String errorLine, String context, String info) {
	super();
	this.errorClass = ErrorType.getErrorClass(errorType);
	this.errorType = errorType;
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.errorLine = errorLine;
	this.context = context;
	this.info = info;
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
	result = prime * result + ((errorType == null) ? 0 : errorType.hashCode());
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
	if (errorType != other.errorType)
	    return false;
	if (errorPos == null) {
	    if (other.errorPos != null)
		return false;
	}
	else if (!errorPos.equals(other.errorPos))
	    return false;
	return true;
    }

    public ParseError clone() {
	ParseError parseError = new ParseError();

	parseError.lineNo = lineNo;
	parseError.columnNo = columnNo;
	parseError.errorLine = errorLine;
	parseError.context = context;
	parseError.fileName = fileName;
	parseError.errorPos = errorPos;
	parseError.contextPos = contextPos;
	parseError.info = info;

	parseError.errorClass = ErrorType.getErrorClass(errorType);
	parseError.errorType = errorType;

	if (this.isParseError())
	    parseError.setParseError();
	else
	    parseError.setRuntimeError();

	return parseError;
    }
}