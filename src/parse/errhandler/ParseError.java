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
	private int lineNo;
	private String errorLine;
	private String context; // контекст ошибки, на данный момент - строчка перед ней
	private String possibleSolution;


	public ParseError(ErrorClass errClass, ErrorType errType, int lineNo,
			String errorLine, String context, String possibleSolution) {
		super();
		this.errClass = errClass;
		this.errType = errType;
		this.lineNo = lineNo;
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

	public int getLineNo() {
		return lineNo;
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
}
