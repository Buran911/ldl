package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;

public class OperationCallAST extends NodeAST {
	private IdentifierAST identifier;
	private ParametresAST parametres;
	
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
