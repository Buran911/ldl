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
    private String context;// контекст ошибки, на данный момент - строчка перед
    // ней
    private String fileName;
    private Integer errorPos;
    private Integer contextPos;
    private String info;

    public ParseError() {

    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo) {
	this(errClass, errType, lineNo, columnNo, null, null,null);
    }
//FIXME поправить possibleSolution
    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo,
	    String info) {
	this(errClass, errType, lineNo, columnNo, null, null , info);
    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo,
	    String errorLine, String context,String info) {
	super();
	this.errClass = errClass;
	this.errType = errType;
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.errorLine = errorLine;
	this.context = context;
	this.info = info;
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

//    public String getPossibleReason() {
//	return possibleSolution;
//    }

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

//    public void setPossibleSolution(String possibleSolution) {
//	this.possibleSolution = possibleSolution;
//    }

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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((errClass == null) ? 0 : errClass.hashCode());
	result = prime * result + ((errType == null) ? 0 : errType.hashCode());
	result = prime * result + ((errorPos == null) ? 0 : errorPos.hashCode());
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
	if (errorPos == null) {
	    if (other.errorPos != null)
		return false;
	}
	else if (!errorPos.equals(other.errorPos))
	    return false;
	return true;
    }



}
