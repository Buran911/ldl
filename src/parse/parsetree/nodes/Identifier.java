package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.IdentifierAST;

public class Identifier extends Node {
	private String identifier;

	public Identifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new IdentifierAST(getIdentifier());
	}
}
