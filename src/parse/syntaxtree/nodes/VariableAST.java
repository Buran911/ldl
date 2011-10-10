package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

public class VariableAST extends ExpressionAST {
	private IdentifierAST identifier;
	private String column;
	
	public void setIdentifier(IdentifierAST identifier) {
		this.identifier = identifier;
		addSuccessor(identifier);
	}

	public IdentifierAST getIdentifier() {
		return identifier;
	}


	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
