package generation.languageconstants;

public enum Behaviour {

    sequence("sequence"),
    random("random");

    private final String behaviour;

    public String word() {
	return behaviour;
    }

    Behaviour(String word) {
	behaviour = word;
    }
}
