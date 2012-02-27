package generation.languageconstants;

/**
 * ѕеречеслиние операций с бинарными выражени€ми.
 * 
 * @author hindu
 * */
public enum Logical {
    and("AND"),
    or("OR"),
    xor("XOR");

    private final String logical;

    Logical(String logical) {
	this.logical = logical;
    }

    public String value() {
	return logical;
    }
}
