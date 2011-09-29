package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.BooleanAST;

public class Bool extends Node {
	private boolean bool;

	public Bool(boolean bool) {
		this.bool = bool;
	}

	public boolean getBool() {
		return bool;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new BooleanAST(getBool());
	}
}
