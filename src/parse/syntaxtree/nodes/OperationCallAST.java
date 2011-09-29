package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

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
		// TODO Auto-generated method stub

	}

}
