package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.SimpleNameAST;

public class SimpleName extends Node {
	private String name;

	public SimpleName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		SimpleNameAST simpleName = new SimpleNameAST(getName());
		
		return simpleName;
	}
}
