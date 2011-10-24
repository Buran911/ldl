package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.TypeAST;

public class Type extends Node {
	private String type;

	public Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new TypeAST(getType());
	}
	
}
