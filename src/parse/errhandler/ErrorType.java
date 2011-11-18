package parse.errhandler;

/**
 * Перечисление всех возможных типов ошибок.
 * 
 * @author hindu
 * */
public enum ErrorType {
    AbstractCompiler("Неизвестная ошибка"),
    InvalidIdentifier("Неизвестный идентификатор"),
    OperandNotFound("Операнд не найден"),
    Syntax("Ошибка синтаксиса"),
    Quote("Нет гармонии в количестве ковычек"),
    UnexpectedEOF("Неожиданный конец файла"),
    UnknownToken("Неизвестный знак"),

    IdentifierRedefenition("Повторное определение идентификатора"),
    IdentifierUndefined("Попытка определения идентификаторов с одинаковыми названиями в одной области видимости"),
    UncompatibleTypes("Ошибка совместимости типов"),
    ParamCount("Ошибка количества параметров"),
    SchemeTranslation("Ошибка трансляции файлов"),
    InputData("Ошибка входных файлов"),

    NotImplementedYet("Данная функция до конца не реализована");

    private final String ERRORTYPE;

    public String getDescription() {
	return ERRORTYPE;
    }

    ErrorType(String errorType) {
	this.ERRORTYPE = errorType;
    }

}
