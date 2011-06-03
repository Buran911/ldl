package parse.errhandler;

/**
 *  Класс содержит общие данные об ошибке:
 * класс, тип, локализацию, контекст, комментарии по возможному решению
 * 
 * @author hindu
 * */
public class ParseError {
	private ErrorClass errClass;
	private ErrorType errType;
	private Integer lineNo;
	private Integer columnNo;
	private String errorLine;
	private String context; // контекст ошибки, на данный момент - строчка перед ней
	private String possibleSolution;

	public ParseError(){
		
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

	public String getPossibleSolution() {
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
}
