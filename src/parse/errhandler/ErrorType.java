package parse.errhandler;

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
    // SemanicError
    IdentifierRedefenition("Повторное определение идентификатора"),
    IdentifierUndefined("Попытка определения идентификаторов с одинаковыми названиями в одной области видимости"),
    UncompatibleTypes("Ошибка совместимости типов"),
    ParamCount("Ошибка количества параметров"),
    // RuntimeError
    SchemeTranslation("Ошибка трансляции файлов"),
    InputData("Ошибка входных файлов"),
    DataBaseDriverNotFound("Не найден драйвер базы данных"),
    SQLError("Ошибка в SQL запросе"),
    NotImplementedYet("Данная функция до конца не реализована");

    private final String ERRORTYPE;

    public String getDescription() {
	return ERRORTYPE;
    }

    ErrorType(String errorType) {
	this.ERRORTYPE = errorType;
    }

}
