package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class VariableAST extends ExpressionAST {
	private IdentifierAST identifier;
	
	public void setIdentifier(IdentifierAST identifier) {
		this.identifier = identifier;
		addSuccessor(identifier);
	}

	public IdentifierAST getIdentifier() {
		return identifier;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
