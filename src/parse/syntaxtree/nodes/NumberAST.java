package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class NumberAST extends LiteralAST {
	private Double number;
	
	public NumberAST(Double number) {
		this.number = number;
	}
	
	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
