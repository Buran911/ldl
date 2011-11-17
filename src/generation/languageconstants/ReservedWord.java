package generation.languageconstants;

/**
 * ������������ ����������������� ���� �����(�� ������).
 * @author hindu
 * */
public enum ReservedWord {
    type("_type"),
    column("_column"),
    table("_table"),
    visible("_visible");

    private final String reservedWord;

    public String word() {
	return reservedWord;
    }

    ReservedWord(String word) {
	reservedWord = word;
    }
}
