package parse.errhandler;

/**
 * ������������ ���� ��������� ����� ������.
 * 
 * @author hindu
 * */
public enum ErrorType {
    // SyntaxError
    AbstractCompiler("����������� ������"),
    InvalidIdentifier("����������� �������������"),
    OperandNotFound("������� �� ������"),
    Quote("��� �������� � ���������� �������"),
    UnexpectedEOF("����������� ����� �����"),
    UnknownToken("����������� ����"),
    // SemanicError
    IdentifierRedefenition("��������� ����������� ��������������"),
    IdentifierUndefined("������� ����������� ��������������� � ����������� ���������� � ����� ������� ���������"),
    UncompatibleTypes("������ ������������� �����"),
    ParamCount("������ ���������� ����������"),
    // RuntimeError
    SchemeTranslation("������ ���������� ������"),
    InputData("������ ������� ������"),
    DataBaseDriverNotFound("�� ������ ������� ���� ������"),
    SQLError("������ � SQL �������"),
    NotImplementedYet("������ ������� �� ����� �� �����������");

    private final String ERRORTYPE;

    public String getDescription() {
	return ERRORTYPE;
    }

    ErrorType(String errorType) {
	this.ERRORTYPE = errorType;
    }

}
