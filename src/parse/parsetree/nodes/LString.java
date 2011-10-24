package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.StringAST;

public class LString extends Node {
	private String string;

	public LString(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new StringAST(getString());
	}
}
