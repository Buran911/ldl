package parse.errhandler;

import java.util.EnumMap;

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
    
    // SemanticError
    IdentifierRedefenition("��������� ����������� ��������������"),
    IdentifierUndefined("������������� ����� �� ������������� ��������������"),
    UncompatibleTypes("������ ������������� �����"),
    ParamCount("������ ���������� ����������"),
    
    // RuntimeError
    SchemeTranslation("������ ���������� ������"),
    InputData("������ ������� ������"),
    DataBaseDriverNotFound("�� ������ ������� ���� ������"),
    SQLError("������ � SQL �������"),
    NotImplementedYet("������ ������� �� ����� �� �����������");

    private static EnumMap<ErrorType, ErrorClass> pair = new EnumMap<ErrorType, ErrorClass>(ErrorType.class);

    static {
	// SyntaxError
	pair.put(ErrorType.AbstractCompiler, ErrorClass.syntax);
	pair.put(ErrorType.InvalidIdentifier, ErrorClass.syntax);
	pair.put(ErrorType.OperandNotFound, ErrorClass.syntax);
	pair.put(ErrorType.Quote, ErrorClass.syntax);
	pair.put(ErrorType.UnexpectedEOF, ErrorClass.syntax);
	pair.put(ErrorType.UnknownToken, ErrorClass.syntax);
	
	// SemanticError
	pair.put(ErrorType.IdentifierRedefenition, ErrorClass.semantic);
	pair.put(ErrorType.IdentifierUndefined, ErrorClass.semantic);
	pair.put(ErrorType.UncompatibleTypes, ErrorClass.semantic);
	pair.put(ErrorType.ParamCount, ErrorClass.semantic);
	
	// RuntimeError
	pair.put(ErrorType.SchemeTranslation, ErrorClass.runtime);
	pair.put(ErrorType.InputData, ErrorClass.runtime);
	pair.put(ErrorType.DataBaseDriverNotFound, ErrorClass.runtime);
	pair.put(ErrorType.SQLError, ErrorClass.runtime);
	pair.put(ErrorType.NotImplementedYet, ErrorClass.runtime);
    }

    public static ErrorClass getErrorClass(ErrorType errorType) {
	return pair.get(errorType);
    }

    private final String ERRORTYPE;

    public String getDescription() {
	return ERRORTYPE;
    }

    ErrorType(String errorType) {
	this.ERRORTYPE = errorType;
    }
}