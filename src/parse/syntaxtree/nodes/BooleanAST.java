package parse.syntaxtree.nodes;

import parse.syntaxtree.Data;
import generation.walkers.TreeWalker;

public class BooleanAST extends LiteralAST implements Data{
	private Boolean bool;
	
	public BooleanAST(Boolean bool) {
		super();
		this.bool = bool;
	}

	public Boolean getBool() {
		return bool;
	}
	
	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

	@Override
	public String getData() {
		return bool.toString();
	}

}
