package parse.errhandler;

import java.util.EnumMap;

/**
 * Перечисление всех возможных типов ошибок.
 * 
 * @author hindu
 * */
public enum ErrorType {
    // SyntaxError
    AbstractCompiler("Неизвестная ошибка"),
    InvalidIdentifier("Неизвестный идентификатор"),
    OperandNotFound("Операнд не найден"),
    Quote("Нет гармонии в количестве ковычек"),
    UnexpectedEOF("Неожиданный конец файла"),
    UnknownToken("Неизвестный знак"),
    
    // SemanticError
    IdentifierRedefenition("Повторное определение идентификатора"),
    IdentifierUndefined("Использование ранее не определенного идентификатора"),
    UncompatibleTypes("Ошибка совместимости типов"),
    ParamCount("Ошибка количества параметров"),
    
    // RuntimeError
    SchemeTranslation("Ошибка трансляции файлов"),
    InputData("Ошибка входных файлов"),
    DataBaseDriverNotFound("Не найден драйвер базы данных"),
    SQLError("Ошибка в SQL запросе"),
    NotImplementedYet("Данная функция до конца не реализована");

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