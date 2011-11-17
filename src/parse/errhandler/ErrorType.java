package parse.errhandler;

/**
 * Перечисление всех возможных типов ошибок.
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
