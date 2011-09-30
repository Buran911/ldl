package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class StringAST extends LiteralAST {
	private String string;
	
	public StringAST(String string) {
		this.string = string;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
