package parse.errhandler;

/**
 * ������������ ���� ��������� ����� ������.
 * 
 * @author hindu
 * */
public enum ErrorType {
    AbstractCompiler("����������� ������"),
    InvalidIdentifier("����������� �������������"),
    OperandNotFound("������� �� ������"),
    Syntax("������ ����������"),
    Quote("��� �������� � ���������� �������"),
    UnexpectedEOF("����������� ����� �����"),
    UnknownToken("����������� ����"),

    IdentifierRedefenition("��������� ����������� ��������������"),
    IdentifierUndefined("������� ����������� ��������������� � ����������� ���������� � ����� ������� ���������"),
    UncompatibleTypes("������ ������������� �����"),
    ParamCount("������ ���������� ����������"),
    SchemeTranslation("������ ���������� ������"),
    InputData("������ ������� ������"),

    NotImplementedYet("������ ������� �� ����� �� �����������");

    private final String ERRORTYPE;

    public String getDescription() {
	return ERRORTYPE;
    }

    ErrorType(String errorType) {
	this.ERRORTYPE = errorType;
    }

}
