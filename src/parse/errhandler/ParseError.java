package parse.errhandler;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * ����� �������� ����� ������ �� ������: �����, ���, �����������, ��������,
 * ����������� �� ���������� �������
 * 
 * @author hindu
 * */
public class ParseError extends Error {
    private Integer lineNo;
    private Integer columnNo;
    private String errorLine;
    private String context;// �������� ������, �� ������ ������ - ������� �����
    // ���
    private String fileName;
    private Integer errorPos;
    private Integer contextPos;
    private String info;

    private Logger logger = Logger.getLogger(ParseError.class);
    public ParseError() {

    }

    public ParseError(ErrorClass errClass, ErrorType errorType, Integer lineNo, Integer columnNo) {
	this(errClass, errorType, lineNo, columnNo, null, null, null);
    }

    public ParseError(ErrorClass errClass, ErrorType errType, Integer lineNo, Integer columnNo, String info) {
	this(errClass, errType, lineNo, columnNo, null, null, info);
    }

    public ParseError(ErrorClass errClass, ErrorType errorType, Integer lineNo, Integer columnNo, String errorLine, String context, String info) {
	super();

	this.errorType = errorType;
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.errorLine = errorLine;
	this.context = context;
	this.info = info;
    }

    public ErrorClass getErrorClass() {
	return null;
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
    
    public void printError() {
	STGroup group = new STGroupFile("generation/templates/errors.stg");
	ST st = group.getInstanceOf("error");
	//st.add("errs", error);
	logger.error(st.render());
    }
}
