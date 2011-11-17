package parse.errhandler;

/**
 * Класс содержит общие данные об ошибке: класс, тип, локализацию, контекст,
 * комментарии по возможному решению
 * 
 * @author hindu
 * */
public class ParseError {
    private ErrorClass errClass;
    private ErrorType errType;
    private Integer lineNo;
    private Integer columnNo;
    private String errorLine;
    private String context; // контекст ошибки, на данный момент - строчка перед
			    // ней
    private String possibleSolution;

    public ParseError() {

    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo) {
	this(errClass, errType, lineNo, columnNo, null, null, null);
    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo,
	    String possibleSolution) {
	this(errClass, errType, lineNo, columnNo, null, null, possibleSolution);
    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo,
	    String errorLine, String context, String possibleSolution) {
	super();
	this.errClass = errClass;
	this.errType = errType;
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.errorLine = errorLine;
	this.context = context;
	this.possibleSolution = possibleSolution;
    }

    public ErrorClass getErrClass() {
	return errClass;
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

	public String getPossibleReason() {
	return possibleSolution;
    }

    public void setErrClass(ErrorClass errClass) {
	this.errClass = errClass;
    }

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

    public void setPossibleSolution(String possibleSolution) {
	this.possibleSolution = possibleSolution;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((errClass == null) ? 0 : errClass.hashCode());
	result = prime * result + ((errType == null) ? 0 : errType.hashCode());
	result = prime * result + ((lineNo == null) ? 0 : lineNo.hashCode());
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
	ParseError other = (ParseError) obj;
	if (errClass != other.errClass)
	    return false;
	if (errType != other.errType)
	    return false;
	if (lineNo == null) {
	    if (other.lineNo != null)
		return false;
	}
	else if (!lineNo.equals(other.lineNo))
	    return false;
	return true;
    }

}
