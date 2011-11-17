package parse.errhandler;

public enum PossibleReason {
	AbstractCompiler,//"  "
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
	InputData
}
