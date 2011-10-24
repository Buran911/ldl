package generation.languageconstants;

public enum ReservedWord {
	type("_type"),
	column("_column"),
	table("_table");
	
	private final String reservedWord;
	
	public String word(){
		return reservedWord;
	}
	
	ReservedWord(String word){
		reservedWord = word;
	}
}
