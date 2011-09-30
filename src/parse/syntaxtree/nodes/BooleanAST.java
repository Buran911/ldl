package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class BooleanAST extends LiteralAST {
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

}
