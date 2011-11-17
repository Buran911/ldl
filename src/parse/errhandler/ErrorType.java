package parse.errhandler;

/**
 * ������������ ���� ��������� ����� ������.
 * @author hindu
 * */
public enum ErrorType {
    AbstractCompiler,
    InvalidIdentifier,
    OperandNotFound,
    Syntax,
    Quote,
    UnexpectedEOF,
    UnknownToken,

    IdentifierRedefenition,
    IdentifierUndefined,
    UncompatibleTypes,
    ParamCount,
    SchemeTranslation,
    InputData,

    NotImplementedYet;
}
