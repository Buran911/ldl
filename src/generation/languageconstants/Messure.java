package generation.languageconstants;

public enum Messure {

    day("day"),
    month("month"),
    year("year");
    
    private final String period;

    public String period() {
	return period;
    }

    Messure(String p) {
	period = p;
    }
}
