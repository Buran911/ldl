package parse.errhandler;

/**
 *  ����� �������� ����� ������ �� ������:
 * �����, ���, �����������, ��������, ����������� �� ���������� �������
 * 
 * @author hindu
 * */
public class Error {
	private ErrorClass errClass;
	private ErrorType errType;
	private int lineNo;
	private String errorLine;
	private String context; // �������� ������, �� ������ ������ - ������� ����� ���
	private String possibleSolution;


	public Error(ErrorClass errClass, ErrorType errType, int lineNo,
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
