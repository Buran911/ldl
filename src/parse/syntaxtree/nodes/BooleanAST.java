package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class BooleanAST extends LiteralAST {
	private Boolean bool;
	
	public BooleanAST(Boolean bool) {
		super();
		this.bool = bool;
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
