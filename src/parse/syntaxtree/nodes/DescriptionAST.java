package parse.syntaxtree.nodes;

import parse.parsetree.nodes.Identifier;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class DescriptionAST extends NodeAST {
	private IdentifierAST identifier;
	private TypeAST type;
	
	public void setIdentifier(IdentifierAST identifier) {
		this.identifier = identifier;
		addSuccessor(identifier);
	}


	public void setType(TypeAST type) {
		this.type = type;
		addSuccessor(type);
	}


	public IdentifierAST getIdentifier() {
		return identifier;
	}


	public TypeAST getType() {
		return type;
	}


	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
