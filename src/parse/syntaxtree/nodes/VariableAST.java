package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class VariableAST extends ExpressionAST {
	private IdentifierAST identifier;
	
	public void setIdentifier(IdentifierAST identifier) {
		this.identifier = identifier;
		addSuccessor(identifier);
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
