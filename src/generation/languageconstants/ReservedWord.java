package generation.languageconstants;

/**
 * Перечисление зарезервированных слов языка(не лексем).
 * @author hindu
 * */
public enum ReservedWord {
    type("_type"),
    column("_column"),
    table("_table"),
    visible("_visible"),
    main("_main"),
    params("_params"),
    code("_code"),
    codepath("_codepath"),
    value("_value");

    private final String reservedWord;

    public String word() {
	return reservedWord;
    }

    ReservedWord(String word) {
	reservedWord = word;
    }
}
