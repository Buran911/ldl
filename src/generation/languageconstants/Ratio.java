package generation.languageconstants;

public enum Ratio {
	less("<"),
	equal("="),
	more(">"),
	notEqual("<>"),
	lessEqual("<="),
	moreEqual(">=");
	
	private final String ratio;
	
	public String toString(){
		return ratio;
	}
	
	Ratio(String ratio){
		this.ratio = ratio;
	} 
}
