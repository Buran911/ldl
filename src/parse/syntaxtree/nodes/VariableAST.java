package parse.syntaxtree.nodes;

import generation.idtable.Identifier;
import generation.walkers.TreeWalker;

public class VariableAST extends ExpressionAST {
	private IdentifierAST identifier;
	private Identifier id;
	
	public void setIdentifier(IdentifierAST identifier) {
		this.identifier = identifier;
		addSuccessor(identifier);
	}

	public IdentifierAST getIdentifier() {
		return identifier;
	}


	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
